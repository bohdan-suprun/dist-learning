package ua.nure.dl.repo.competency;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.nure.dl.model.Competency;

/**
 * @author Bohdan_Suprun
 */
public interface CompetencyDao extends JpaRepository<Competency, Long> {
}
