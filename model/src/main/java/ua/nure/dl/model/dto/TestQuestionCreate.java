package ua.nure.dl.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.nure.dl.model.entity.TestQuestionType;

import java.util.List;

/**
 * @author Bohdan_Suprun
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestQuestionCreate {
    private String text;
    private TestQuestionType questionType;
    private Double mark;
    private List<TestQuestionOptionCreate> testOptions;
}
