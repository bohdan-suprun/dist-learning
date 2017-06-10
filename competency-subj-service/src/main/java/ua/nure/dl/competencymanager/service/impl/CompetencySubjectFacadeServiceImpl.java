package ua.nure.dl.competencymanager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.nure.dl.competencymanager.service.CompetencySubjectService;
import ua.nure.dl.model.dto.Competency;
import ua.nure.dl.model.dto.Subject;
import ua.nure.dl.model.dto.SubjectMaterial;
import ua.nure.dl.model.entity.CompetencyEntity;
import ua.nure.dl.model.entity.SubjectEntity;
import ua.nure.dl.model.entity.SubjectMaterialEntity;
import ua.nure.dl.repo.relational.CompetencyDao;
import ua.nure.dl.repo.relational.SubjectDao;
import ua.nure.dl.repo.relational.SubjectMaterialDao;

import java.util.Collection;
import java.util.Collections;

/**
 * @author Bohdan_Suprun
 */
@Service
public class CompetencySubjectFacadeServiceImpl implements CompetencySubjectService {

    @Autowired
    private CompetencyDao competencyDao;
    @Autowired
    private SubjectDao subjectDao;
    @Autowired
    private SubjectMaterialDao subjectMaterialDao;

    @Override
    public Collection<SubjectEntity> getAllSubjectsForCompetency(Competency competency) {
        return getAllSubjectsForCompetency(competency.getId());
    }

    @Override
    public Collection<SubjectEntity> getAllSubjectsForCompetency(long competency) {
        CompetencyEntity competencyEntity = competencyDao.findOne(competency);

        return competencyEntity.getSubjects();
    }

    @Override
    public SubjectEntity addSubjectToCompetency(Subject subject, Competency competency) {
        return addSubjectToCompetency(subject.getId(), competency.getId());
    }

    @Override
    public SubjectEntity addSubjectToCompetency(Subject subject, long compId) {
        return addSubjectToCompetency(subject.getId(), compId);
    }

    public SubjectEntity addSubjectToCompetency(long subjectId, long competencyId) {
        CompetencyEntity competencyEntity = competencyDao.findOne(competencyId);
        SubjectEntity subjectEntity = subjectDao.findOne(subjectId);

        competencyEntity.getSubjects().add(subjectEntity);
        competencyDao.save(competencyEntity);

        return subjectEntity;
    }

    @Override
    public CompetencyEntity addCompetency(Competency competency) {
        CompetencyEntity competencyEntity = new CompetencyEntity(0L, competency.getName(), Collections.emptyList(),
                Collections.emptyList());

        return competencyDao.save(competencyEntity);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public SubjectEntity addSubject(Subject subject) {
        SubjectEntity subjectEntity = new SubjectEntity(0L, subject.getName(),
                Collections.emptyList(), Collections.emptyList(), Collections.emptyList());

        return subjectDao.save(subjectEntity);
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
    public Collection<CompetencyEntity> getAllCompetencies() {
        return competencyDao.findAll();
    }

    @Override
    public Collection<SubjectEntity> getAllSubjects() {
        return subjectDao.findAll();
    }

    @Override
    public SubjectMaterialEntity addSubjectMaterial(long subjId, SubjectMaterial dto) {
        SubjectEntity subjectEntity = subjectDao.findOne(subjId);
        SubjectMaterialEntity entity = new SubjectMaterialEntity(0L, dto.getTitle(), dto.getMaterial(), subjectEntity);

        return subjectMaterialDao.save(entity);
    }

    @Override
    public Collection<SubjectMaterialEntity> getSubjectMaterial(long subjId) {
        SubjectEntity subjectEntity = subjectDao.findOne(subjId);

        return subjectEntity.getMaterials();
    }
}
