package dao;

import config.ORM;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import model.Teacher;
import java.util.ArrayList;
import java.util.List;

public class TeacherDAO {
    public  void addTeacher(Teacher t) {
        EntityManager em = ORM.emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(t);
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive())
                tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
    public void updateTeacher(Teacher t) {
        EntityManager em = ORM.emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(t);
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive())
                tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
    public  List<Teacher> getListTeacher() {
        EntityManager em = ORM.emf.createEntityManager();
        List<Teacher> list = new ArrayList<>();
        try {
            TypedQuery<Teacher> query = em.createQuery("SELECT t FROM Teacher t", Teacher.class);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return list;
    }
    public  void deleteTeacher(String id) {
        EntityManager em = ORM.emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Teacher t = em.find(Teacher.class, id);
            if (t != null)
                em.remove(t);
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive())
                tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}