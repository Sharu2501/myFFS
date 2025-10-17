package faria.sasikumar.sylla.myffs.repository;

import faria.sasikumar.sylla.myffs.model.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {}
