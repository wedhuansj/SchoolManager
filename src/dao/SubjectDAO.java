package dao;

import config.ORM;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import model.Schedule;

import java.util.ArrayList;
import java.util.List;

public class SubjectDAO {
    public  List<String> getSub(String teacherId) {
        EntityManager em = ORM.emf.createEntityManager();
        List<String> list = new ArrayList<>();
        try {
            TypedQuery<String> query = em.createQuery("SELECT DISTINCT sub.name FROM Schedule sch JOIN Subject sub ON sch.subId = sub.id WHERE sch.teacherId = :teacherId", String.class);
            query.setParameter("teacherId", teacherId);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return list;
    }
    public void addSub(String teacherId, String sub)  {
        EntityManager em = ORM.emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Schedule s = new Schedule();
            s.setTeacherId(teacherId);
            s.setSubId(sub);
            em.persist(s);
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive())
                tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
    public  void deleteTeacherSubjects(String teacherId) {
        EntityManager em = ORM.emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Schedule s = em.find(Schedule.class, teacherId);
            if (s != null)
                em.remove(s);
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