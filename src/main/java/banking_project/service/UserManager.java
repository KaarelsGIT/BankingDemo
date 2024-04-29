package banking_project.service;

import banking_project.model.User;

import javax.persistence.*;
import java.util.List;


public class UserManager {
    private final EntityManagerFactory emf;

    public UserManager() {
        emf = Persistence.createEntityManagerFactory("persistence-unit");
    }


    public void createNewUser(String fullName, String email, String phoneNumber) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        User user = new User();
        user.setFullName(fullName);
        user.setMail(email);
        user.setPhoneNumber(phoneNumber);

        em.persist(user);

        em.getTransaction().commit();
        em.close();
    }

    public User getUserByID(Long id) {
        EntityManager em = emf.createEntityManager();
        User product = em.find(User.class, id);
        em.close();
        return product;
    }

    public void updateUser(User user) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        em.merge(user);

        em.getTransaction().commit();
        em.close();
    }

    public void deleteUser(User user) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        user = em.merge(user);
        em.remove(user);

        em.getTransaction().commit();
        em.close();
    }

    public List<User> getAllUsers() {
        EntityManager em = emf.createEntityManager();
        List<User> users = em.createQuery("SELECT u FROM User u", User.class).getResultList();
        em.close();
        return users;
    }

    public void close() {
        emf.close();
    }
}
