package org.myfss.service;

import org.myfss.model.Company;
import java.util.List;

public interface CompanyService {

    List<Company> getAllCompanies();

    Company getCompanyById(Long id);

    Company createCompany(Company newCompany);

    Company updateCompany(Long id, Company updatedCompany);
}