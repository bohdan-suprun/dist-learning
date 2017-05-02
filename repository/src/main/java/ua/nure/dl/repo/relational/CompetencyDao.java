package ua.nure.dl.repo.relational;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.nure.dl.model.entity.CompetencyEntity;

/**
 * @author Bohdan_Suprun
 */
public interface CompetencyDao extends JpaRepository<CompetencyEntity, Long> {
}
