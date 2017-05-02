package ua.nure.dl.competencymanager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.nure.dl.competencymanager.service.CompetencySubjectService;
import ua.nure.dl.model.dto.Competency;
import ua.nure.dl.model.dto.Subject;
import ua.nure.dl.model.entity.CompetencyEntity;
import ua.nure.dl.model.entity.SubjectEntity;
import ua.nure.dl.repo.graph.CompetencySubjectGraphDao;
import ua.nure.dl.repo.relational.CompetencyDao;
import ua.nure.dl.repo.relational.SubjectDao;
import ua.nure.dl.repo.util.EntityToDtoConverter;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Bohdan_Suprun
 */
@Service
@SuppressWarnings("unchecked")
public class CompetencySubjectServiceImpl implements CompetencySubjectService {

    @Autowired
    private CompetencyDao competencyDao;
    @Autowired
    private SubjectDao subjectDao;
    @Autowired
    private CompetencySubjectGraphDao competencySubjectGraphDao;
    @Autowired
    private EntityToDtoConverter converter;

    @Override
    public Collection<Subject> getAllSubjectsForCompetency(Competency competency) {
        return getAllSubjectsForCompetency(competency.getId());
    }

    @Override
    public Collection<Subject> getAllSubjectsForCompetency(long competency) {
        Collection<Subject> result = new ArrayList<>();

        for (SubjectEntity entry : competencySubjectGraphDao
                .findAllSubjectsForCompetency(competency)) {
            result.add(converter.convert(entry, Subject.class));
        }

        return result;
    }

    @Override
    public void addRelation(Subject subject, Competency competency) {
        addRelation(subject.getId(), competency.getId());
    }

    @Override
    public void addRelation(Subject subject, long compId) {
        addRelation(subject.getId(), compId);
    }

    @Override
    public void addRelation(long subjectId, long competencyId) {
        SubjectEntity subjectEntity = subjectDao.findOne(subjectId);
        CompetencyEntity competencyEntity = competencyDao.findOne(competencyId);

        competencySubjectGraphDao.save(subjectEntity);
        competencySubjectGraphDao.save(competencyEntity);

        competencySubjectGraphDao.addRelation(subjectEntity, competencyEntity);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Competency addCompetency(Competency competency) {
        return converter.convert(competencyDao.save(converter.convert(competency, CompetencyEntity.class)),
                Competency.class);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Subject addSubject(Subject subject) {
        return converter.convert(subjectDao.save(converter.convert(subject, SubjectEntity.class)),
                Subject.class);
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
        return converter.convert(competencyDao.findAll(), Iterable.class);
    }

    @Override
    public Iterable<Subject> getAllSubjects() {
        return converter.convert(competencyDao.findAll(), Iterable.class);
    }
}
