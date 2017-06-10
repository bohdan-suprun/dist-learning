package ua.nure.dl.repo.relational;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.nure.dl.model.entity.RoleEntity;

/**
 * @author Bohdan_Suprun
 */
public interface RoleDao extends JpaRepository<RoleEntity, Long> {

}
