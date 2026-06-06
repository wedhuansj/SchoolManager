package dao;

import config.ORM;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    public List<Student> getListStudent() {
        EntityManager em = ORM.emf.createEntityManager();
        List<Student> list = new ArrayList<>();
        try {
            TypedQuery<Student> query = em.createQuery("SELECT s FROM Student s", Student.class);
            list = query.getResultList();
        }
        catch (Exception e) { e.printStackTrace(); }
        finally { em.close(); }
        return list;
    }
    public void addStudent(Student s) {
        EntityManager em = ORM.emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(s);
            tx.commit();
        }
        catch (Exception e) { tx.rollback(); }
        finally { em.close(); }
    }
    public void updateStudent(Student s) {
        EntityManager em = ORM.emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(s);
            tx.commit();
        }
        catch (Exception e) {
            if (tx != null && tx.isActive()) tx.rollback();
            e.printStackTrace();
        }
        finally { em.close(); }
    }
    public void deleteStudent(String id) {
        EntityManager em = ORM.emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Student s = em.find(Student.class, id);
            if (s != null) em.remove(s);
            tx.commit();
        }
        catch (Exception e) {
            if (tx != null && tx.isActive()) tx.rollback();
            e.printStackTrace();
        }
        finally { em.close(); }
    }
    public Student findById(String studentId) {
        EntityManager em = ORM.emf.createEntityManager();
        Student s = null;
        try { s = em.find(Student.class, studentId); }
        catch (Exception e) { e.printStackTrace(); }
        finally { em.close(); }
        return s;
    }
}