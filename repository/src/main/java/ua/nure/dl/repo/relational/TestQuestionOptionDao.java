package ua.nure.dl.repo.relational;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.nure.dl.model.entity.TestQuestionOptionEntity;

/**
 * @author Bohdan_Suprun
 */
public interface TestQuestionOptionDao extends JpaRepository<TestQuestionOptionEntity, Long> {

}
