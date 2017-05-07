package ua.nure.dl.subjmanager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import ua.nure.dl.model.dto.Article;
import ua.nure.dl.model.entity.ArticleEntity;
import ua.nure.dl.model.entity.SubjectEntity;
import ua.nure.dl.model.entity.UserEntity;
import ua.nure.dl.repo.relational.ArticleDao;
import ua.nure.dl.subjmanager.service.ArticleService;

import java.util.stream.Collectors;

/**
 * @author Bohdan_Suprun
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleDao articleDao;

    @Override
    public Iterable<Article> findAllArticlesForSubject(long subjectId) {
        ArticleEntity articleEntity = new ArticleEntity();
        articleEntity.setArticleSubject(new SubjectEntity(subjectId, null));

        return articleDao.findAll(Example.of(articleEntity))
                .stream().map(this::convert).collect(Collectors.toList());
    }

    @Override
    public Article getArticle(long id) {
        ArticleEntity articleEntity = articleDao.findOne(id);
        Article article = convert(articleEntity);

        return article.isHidden()
                ? null
                : article;
    }

    @Override
    public Article createArticle(Article article) {
        return convert(articleDao.save(convert(article)));
    }

    @Override
    public Article hideArticle(long id) {
        Article article = getArticle(id);
        article.setHidden(true);

        return convert(articleDao.save(convert(article)));
    }

    private Article convert(ArticleEntity articleEntity) {
        return new Article(articleEntity.getId(), articleEntity.getTitle(),
                articleEntity.getArticleSubject().getName(), articleEntity.getArticleSubject().getId(),
                articleEntity.getText(), articleEntity.isHidden(),
                articleEntity.getArticleAuthor().getFullName(), articleEntity.getArticleAuthor().getId());
    }

    private ArticleEntity convert(Article article) {
        return new ArticleEntity(article.getId(), article.getTitle(),
                new SubjectEntity(article.getSubjectId(), article.getSubjectName()),
                article.getText(), article.isHidden(),
                new UserEntity(article.getSubjectId(), article.getAuthorName(), null));
    }
}
