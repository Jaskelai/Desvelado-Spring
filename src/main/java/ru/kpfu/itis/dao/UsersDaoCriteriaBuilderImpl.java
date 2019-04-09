package ru.kpfu.itis.dao;

import org.springframework.stereotype.Repository;
import ru.kpfu.itis.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class UsersDaoCriteriaBuilderImpl implements UsersDao {
    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @Override
    public User find(Integer id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> query = cb.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.select(root);
        query.where(cb.equal(root.get("id"), id));
        return em.createQuery(query).getSingleResult();
    }

    @Override
    @Transactional
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
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaUpdate<User> update = cb.createCriteriaUpdate(User.class);
        Root<User> root = update.from(User.class);
        update.set(root.get("email"), model.getEmail());
        update.where(cb.equal(root.get("username"), model.getUsername()));
        em.createQuery(update).executeUpdate();
        em.getTransaction().commit();
    }

    @Override
    public void delete(User model) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaDelete<User> delete = cb.createCriteriaDelete(User.class);
        Root root = delete.from(User.class);
        delete.where(cb.equal(root.get("username"), model.getUsername()));
        em.createQuery(delete).executeUpdate();
        em.getTransaction().commit();
    }

    @Override
    public List<User> findAll() {
        EntityManager em = entityManagerFactory.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> query = cb.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.select(root);
        return em.createQuery(query).getResultList();
    }

    @Override
    public List<User> findAll(int limit, int offset) {
        EntityManager em = entityManagerFactory.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> query = cb.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.select(root);
        TypedQuery<User> typedQuery = em.createQuery(query);
        typedQuery.setFirstResult(offset);
        typedQuery.setMaxResults(limit);
        return typedQuery.getResultList();
    }
}
