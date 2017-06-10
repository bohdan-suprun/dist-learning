package ua.nure.dl.competencymanager.service;

import ua.nure.dl.model.dto.ArticleCreate;
import ua.nure.dl.model.dto.UserRegistryDto;
import ua.nure.dl.model.entity.ArticleEntity;
import ua.nure.dl.model.entity.CompetencyEntity;
import ua.nure.dl.model.entity.SubjectEntity;
import ua.nure.dl.model.entity.TestEntity;
import ua.nure.dl.model.entity.UserEntity;

import java.util.Collection;

/**
 * @author Bohdan_Suprun
 */
public interface UserFacadeService {

    boolean login(String login, String password);

    UserEntity getByEmail(String login);

    UserEntity registry(UserRegistryDto user);

    ArticleEntity createArticle(long userId, ArticleCreate article);

    Collection<CompetencyEntity> getCompetencies(long userId);

    Collection<SubjectEntity> getSubjects(long userId);

    Collection<TestEntity> getTests(long userId);

    Collection<ArticleEntity> getArticles(long userId);

    void subscribe(long userId, long cId);
}
