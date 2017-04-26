package ua.nure.dl.repo.competency;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.nure.dl.model.Subject;

/**
 * @author Bohdan_Suprun
 */
public interface SubjectDao extends JpaRepository<Subject, Long> {
}
