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
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Bohdan_Suprun
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TestQuestion")
public class TestQuestionOptionEntity implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sequence;
    @ManyToOne
    @JoinColumn(name = "testQuestionId", nullable = false)
    private TestQuestionEntity testQuestionOption;

    private String text;
    private Boolean correct;

}
