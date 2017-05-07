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
@Table(name = "User",
        uniqueConstraints = {@UniqueConstraint(columnNames = "email")}
)
public class UserEntity {

    @Id
    @GeneratedValue
    private long id;
    private String email;
    private String fullName;
}
