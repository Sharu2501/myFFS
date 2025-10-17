package faria.sasikumar.sylla.myffs.repository;

import faria.sasikumar.sylla.myffs.model.Visite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisiteRepository extends JpaRepository<Visite, Long> {}
