package ua.nure.dl.repo.relational;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.nure.dl.model.entity.TestQuestionEntity;

/**
 * @author Bohdan_Suprun
 */
public interface TestQuestionDao extends JpaRepository<TestQuestionEntity, Long> {

}
