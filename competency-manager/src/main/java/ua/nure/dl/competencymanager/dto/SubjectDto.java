package ua.nure.dl.competencymanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Bohdan_Suprun
 */
@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class SubjectDto {

    private long id;
    private String name;
}
