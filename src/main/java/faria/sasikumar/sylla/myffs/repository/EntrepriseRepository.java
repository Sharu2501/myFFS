package faria.sasikumar.sylla.myffs.repository;

import faria.sasikumar.sylla.myffs.model.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntrepriseRepository extends JpaRepository<Entreprise, Long> {}
