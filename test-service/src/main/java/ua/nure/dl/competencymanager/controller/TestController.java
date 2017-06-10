package ua.nure.dl.competencymanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.dl.competencymanager.service.TestFacadeService;
import ua.nure.dl.model.dto.Test;
import ua.nure.dl.model.dto.TestAnswer;
import ua.nure.dl.model.dto.TestQuestion;
import ua.nure.dl.model.dto.TestQuestionCreate;
import ua.nure.dl.model.dto.TestResult;
import ua.nure.dl.model.util.EntityToDtoConverter;

import java.util.Collection;
import java.util.List;

/**
 * @author Bohdan_Suprun
 */
@RestController
@ResponseBody
public class TestController {

    @Autowired
    private TestFacadeService testFacadeService;
    @Autowired
    private EntityToDtoConverter converter;

    @PostMapping("/test")
    public Test createTest(@RequestBody Test test) {
        return converter.toTest(testFacadeService.createTest(test));
    }

    @PostMapping("/test/{testId}")
    public TestQuestionCreate createQuestion(@PathVariable long testId, @RequestBody TestQuestionCreate ob) {
        return converter.toQuestionCreate(testFacadeService.createQuestion(testId, ob));
    }

    @GetMapping("/test/{testId}/questions")
    public Collection<TestQuestion> getQuestions(@PathVariable long testId) {
        return converter.convertMultiple(
                testFacadeService.getQuestions(testId),
                converter::toQuestion);
    }

    @PostMapping("/test/{testId}/submit")
    public TestResult submitTestAnswer(@RequestBody List<TestAnswer> answers, @PathVariable long testId) {
        return testFacadeService.submitTestAnswer(answers, testId);
    }

}
