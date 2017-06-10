package ua.nure.dl.model.util;

import ua.nure.dl.model.dto.Article;
import ua.nure.dl.model.dto.Competency;
import ua.nure.dl.model.dto.Subject;
import ua.nure.dl.model.dto.SubjectMaterial;
import ua.nure.dl.model.dto.Test;
import ua.nure.dl.model.dto.TestQuestion;
import ua.nure.dl.model.dto.TestQuestionCreate;
import ua.nure.dl.model.dto.TestQuestionOption;
import ua.nure.dl.model.dto.TestQuestionOptionCreate;
import ua.nure.dl.model.dto.User;
import ua.nure.dl.model.entity.ArticleEntity;
import ua.nure.dl.model.entity.CompetencyEntity;
import ua.nure.dl.model.entity.SubjectEntity;
import ua.nure.dl.model.entity.SubjectMaterialEntity;
import ua.nure.dl.model.entity.TestEntity;
import ua.nure.dl.model.entity.TestQuestionEntity;
import ua.nure.dl.model.entity.UserEntity;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class EntityToDtoConverter {

    public Subject toSubject(SubjectEntity subjectEntity) {
        return new Subject(subjectEntity.getId(), subjectEntity.getName());
    }

    public Competency toCompetency(CompetencyEntity competencyEntity) {
        return new Competency(competencyEntity.getId(), competencyEntity.getName());
    }

    public <E, D> List<D> convertMultiple(Collection<E> entities, Function<E, D> converter) {
        return entities
                .stream()
                .map(converter)
                .collect(Collectors.toList());
    }

    public SubjectMaterial toSubjectMaterial(SubjectMaterialEntity e) {
        return new SubjectMaterial(e.getId(), e.getTitle(), e.getMaterial());
    }

    public Article toSubjectMaterial(ArticleEntity e) {
        return new Article(e.getId(), e.getTitle(), e.getText(), e.getOwner().getName(), e.getCreateTime(),
                e.getTargetCompetency().getName());
    }

    public User toUser(UserEntity e) {
        return new User(e.getId(), e.getEmail(), e.getName(), e.getRole().getName());
    }

    public Test toTest(TestEntity e) {
        return new Test(e.getId(), e.getTitle(), e.getQuestionNumberToAsk(), e.getSuccessPassRatio(),
                toSubject(e.getSubjectEntity()));
    }

    public Article toArticle(ArticleEntity e) {
        return new Article(e.getId(), e.getTitle(), e.getText(), e.getOwner().getName(), e.getCreateTime(),
                e.getTargetCompetency().getName());
    }

    public TestQuestion toQuestion(TestQuestionEntity question) {
        return new TestQuestion();
    }

    public TestQuestionCreate toQuestionCreate(TestQuestionEntity question) {
        return new TestQuestionCreate();
    }

    public TestQuestionOptionCreate toQuestionOptionCreate(TestQuestionEntity question) {
        return new TestQuestionOptionCreate();
    }

    public TestQuestionOption toQuestionOption(TestQuestionEntity question) {
        return new TestQuestionOption();
    }
}
