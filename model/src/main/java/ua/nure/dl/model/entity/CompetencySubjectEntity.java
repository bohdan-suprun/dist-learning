package ua.nure.dl.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Bohdan_Suprun
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompetencySubjectEntity {

    private CompetencyEntity competencyEntity;
    private SubjectEntity subjectEntity;
}
