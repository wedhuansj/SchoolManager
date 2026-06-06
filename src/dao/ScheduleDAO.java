package dao;

import config.ORM;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import model.Schedule;

import java.util.ArrayList;
import java.util.List;

public class ScheduleDAO {
    public List<Schedule> getListSchedule() {
        EntityManager em = ORM.emf.createEntityManager();
        List<Schedule> list = new ArrayList<>();
        try {
            TypedQuery<Schedule> query = em.createQuery("SELECT DISTINCT s FROM Schedule s", Schedule.class);
            list = query.getResultList();
        } catch (Exception e) { e.printStackTrace(); }
        finally { em.close(); }
        return list;
    }
    public void addSchedule(Schedule s) {
        EntityManager em = ORM.emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
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
    public void updateSchedule(Schedule s) {
        EntityManager em = ORM.emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(s);
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive())
                tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
    public void deleteSchedule(String classId, int day, int slot) {
        EntityManager em = ORM.emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Schedule s = em.find(Schedule.class, classId);
            if (s != null)
                em.remove(s);
            tx.rollback();
        } catch (Exception e) {
            if (tx != null && tx.isActive())
                tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}