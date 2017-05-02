package ua.nure.dl.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Bohdan_Suprun
 */
@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class Subject {

    private Long id;
    private String name;
}
