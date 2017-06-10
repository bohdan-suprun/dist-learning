package ua.nure.dl.repo.relational;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.nure.dl.model.entity.SubjectMaterialEntity;

/**
 * @author Bohdan_Suprun
 */
public interface SubjectMaterialDao extends JpaRepository<SubjectMaterialEntity, Long> {

}
