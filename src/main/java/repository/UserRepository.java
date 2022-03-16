package repository;

import model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class UserRepository {

    private EntityManagerFactory entityManagerFactory;

    public UserRepository (EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public void getAllUsers (User user) {                                       // will use this for login
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        List<User> users = em.createQuery("SELECT u FROM User u", User.class)
                .getResultList();
        em.getTransaction().commit();
        em.close();
    }

    public User findUser (String username, String password) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        User user = em.createQuery("SELECT u FROM User u WHERE u.username LIKE : usname AND u.password LIKE : pass", User.class)
                .setParameter("usname", username)
                .setParameter("pass", password)
                .getSingleResult();
        em.getTransaction().commit();
        em.close();

        return user;
    }

    public void insertUser (User user) {                                       //will use this for registering
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.close();
    }

    public void updateUser (User user) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
        em.close();
    }

}