package com.project.dao;

import com.project.model.Projekt;
import com.project.datasource.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class ProjektDAOImpl implements ProjektDAO {

    @Override
    public Projekt getProjekt(Integer projektId) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        Projekt projekt = entityManager.find(Projekt.class, projektId);
        entityManager.close();
        return projekt;
    }

    @Override
    public void setProjekt(Projekt projekt) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(projekt);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void deleteProjekt(Integer projektId) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        entityManager.getTransaction().begin();
        Projekt projekt = entityManager.find(Projekt.class, projektId);
        entityManager.remove(projekt);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public List<Projekt> getProjekty(Integer offset, Integer limit) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        TypedQuery<Projekt> query = entityManager
                .createQuery("SELECT Projekt FROM Projekt", Projekt.class);
        List<Projekt> projekty = query
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
        return projekty;
    }

    @Override
    public List<Projekt> searchByNazwa(String search4, Integer offset, Integer limit) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        TypedQuery<Projekt> query = entityManager
                .createQuery("SELECT Projekt FROM Projekt WHERE Projekt.nazwa = :search ", Projekt.class);
        query.setParameter("search", search4 );
        List<Projekt> projekty = query
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
        return projekty;
    }
}
