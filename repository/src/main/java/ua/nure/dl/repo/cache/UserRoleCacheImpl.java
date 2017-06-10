package ua.nure.dl.repo.cache;

import org.springframework.beans.factory.annotation.Autowired;
import ua.nure.dl.model.entity.RoleEntity;
import ua.nure.dl.repo.relational.RoleDao;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Set;

/**
 * @author Bohdan_Suprun
 */
public class UserRoleCacheImpl implements UserRoleCache {

    private List<RoleEntity> roles;
    private Set<RoleEntity> defaultRoles;
    @Autowired
    private RoleDao roleDao;

    @PostConstruct
    protected void init() {
        List<RoleEntity> roleEntities = roleDao.findAll();
        System.out.println("Found roles: " + roleEntities);

        if (roleEntities.isEmpty()) {
            System.out.println("Going to load roles: " + defaultRoles);
            roleDao.save(defaultRoles);
        }

        roles = roleDao.findAll();
        System.out.println("Finally initialized:" + roles);
    }

    @Autowired
    public void setDefaultRoles(Set<RoleEntity> defaultRoles) {
        this.defaultRoles = defaultRoles;
    }

    @Override
    public RoleEntity getRoleByName(String name) {

        for (RoleEntity roleEntity : roles) {
            if (roleEntity.getName().equalsIgnoreCase(name)) {
                return roleEntity;
            }
        }

        return null;
    }
}
