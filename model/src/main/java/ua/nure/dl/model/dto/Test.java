package ua.nure.dl.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Bohdan_Suprun
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Test {
    private Long id;
    private String title;
    private Integer questionNumberToAsk;
    private Double successPassRatio;
    private Subject subject;
}
