package services.impl;

import models.Groupe;
import services.GroupeService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.Collections;
import java.util.List;

public class GroupeServiceImpl implements GroupeService {
    @PersistenceContext
    private final EntityManager entityManager;

    public GroupeServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Groupe addGroupe(Groupe groupe) {
        try{
            entityManager.persist(groupe);
            return groupe;
        }
        catch (PersistenceException e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public boolean deleteGroupe(Long id) {
        Groupe groupe = entityManager.find(Groupe.class, id);
        if (groupe != null)
            entityManager.remove(groupe);
        return false;
    }

    @Override
    public void updateGroupe(Groupe groupe) {
        try {
            entityManager.merge(groupe);
        }
        catch (PersistenceException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Groupe> getAllGroupes() {
        try {
            return entityManager.createQuery("SELECT g FROM Groupe g", Groupe.class).getResultList();
        }
        catch (PersistenceException e){
            return Collections.emptyList();
        }

    }

    @Override
    public Groupe getGroupe(Long id) {
        try{
            return entityManager.find(Groupe.class, id);
        }catch (PersistenceException e){
            return (Groupe) Collections.emptyList();
        }

    }
}
