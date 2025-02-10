package services.impl;

import models.Canal;
import services.CanalService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.List;

public class CanalServiceImpl implements CanalService {

    @PersistenceContext
    private final EntityManager entityManager;

    public CanalServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Canal addCanal(Canal canal) {
        try {
            entityManager.persist(canal);
            return canal;
        }
        catch (PersistenceException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void updateCanal(Canal canal) {
        try {
            entityManager.merge(canal);
        }
        catch (PersistenceException e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean deleteCanal(Long id) {
        try {
            Canal canal = entityManager.find(Canal.class, id);
            if (canal != null)
                entityManager.remove(canal);
        }
        catch (PersistenceException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Canal> getAllCanals() {
        return entityManager.createQuery("SELECT c FROM Canal c", Canal.class).getResultList();
    }

    @Override
    public Canal getCanal(Long id) {
        try {
             return entityManager.find(Canal.class, id);
        }
        catch(PersistenceException e){
            e.printStackTrace();
            return null;
        }
    }
}
