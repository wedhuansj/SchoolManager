package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import model.Classroom;

import config.ORM;

import java.util.ArrayList;
import java.util.List;

public class ClassroomDAO {
    public void addClassroom(Classroom c) {
        EntityManager em = ORM.emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(c);
            tx.commit();
        }
        catch (Exception e) {
            if (tx != null && tx.isActive()) tx.rollback();
            e.printStackTrace();
        }
        finally { em.close(); }
    }
    public List<Classroom> getListClassroom() {
        EntityManager em = ORM.emf.createEntityManager();
        List<Classroom> list = new ArrayList<>();
        try {
            TypedQuery<Classroom> query = em.createQuery("SELECT DISTINCT c FROM Classroom c LEFT JOIN FETCH c.students", Classroom.class);
            list = query.getResultList();
        }
        catch (Exception e) { e.printStackTrace(); }
        finally { em.close(); }
        return list;
    }
    public Classroom getStudents(String class_id) {
        EntityManager em = ORM.emf.createEntityManager();
        Classroom c = null;
        try {
            TypedQuery<Classroom> query = em.createQuery("SELECT c FROM Classroom c LEFT JOIN FETCH c.students WHERE c.id = :classId", Classroom.class);
            query.setParameter("classId", class_id);
            c = query.getSingleResult();
        }
        catch (Exception e)  { e.printStackTrace(); }
        finally { em.close(); }
        return c;
    }
}