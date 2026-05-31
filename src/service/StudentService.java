package service;
import dao.ScoreDAO;
import model.Student;
import dao.StudentDAO;
import view.SchoolView;

import java.util.List;

public class StudentService {
    private static final StudentDAO studentDAO = new StudentDAO();
    private static final ScoreDAO scoreDAO = new ScoreDAO();
    public static void showAllStudents() {
        List<Student> list = studentDAO.getListStudent();
        if (list.isEmpty()) {
            SchoolView.msg("Danh sách trống");
            return;
        }
        SchoolView.msg("Danh sách HS");
        for (Student s : list) {
            SchoolView.msg("ID: " + s.getId() + "\nTên: " + s.getName() + "\nTuổi: " + s.getAge() + "\nToán: " + s.getMathScore() + "\nVăn: " + s.getLiteratureScore() + "\nAnh: " + s.getEnglishScore());
        }
    }
    public static void addStudent(Student s) {
        for (Student old : studentDAO.getListStudent()) {
            if (old.getId().equals(s.getId())) {
                SchoolView.msg("Mã đã tồn tại");
                return;
            }
        }
        studentDAO.addStudent(s);
        SchoolView.msg("Thêm học sinh thành công");
    }
    public static void inputScore(String id, double math, double lit, double eng) {
        if (math < 0 || math > 10 || lit < 0 || lit > 10 || eng < 0 || eng > 10) {
            SchoolView.msg("Điểm không hợp lệ");
            return;
        }
        boolean exist = false;
        for (Student old : studentDAO.getListStudent()) {
            if (old.getId().equals(id)) {
                exist = true;
                break;
            }
        }
        if (!exist) {
            SchoolView.msg("Mã học sinh không tồn tại");
            return;
        }
        scoreDAO.updateScore(id, math, lit, eng);
        SchoolView.msg("Cập nhật điểm thành công");
    }
    public static void showGPA(String id) {
        for (Student s : studentDAO.getListStudent()) {
            if (s.getId().equals(id)) {
                double gpa = (s.getMathScore() + s.getEnglishScore() + s.getLiteratureScore()) / 3.0;
                SchoolView.msg("Mã học sinh: " + id + "\nĐiểm trung bình: " + gpa);
                return;
            }
        }
        SchoolView.msg("Không tìm thấy mã");
    }
    public static void deleteStudent(String id) {
        boolean exist = false;
        for (Student old : studentDAO.getListStudent()) {
            if (old.getId().equals(id)) {
                exist = true;
                break;
            }
        }
        if (!exist) {
            SchoolView.msg("Học sinh không tồn tại");
            return;
        }
        StudentDAO.deleteStudent(id);
        SchoolView.msg("Xóa thành công");
    }
    public static void updateStudent(Student s) {
        boolean exist = false;
        for (Student old : studentDAO.getListStudent()) {
            if (old.getId().equals(s.getId())) {
                exist = true;
                break;
            }
        }
        if (!exist) {
            SchoolView.msg("Học sinh không tồn tại");
            return;
        }
        StudentDAO.updateStudent(s);
    }
}
