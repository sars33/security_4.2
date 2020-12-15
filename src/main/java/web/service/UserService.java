package web.service;

import web.model.Role;
import web.model.User;

import java.util.List;

public interface UserService {

    List<User> showAll();

    void addAndSave(User user);

    void delete(Long id);

    void edit(User user);

    User getById(Long id);

    User getUserByName(String login);

    Role getRoleByName(String role);
}


