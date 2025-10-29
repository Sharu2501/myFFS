package org.myfss.service;

import jakarta.transaction.Transactional;
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

    public Company getCompanyById(Long Id) {
        return companyRepository.findById(Id)
                .orElseThrow(() -> new CompanyNotFoundException(Id));
    }

    @Transactional
    public Company createCompany(Company newCompany) {
        newCompany.setId(null);
        return companyRepository.save(newCompany);
    }
}
