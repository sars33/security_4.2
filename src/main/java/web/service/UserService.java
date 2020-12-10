package web.service;

import web.model.User;

import java.util.List;

public interface UserService {

    List<User> showAll();

    void addAndSave(User user);

    void delete(Long id);

    void edit(User user);

    User getById(Long id);
}


