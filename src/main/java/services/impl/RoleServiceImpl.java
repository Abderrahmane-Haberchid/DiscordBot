package services.impl;

import models.Role;
import services.RoleService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class RoleServiceImpl implements RoleService {

    @PersistenceContext
    private final EntityManager entityManager;

    public RoleServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Role addRole(Role role) {
         entityManager.persist(role);
         return role;
    }

    @Override
    public void deleteRole(Long id) {
        Role role = entityManager.find(Role.class, id);
        entityManager.remove(role);
    }

    @Override
    public void updateRole(Role role) {
        entityManager.merge(role);
    }

    @Override
    public List<Role> getRoles() {
        return entityManager.createQuery("SELECT r FROM Role r", Role.class).getResultList();
    }

    @Override
    public Role getRole(Long id) {
        return entityManager.find(Role.class, id);
    }
}
