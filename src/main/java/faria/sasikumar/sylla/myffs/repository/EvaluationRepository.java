package faria.sasikumar.sylla.myffs.repository;

import faria.sasikumar.sylla.myffs.model.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {}
