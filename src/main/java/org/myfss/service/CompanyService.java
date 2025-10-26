package org.myfss.service;

import lombok.RequiredArgsConstructor;
import org.myfss.exception.CompanyNotFoundException;
import org.myfss.model.Company;
import org.myfss.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Company findBySocialReason(String socialReason) {
        return companyRepository.findCompaniesBySocialReason(socialReason);
    }

    public void saveCompany(Company company) {
        companyRepository.save(company);
    }
}
