package ua.nure.dl.competencymanager.service;

import ua.nure.dl.model.Competency;
import ua.nure.dl.model.Subject;

import java.util.Collection;

/**
 * @author Bohdan_Suprun
 */
public interface CompetencySubjectService {

    Collection<Subject> getAllSubjectsForCompetency(Competency competency);

    Collection<Subject> getAllSubjectsForCompetency(long competency);

    boolean addRelation(Subject subject, Competency competency);

    Competency addCompetency(Competency competency);

    Subject addSubject(Subject subject);

    void removeSubject(long subjId);

    void removeCompetency(long compId);

    Iterable<Competency> getAllCompetencies();

    Iterable<Subject> getAllSubjects();
}
