package services;

import models.User;
import java.util.List;

public interface UserService {

     List<User> getUsers();
     User getUser(Long id);
     void createUser(User user);
     void updateUser(User user);
    boolean deleteUser(Long id);
}
