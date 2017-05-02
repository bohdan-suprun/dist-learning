package ua.nure.dl.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * @author Bohdan_Suprun
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
//@NodeEntity(label = "Subject")
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = "name") }, name = "Subject")
public class SubjectEntity {

//	@GraphId
	@Id
	@GeneratedValue
	private Long id;
	private String name;
}
