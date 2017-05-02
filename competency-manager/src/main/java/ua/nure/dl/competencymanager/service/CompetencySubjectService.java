package ua.nure.dl.competencymanager.service;

import ua.nure.dl.model.dto.Competency;
import ua.nure.dl.model.dto.Subject;

import java.util.Collection;

/**
 * @author Bohdan_Suprun
 */
public interface CompetencySubjectService {

    Collection<Subject> getAllSubjectsForCompetency(Competency competency);

    Collection<Subject> getAllSubjectsForCompetency(long competency);

    void addRelation(Subject subject, Competency competency);

    void addRelation(Subject subject, long compId);

    void addRelation(long subjectId, long competencyId);

    Competency addCompetency(Competency competency);

    Subject addSubject(Subject subject);

    void removeSubject(long subjId);

    void removeCompetency(long compId);

    Iterable<Competency> getAllCompetencies();

    Iterable<Subject> getAllSubjects();
}
