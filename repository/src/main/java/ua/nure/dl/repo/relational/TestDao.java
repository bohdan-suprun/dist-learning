package ua.nure.dl.repo.relational;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.nure.dl.model.entity.TestEntity;

/**
 * @author Bohdan_Suprun
 */
public interface TestDao extends JpaRepository<TestEntity, Long> {

}
