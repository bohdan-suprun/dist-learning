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
public class TestQuestion {
    private Long questionId;
    private String text;
    private TestQuestionType questionType;
    private List<TestQuestionOption> testOptions;
}
