package ua.nure.dl.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Bohdan_Suprun
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompetencySubjectRelation {

    private Competency competency;
    private Subject subject;
}
