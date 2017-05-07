package ua.nure.dl.repo.relational;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.nure.dl.model.entity.ArticleEntity;

/**
 * @author Bohdan_Suprun
 */
public interface ArticleDao extends JpaRepository<ArticleEntity, Long> {
}
