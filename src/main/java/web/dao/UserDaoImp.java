package web.dao;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.Role;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> showAll() {
        return entityManager.createQuery("From User").getResultList();
    }

    @Override
    public void addAndSave(User user) {
        entityManager.persist(user);
    }

    @Override
    public void delete(Long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    public void edit(User user) {

                entityManager.merge(user);
            }



    @Override
    public User getById(Long id) {
        return entityManager.find(User.class, id);

    }

    @Override
    public User getUserByName(String login) {
        return entityManager.createQuery("from User where login = :login", User.class).setParameter("login", login).getSingleResult();
    }

    @Override
    public Role getRoleByName(String role) {
        return entityManager.createQuery("from Role where role =:role", Role.class).setParameter("role", role).getSingleResult();
    }
}
