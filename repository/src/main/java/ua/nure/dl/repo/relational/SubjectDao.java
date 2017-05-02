package ua.nure.dl.repo.relational;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.nure.dl.model.entity.SubjectEntity;

/**
 * @author Bohdan_Suprun
 */
public interface SubjectDao extends JpaRepository<SubjectEntity, Long> {
	
}
