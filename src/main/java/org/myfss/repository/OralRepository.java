package org.myfss.repository;

import org.myfss.model.Oral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OralRepository extends JpaRepository<Oral, Long> {
}