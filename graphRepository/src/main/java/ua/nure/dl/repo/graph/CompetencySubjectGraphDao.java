package ua.nure.dl.repo.graph;

import ua.nure.dl.model.entity.CompetencyEntity;
import ua.nure.dl.model.entity.SubjectEntity;

import java.util.Collection;

/**
 * @author Bohdan_Suprun
 */
public interface CompetencySubjectGraphDao {

    Collection<SubjectEntity> findAllSubjectsForCompetency(long competency);

    void addRelation(SubjectEntity subject, CompetencyEntity competency);

    SubjectEntity save(SubjectEntity subjectEntity);

    CompetencyEntity save(CompetencyEntity competencyEntity);
}
