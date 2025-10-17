package faria.sasikumar.sylla.myffs.repository;

import faria.sasikumar.sylla.myffs.model.MaitreApprentissage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaitreApprentissageRepository extends JpaRepository<MaitreApprentissage, Long> {}
