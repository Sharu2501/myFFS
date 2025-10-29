package org.myfss.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.myfss.exception.CompanyExceptions.*;
import org.myfss.model.Company;
import org.myfss.repository.CompanyRepository;
import org.myfss.util.ValidationUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Company getCompanyById(Long id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException(id));
    }

    @Transactional
    public Company createCompany(Company newCompany) {
        validateCompanyRequiredFields(newCompany);
        newCompany.setId(null);

        return companyRepository.save(newCompany);
    }

    @Transactional
    public Company updateCompany(Long id, Company updatedCompany) {
        Company existing = getCompanyById(id);

        validateCompanyRequiredFields(updatedCompany);
        applyChanges(existing, updatedCompany);

        return companyRepository.save(existing);
    }

    private void validateCompanyRequiredFields(Company company) {
        ValidationUtils.validateRequiredFields(
                Map.of(
                        "raison sociale", company.getSocialReason(),
                        "adresse", company.getAddress()
                ),
                InvalidCompanyDataException::new
        );

        ValidationUtils.checkConflicts(
                Map.of(
                        "Entreprise existe déjà.", () ->
                                companyRepository.existsBySocialReasonAndAddressAndIdNot(
                                        company.getSocialReason().trim(), company.getAddress().trim(), company.getId()
                                )
                ),
                CompanyAlreadyExistsException::new
        );
    }

    private void applyChanges(Company targetCompany, Company sourceCompany) {
        if (!sourceCompany.getSocialReason().equals(targetCompany.getSocialReason())) {
            targetCompany.setSocialReason(sourceCompany.getSocialReason());
        }
        if (!sourceCompany.getAddress().equals(targetCompany.getAddress())) {
            targetCompany.setAddress(sourceCompany.getAddress());
        }
        if (sourceCompany.getAccessInformation() != null &&
                !sourceCompany.getAccessInformation().equals(targetCompany.getAccessInformation())) {
            targetCompany.setAccessInformation(sourceCompany.getAccessInformation());
        }
    }
}
