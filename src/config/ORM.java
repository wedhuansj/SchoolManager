package config;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ORM {
    public static final EntityManagerFactory emf;
    static  {
        try {
            emf = Persistence.createEntityManagerFactory("employeeMgmtPU");
        }
        catch (Exception e) {
            System.err.println("Lỗi nghiêm trọng: Không thể khởi tạo EntityManagerFactory!");
            throw new ExceptionInInitializerError(e);
        }
    }
    public static EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }
    public static void shutDown() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
