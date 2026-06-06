package dao;


import config.ORM;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import model.Student;

public class ScoreDAO {
    public void updateScore(String id, double math, double literature, double english) {
        EntityManager em = ORM.emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Student s = em.find(Student.class, id);
            if (s != null) {
                s.setMathScore(math);
                s.setLiteratureScore(literature);
                s.setEnglishScore(english);
            }
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