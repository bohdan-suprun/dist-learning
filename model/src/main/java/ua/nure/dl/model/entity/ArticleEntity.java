package ua.nure.dl.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import java.util.Date;

/**
 * @author Bohdan_Suprun
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "title")},
        name = "Article")
public class ArticleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String title;
    @Lob
    private String text;

    @ManyToOne
    @JoinColumn(name = "ownerId", nullable = false)
    private UserEntity owner;

    @ManyToOne
    @JoinColumn(name = "targetCompetencyId", nullable = false)
    private CompetencyEntity targetCompetency;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date createTime;
}
