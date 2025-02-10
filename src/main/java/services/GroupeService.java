package services;

import models.Groupe;

import java.util.List;

public interface GroupeService {
    Groupe addGroupe(Groupe groupe);
    boolean deleteGroupe(Long id);
    void updateGroupe(Groupe groupe);
    List<Groupe> getAllGroupes();
    Groupe getGroupe(Long id);
}
