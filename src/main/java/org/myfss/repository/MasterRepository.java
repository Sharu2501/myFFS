package org.myfss.repository;

import org.myfss.model.Master;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterRepository extends JpaRepository<Master, Long> {

    boolean existsByEmailAndIdNot(String email, Long id);

    boolean existsByLastNameAndFirstNameAndIdNot(String lastName, String firstName, Long id);

    boolean existsByPhoneNumberAndIdNot(String phoneNumber, Long id);
}
