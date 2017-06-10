package ua.nure.dl.competencymanager.service;

import ua.nure.dl.model.dto.Test;
import ua.nure.dl.model.dto.TestAnswer;
import ua.nure.dl.model.dto.TestQuestionCreate;
import ua.nure.dl.model.dto.TestResult;
import ua.nure.dl.model.entity.TestEntity;
import ua.nure.dl.model.entity.TestQuestionEntity;

import java.util.Collection;
import java.util.List;

/**
 * @author Bohdan_Suprun
 */
public interface TestFacadeService {


    TestEntity createTest(Test test);

    TestQuestionEntity createQuestion(long testId, TestQuestionCreate ob);

    Collection<TestQuestionEntity> getQuestions(long testId);

    TestResult submitTestAnswer(List<TestAnswer> answers, long testId);

}
