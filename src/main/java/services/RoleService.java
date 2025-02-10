package services;

import models.Role;

import java.util.List;

public interface RoleService {
    Role addRole(Role role);
    void deleteRole(Long id);
    void updateRole(Role role);
    List<Role> getRoles();
    Role getRole(Long id);
}
