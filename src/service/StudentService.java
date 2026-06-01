package service;

import dao.ScoreDAO;
import model.Student;
import dao.StudentDAO;
import view.SchoolView;
import java.util.List;

public class StudentService {
    private final StudentDAO studentDAO = new StudentDAO();
    private final ScoreDAO scoreDAO = new ScoreDAO();
    private String getRank(double gpa) {
        if (gpa >= 8.0) return "Giỏi";
        if (gpa >= 6.5) return "Khá";
        if (gpa >= 5.0) return "Trung bình";
        return "Yếu";
    }
    public void showAllStudents() {
        List<Student> list = studentDAO.getListStudent();
        if (list.isEmpty()) {
            SchoolView.msg("Danh sách trống");
            return;
        }
        System.out.println("\n--- DANH SÁCH HỌC SINH ---");
        System.out.println("-------------------------------------------------------------------------------");
        System.out.printf("%-10s %-25s %-8s %-8s %-8s %-8s %-12s\n", "Mã HS", "Họ tên", "Toán", "Văn", "Anh", "GPA", "Xếp loại");
        System.out.println("-------------------------------------------------------------------------------");
        for (Student s : list) {
            double gpa = (s.getMathScore() + s.getEnglishScore() + s.getLiteratureScore()) / 3.0;
            System.out.printf("%-10s %-25s %-8.2f %-8.2f %-8.2f %-8.2f %-12s\n",
                    s.getId(), s.getName(), s.getMathScore(), s.getLiteratureScore(), s.getEnglishScore(), gpa, getRank(gpa));
        }
        System.out.println("-------------------------------------------------------------------------------");
        SchoolView.pressEnter();
    }
    public void addStudent(Student s) {
        for (Student old : studentDAO.getListStudent()) {
            if (old.getId().equals(s.getId())) {
                SchoolView.msg("Mã đã tồn tại");
                return;
            }
        }
        studentDAO.addStudent(s);
        SchoolView.msg("Thêm học sinh thành công");
    }
    public void inputScore(String id, double math, double lit, double eng) {
        if (math < 0 || math > 10 || lit < 0 || lit > 10 || eng < 0 || eng > 10) {
            SchoolView.msg("Điểm không hợp lệ");
            return;
        }
        Student s1 = studentDAO.findById(id);
        if (s1 == null) {
            SchoolView.msg("Học sinh không tồn tại");
            return;
        }
        scoreDAO.updateScore(id, math, lit, eng);
        SchoolView.msg("Cập nhật điểm thành công");
    }
    public void showGPA(String id) {
        for (Student s : studentDAO.getListStudent()) {
            if (s.getId().equals(id)) {
                double gpa = (s.getMathScore() + s.getEnglishScore() + s.getLiteratureScore()) / 3.0;
                System.out.println("\n-------------------------------------------");
                System.out.printf("Mã học sinh: %s\nHọ và tên: %s\nĐiểm trung bình: %.2f\nXếp loại: %s\n", id, s.getName(), gpa, getRank(gpa));
                System.out.println("-------------------------------------------");
                SchoolView.pressEnter();
                return;
            }
        }
        SchoolView.msg("Không tìm thấy mã");
    }
    public void deleteStudent(String id) {
        Student s1 = studentDAO.findById(id);
        if (s1 == null) {
            SchoolView.msg("Học sinh không tồn tại");
            return;
        }
        studentDAO.deleteStudent(id);
        SchoolView.msg("Xóa thành công");
    }
    public void updateStudent(Student s) {
        Student s1 = studentDAO.findById(s.getId());
        if (s1 == null) {
            SchoolView.msg("Học sinh không tồn tại");
            return;
        }
        studentDAO.updateStudent(s);
    }
}