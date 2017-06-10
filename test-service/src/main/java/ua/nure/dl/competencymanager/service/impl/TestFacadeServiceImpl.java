package ua.nure.dl.competencymanager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.nure.dl.competencymanager.service.TestFacadeService;
import ua.nure.dl.model.dto.Test;
import ua.nure.dl.model.dto.TestAnswer;
import ua.nure.dl.model.dto.TestQuestionCreate;
import ua.nure.dl.model.dto.TestQuestionOptionCreate;
import ua.nure.dl.model.dto.TestResult;
import ua.nure.dl.model.entity.Journal;
import ua.nure.dl.model.entity.SubjectEntity;
import ua.nure.dl.model.entity.TestEntity;
import ua.nure.dl.model.entity.TestQuestionEntity;
import ua.nure.dl.model.entity.TestQuestionOptionEntity;
import ua.nure.dl.model.entity.TestQuestionType;
import ua.nure.dl.repo.relational.SubjectDao;
import ua.nure.dl.repo.relational.TestDao;
import ua.nure.dl.repo.relational.TestQuestionDao;
import ua.nure.dl.repo.relational.TestQuestionOptionDao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Bohdan_Suprun
 */
@Service
@SuppressWarnings("unchecked")
public class TestFacadeServiceImpl implements TestFacadeService {

    @Autowired
    private TestDao testDao;
    @Autowired
    private TestQuestionDao testQuestionDao;
    @Autowired
    private TestQuestionOptionDao testQuestionOptionDao;
    @Autowired
    private SubjectDao subjectDao;

    @Override
    public TestEntity createTest(Test test) {
        SubjectEntity subjectEntity = subjectDao.findOne(test.getSubject().getId());
        TestEntity testEntity = new TestEntity(0L, test.getTitle(), test.getQuestionNumberToAsk(),
                test.getSuccessPassRatio(), subjectEntity, Collections.emptyList());

        return testDao.save(testEntity);
    }

    @Override
    public TestQuestionEntity createQuestion(long testId, TestQuestionCreate ob) {
        TestEntity testEntity = testDao.findOne(testId);
        TestQuestionEntity testQuestion = new TestQuestionEntity(0L, testEntity, ob.getText(), TestQuestionType.ONE_ANSWER,
                ob.getMark(), Collections.emptyList());

        testQuestion = testQuestionDao.save(testQuestion);

        List<TestQuestionOptionEntity> options = new ArrayList<>();
        boolean correctFound = false;

        for (TestQuestionOptionCreate opt : ob.getTestOptions()) {
            if (opt.getCorrect() != null && opt.getCorrect()) {
                correctFound = true;
            }

            options.add(new TestQuestionOptionEntity(0L, testQuestion, opt.getText(), opt.getCorrect()));
        }

        if (!correctFound) {
            throw new IllegalArgumentException("No correct answer was detected");
        }

        options = testQuestionOptionDao.save(options);
        testQuestion.setTestOptions(options);

        return testQuestion;
    }

    @Override
    public Collection<TestQuestionEntity> getQuestions(long testId) {
        TestEntity testEntity = testDao.findOne(testId);

        return testEntity.getTestQuestions();
    }

    @Override
    public TestResult submitTestAnswer(List<TestAnswer> answers, long testId) {
        TestEntity testEntity = testDao.findOne(testId);
        Journal journal = new Journal();
        journal.setMark(calculateMark(answers, testEntity));

        double ratio = journal.getMark() / calculateTotalMark(testEntity);

        return new TestResult(journal.getMark(), ratio >= testEntity.getSuccessPassRatio());
    }

    private Double calculateMark(List<TestAnswer> answers, TestEntity testEntity) {
        Map<Long, Long> questionToCorrectAnswerMap = buildQuestionToCorrectAnswerMap(testEntity);
        Map<Long, Double> answerToMarkMap = buildAnswerToMarkMap(testEntity);

        double totalMark = 0.0D;
        for (TestAnswer answer : answers) {
            Long correctId = questionToCorrectAnswerMap.get(answer.getQuestionId());
            Double mark = answerToMarkMap.get(answer.getQuestionId());

            if (correctId.equals(answer.getOptionSequence())) {
                totalMark += mark;
            }
        }

        return totalMark;
    }

    private Double calculateTotalMark(TestEntity testEntity) {
        Map<Long, Double> answerToMarkMap = buildAnswerToMarkMap(testEntity);
        return answerToMarkMap
                .values()
                .stream()
                .mapToDouble(Double::doubleValue)
                .sum();
    }

    private Map<Long, Long> buildQuestionToCorrectAnswerMap(TestEntity testEntity) {
        Map<Long, Long> map = new HashMap<>();
        for (TestQuestionEntity question : testEntity.getTestQuestions()) {
            for (TestQuestionOptionEntity option : question.getTestOptions()) {
                if (option.getCorrect() != null && option.getCorrect()) {
                    map.put(question.getId(), option.getSequence());
                }
            }
        }

        return map;
    }

    private Map<Long, Double> buildAnswerToMarkMap(TestEntity testEntity) {
        Map<Long, Double> map = new HashMap<>();
        for (TestQuestionEntity question : testEntity.getTestQuestions()) {
            Double mark = question.getMark() == null
                    ? 1.0D
                    : question.getMark();

            map.put(question.getId(), mark);
        }

        return map;
    }
}
