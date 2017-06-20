package ua.nure.dl.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Collection;

/**
 * @author Bohdan_Suprun
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TestQuestion")
public class TestQuestionEntity implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "testId", nullable = false)
    private TestEntity test;

    private String text;
    private TestQuestionType questionType;
    private Double mark;
    @OneToMany(mappedBy = "testQuestionOption")
    private Collection<TestQuestionOptionEntity> testOptions;
}
