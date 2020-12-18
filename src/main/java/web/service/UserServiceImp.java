package web.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.model.Role;
import web.model.User;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    private final UserDao userDao;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImp(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public List<User> showAll() {
        return userDao.showAll();
    }

    @Transactional
    @Override
    public void addAndSave(User user) {
        user.setPassword((passwordEncoder.encode(user.getPassword())));
        userDao.addAndSave(user);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        userDao.delete(id);
    }

    @Transactional
    @Override
    public void edit(User user) {

        if (!user.getPassword().matches(user.getPassword()) || user.getPassword().isEmpty()) {
            user.setPassword(user.getPassword());
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userDao.edit(user);
        }
    }

    @Transactional
    @Override
    public User getById(Long id) {
        return userDao.getById(id);
    }

    @Override
    public User getUserByName(String login) {
        return userDao.getUserByName(login);
    }

    @Override
    public Role getRoleByName(String role) {
        return userDao.getRoleByName(role);
    }
}



