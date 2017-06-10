package ua.nure.dl.repo.cache;

import ua.nure.dl.model.entity.RoleEntity;

public interface UserRoleCache {

    RoleEntity getRoleByName(String name);
}
