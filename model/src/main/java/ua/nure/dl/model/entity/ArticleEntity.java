package ua.nure.dl.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author Bohdan_Suprun
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Article")
public class ArticleEntity {

    @Id
    @GeneratedValue
    private long id;
    private String title;
    @OneToOne
    private SubjectEntity articleSubject;
    private String text;
    private boolean hidden;
    @OneToOne
    private UserEntity articleAuthor;
}
