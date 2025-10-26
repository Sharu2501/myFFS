package org.myfss.repository;

import org.myfss.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long>{
    Company findCompaniesBySocialReason(String socialReason);
}
