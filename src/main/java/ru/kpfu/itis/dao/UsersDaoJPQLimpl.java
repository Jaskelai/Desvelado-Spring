package ru.kpfu.itis.dao;

import org.springframework.stereotype.Repository;
import ru.kpfu.itis.model.User;

import javax.persistence.*;
import java.util.List;

@Repository
public class UsersDaoJPQLimpl implements UsersDao {
    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @Override
    public List<User> findAll(int limit, int offset) {
        EntityManager em = entityManagerFactory.createEntityManager();
        String queryFind = "SELECT u FROM User u";
        Query query = em.createQuery(queryFind);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.getResultList();
    }

    @Override
    public User find(Integer id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        String queryFind = "SELECT u FROM User u WHERE u.id = :id";
        Query query = em.createQuery(queryFind);
        query.setParameter("id", id);
        return (User) query.getSingleResult();
    }

    @Override
    public void save(User model) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(model);
        entityManager.flush();
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(User model) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        String queryUpdate = "UPDATE User u SET u.email = :email";
        Query query = em.createQuery(queryUpdate);
        query.setParameter("email", model.getEmail());
        query.executeUpdate();
        em.flush();
        em.getTransaction().commit();
    }

    @Override
    public void delete(User model) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        String queryDelete = "DELETE FROM User u WHERE u.username = :username";
        Query query = em.createQuery(queryDelete);
        query.setParameter("username", model.getUsername());
        query.executeUpdate();
        em.flush();
        em.getTransaction().commit();
    }

    @Override
    public List<User> findAll() {
        EntityManager em = entityManagerFactory.createEntityManager();
        String queryFind = "SELECT u FROM User u";
        Query query = em.createQuery(queryFind);
        return query.getResultList();
    }
}
