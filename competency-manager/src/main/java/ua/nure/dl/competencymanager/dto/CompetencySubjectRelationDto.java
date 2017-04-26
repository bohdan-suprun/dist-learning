package ua.nure.dl.competencymanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Bohdan_Suprun
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompetencySubjectRelationDto {

    private CompetencyDto competency;
    private SubjectDto subject;
}
