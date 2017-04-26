package ua.nure.dl.repo.competency;

import ua.nure.dl.model.Competency;
import ua.nure.dl.model.Subject;

import java.util.Collection;

/**
 * @author Bohdan_Suprun
 */
public interface CompetencySubjectGraphDao {

    Collection<Subject> getAllSubjectsForCompetency(long competency);

    boolean addRelation(Subject subject, Competency competency);
}
