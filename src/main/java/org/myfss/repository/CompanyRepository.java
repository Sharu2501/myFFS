package org.myfss.repository;

import org.myfss.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    boolean existsBySocialReasonAndAddressAndIdNot(String socialReason, String address, Long id);
}
