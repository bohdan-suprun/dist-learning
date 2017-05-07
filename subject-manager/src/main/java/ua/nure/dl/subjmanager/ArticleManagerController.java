package ua.nure.dl.subjmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.nure.dl.model.dto.Article;
import ua.nure.dl.subjmanager.service.ArticleService;

/**
 * @author is_competent
 */
@RestController
@ResponseBody
@RequestMapping("/article")
public class ArticleManagerController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/{id}")
    public Article getArticle(@PathVariable long id) {
        return articleService.getArticle(id);
    }

    @PostMapping
    public Article createArticle(@RequestBody Article article) {
        return articleService.createArticle(article);
    }

    @DeleteMapping("/{id}")
    public Article hideArticle(@PathVariable long id) {
        return articleService.hideArticle(id);
    }
}
