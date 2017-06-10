package ua.nure.dl.competencymanager.service;

import ua.nure.dl.model.dto.Competency;
import ua.nure.dl.model.dto.Subject;
import ua.nure.dl.model.dto.SubjectMaterial;
import ua.nure.dl.model.entity.CompetencyEntity;
import ua.nure.dl.model.entity.SubjectEntity;
import ua.nure.dl.model.entity.SubjectMaterialEntity;

import java.util.Collection;

/**
 * @author Bohdan_Suprun
 */
public interface CompetencySubjectService {

    Collection<SubjectEntity> getAllSubjectsForCompetency(Competency competency);

    Collection<SubjectEntity> getAllSubjectsForCompetency(long competency);

    SubjectEntity addSubjectToCompetency(Subject subject, Competency competency);

    SubjectEntity addSubjectToCompetency(Subject subject, long compId);

    SubjectEntity addSubjectToCompetency(long subjectId, long competencyId);

    CompetencyEntity addCompetency(Competency competency);

    SubjectEntity addSubject(Subject subject);

    void removeSubject(long subjId);

    void removeCompetency(long compId);

    Collection<CompetencyEntity> getAllCompetencies();

    Collection<SubjectEntity> getAllSubjects();

    SubjectMaterialEntity addSubjectMaterial(long subjId, SubjectMaterial dto);

    Collection<SubjectMaterialEntity> getSubjectMaterial(long subjId);
}
