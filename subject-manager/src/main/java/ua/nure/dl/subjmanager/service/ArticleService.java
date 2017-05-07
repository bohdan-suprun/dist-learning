package ua.nure.dl.subjmanager.service;

import ua.nure.dl.model.dto.Article;

import java.util.List;

/**
 * @author Bohdan_Suprun
 */
public interface ArticleService {

    Iterable<Article> findAllArticlesForSubject(long subjectId);

    Article getArticle(long id);

    Article createArticle(Article article);

    Article hideArticle(long id);
}
