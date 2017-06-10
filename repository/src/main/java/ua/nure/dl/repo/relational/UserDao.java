package ua.nure.dl.repo.relational;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.nure.dl.model.entity.UserEntity;

/**
 * @author Bohdan_Suprun
 */
public interface UserDao extends JpaRepository<UserEntity, Long> {

}
