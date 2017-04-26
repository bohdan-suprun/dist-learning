package ua.nure.dl.competencymanager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.nure.dl.competencymanager.service.CompetencySubjectService;
import ua.nure.dl.model.Competency;
import ua.nure.dl.model.Subject;
import ua.nure.dl.repo.competency.CompetencyDao;
import ua.nure.dl.repo.competency.CompetencySubjectGraphDao;
import ua.nure.dl.repo.competency.SubjectDao;

import java.util.Collection;

/**
 * @author Bohdan_Suprun
 */
@Service
public class CompetencySubjectServiceImpl implements CompetencySubjectService {

    @Autowired
    private CompetencyDao competencyDao;
    @Autowired
    private SubjectDao subjectDao;
    @Autowired
    private CompetencySubjectGraphDao competencySubjectGraphDao;

    @Override
    public Collection<Subject> getAllSubjectsForCompetency(Competency competency) {
        return competencySubjectGraphDao.getAllSubjectsForCompetency(competency.getId());
    }

    @Override
    public Collection<Subject> getAllSubjectsForCompetency(long competency) {
        return competencySubjectGraphDao.getAllSubjectsForCompetency(competency);
    }

    @Override
    public boolean addRelation(Subject subject, Competency competency) {
        return competencySubjectGraphDao.addRelation(subject, competency);
    }

    @Override
    public Competency addCompetency(Competency competency) {
        return competencyDao.save(competency);
    }

    @Override
    public Subject addSubject(Subject subject) {
        return subjectDao.save(subject);
    }

    @Override
    public void removeSubject(long subjId) {
        subjectDao.delete(subjId);
    }

    @Override
    public void removeCompetency(long compId) {
        competencyDao.delete(compId);
    }

    @Override
    public Iterable<Competency> getAllCompetencies() {
        return competencyDao.findAll();
    }

    @Override
    public Iterable<Subject> getAllSubjects() {
        return subjectDao.findAll();
    }
}
