package ua.nure.dl.competencymanager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import ua.nure.dl.competencymanager.service.UserFacadeService;
import ua.nure.dl.model.dto.ArticleCreate;
import ua.nure.dl.model.dto.UserRegistryDto;
import ua.nure.dl.model.entity.ArticleEntity;
import ua.nure.dl.model.entity.CompetencyEntity;
import ua.nure.dl.model.entity.SubjectEntity;
import ua.nure.dl.model.entity.TestEntity;
import ua.nure.dl.model.entity.UserEntity;
import ua.nure.dl.repo.cache.UserRoleCache;
import ua.nure.dl.repo.relational.ArticleDao;
import ua.nure.dl.repo.relational.CompetencyDao;
import ua.nure.dl.repo.relational.SubjectDao;
import ua.nure.dl.repo.relational.UserDao;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Bohdan_Suprun
 */
@Service
@SuppressWarnings("unchecked")
public class UserFacadeServiceImpl implements UserFacadeService {

    @Autowired
    private CompetencyDao competencyDao;
    @Autowired
    private SubjectDao subjectDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private UserRoleCache userRoleCache;


    @Override
    public boolean login(String login, String password) {
        return getByEmail(login).getPassword().equals(password);
    }

    @Override
    public UserEntity getByEmail(String login) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(login);

        List<UserEntity> users = userDao.findAll(Example.of(userEntity));
        if (users.size() > 1) {
            throw new IllegalArgumentException("Illegal login");
        }

        return users.get(0);
    }

    @Override
    public UserEntity registry(UserRegistryDto user) {
        UserEntity userEntity = new UserEntity(0L, user.getEmail(), user.getName(),
                user.getPassword(), userRoleCache.getRoleByName(user.getRole()), Collections.emptyList());

        return userDao.save(userEntity);
    }

    @Override
    public ArticleEntity createArticle(long userId, ArticleCreate article) {
        UserEntity user = userDao.findOne(userId);
        CompetencyEntity competency = competencyDao.findOne(article.getTargetCompetency());

        ArticleEntity articleEntity = new ArticleEntity(0L, article.getTitle(), article.getText(), user, competency,
                new Date());

        return articleDao.save(articleEntity);
    }

    @Override
    public Collection<CompetencyEntity> getCompetencies(long userId) {
        UserEntity user = userDao.findOne(userId);

        return user.getCompetencies();
    }

    @Override
    public Collection<SubjectEntity> getSubjects(long userId) {
        UserEntity user = userDao.findOne(userId);
        Set<SubjectEntity> subjects = new HashSet<>();
        for (CompetencyEntity c : user.getCompetencies()) {
            subjects.addAll(c.getSubjects());
        }

        return subjects;
    }

    @Override
    public Collection<TestEntity> getTests(long userId) {
        UserEntity user = userDao.findOne(userId);
        Set<TestEntity> tests = new HashSet<>();

        for (CompetencyEntity c : user.getCompetencies()) {
            for (SubjectEntity s : c.getSubjects()) {
                tests.addAll(s.getTests());
            }
        }

        return tests;
    }

    @Override
    public Collection<ArticleEntity> getArticles(long userId) {
        UserEntity user = userDao.findOne(userId);
        Set<ArticleEntity> articles = new HashSet<>();

        for (CompetencyEntity c : user.getCompetencies()) {
            articles.addAll(c.getArticles());
        }

        return articles;
    }

    @Override
    public void subscribe(long userId, long cId) {
        UserEntity user = userDao.findOne(userId);
        CompetencyEntity competency = competencyDao.findOne(cId);
        user.getCompetencies().add(competency);

        userDao.save(user);
    }
}
