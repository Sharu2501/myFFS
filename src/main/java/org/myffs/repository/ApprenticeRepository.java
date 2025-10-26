package org.myffs.repository;

import org.myffs.model.Apprentice;
import org.myffs.model.enums.Major;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApprenticeRepository extends JpaRepository<Apprentice, Long> {

    List<Apprentice> findByMajorNot(Major major);

    @Query("""
                SELECT a FROM Apprentice a
                LEFT JOIN a.company c
                LEFT JOIN a.mission m
                WHERE (:name IS NULL OR LOWER(a.lastName) LIKE LOWER(CONCAT('%', :name, '%')) OR LOWER(a.firstName) LIKE LOWER(CONCAT('%', :name, '%')))
                AND (:company IS NULL OR LOWER(c.socialReason) LIKE LOWER(CONCAT('%', :company, '%')))
                AND (:missionKeyword IS NULL OR LOWER(m.keywords) LIKE LOWER(CONCAT('%', :missionKeyword, '%')))
                AND (:academicYear IS NULL OR a.academicYear LIKE CONCAT('%', :academicYear, '%'))
                AND a.major <> 'ALUMNI'
            """)
    List<Apprentice> searchApprentices(
            @Param("name") String name,
            @Param("company") String company,
            @Param("missionKeyword") String missionKeyword,
            @Param("academicYear") String academicYear
    );
}