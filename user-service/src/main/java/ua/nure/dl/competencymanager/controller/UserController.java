package ua.nure.dl.competencymanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.dl.competencymanager.service.UserFacadeService;
import ua.nure.dl.model.dto.Article;
import ua.nure.dl.model.dto.ArticleCreate;
import ua.nure.dl.model.dto.Competency;
import ua.nure.dl.model.dto.Subject;
import ua.nure.dl.model.dto.Test;
import ua.nure.dl.model.dto.User;
import ua.nure.dl.model.dto.UserLogin;
import ua.nure.dl.model.dto.UserRegistryDto;
import ua.nure.dl.model.util.EntityToDtoConverter;

import java.util.Collection;

/**
 * @author Bohdan_Suprun
 */
@RestController
@ResponseBody
public class UserController {

    @Autowired
    private UserFacadeService userFacadeService;
    @Autowired
    private EntityToDtoConverter converter;

    @PostMapping("/login")
    public User login(@RequestBody UserLogin login) {
        User user = null;

        if (userFacadeService.login(login.getEmail(), login.getPassword())) {
            user = converter.toUser(userFacadeService.getByEmail(login.getEmail()));
        }

        return user;
    }

    @PostMapping("/registry")
    public User registry(@RequestBody UserRegistryDto user) {
        return converter.toUser(userFacadeService.registry(user));

    }

    @GetMapping("/user/{userId}/competencies")
    public Collection<Competency> getCompetencies(@PathVariable long userId) {
        return converter.convertMultiple(
                userFacadeService.getCompetencies(userId),
                converter::toCompetency
        );
    }

    @GetMapping("/user/{userId}/subjects")
    public Collection<Subject> getSubjects(@PathVariable long userId) {
        return converter.convertMultiple(
                userFacadeService.getSubjects(userId),
                converter::toSubject
        );
    }

    @GetMapping("/user/{userId}/tests")
    public Collection<Test> getTests(@PathVariable long userId) {
        return converter.convertMultiple(
                userFacadeService.getTests(userId),
                converter::toTest
        );
    }

    @GetMapping("/user/{userId}/articles")
    public Collection<Article> getArticles(@PathVariable long userId) {
        return converter.convertMultiple(
                userFacadeService.getArticles(userId),
                converter::toArticle
        );

    }

    @PostMapping("/user/{userId}/article")
    public Article createArticle(@PathVariable long userId,
                                 @RequestBody ArticleCreate article) {

        return converter.toArticle(userFacadeService.createArticle(userId, article));
    }

    @PostMapping("/user/{userId}/competency/{cid}")
    public void subscribe(@PathVariable long userId,
                          @PathVariable long cId) {

        userFacadeService.subscribe(userId, cId);
    }
}
