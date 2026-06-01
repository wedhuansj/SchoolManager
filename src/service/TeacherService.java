package service;

import dao.TeacherDAO;
import dao.SubjectDAO;
import model.Teacher;
import view.SchoolView;
import java.util.List;

public class TeacherService {
    private final TeacherDAO teacherDAO = new TeacherDAO();
    private final SubjectDAO subjectDAO = new SubjectDAO();

    public void showAllTeachers() {
        List<Teacher> list = teacherDAO.getListTeacher();
        if (list.isEmpty()) {
            SchoolView.msg("Danh sách giáo viên trống!");
            return;
        }
        System.out.println("\n--- DANH SÁCH GIÁO VIÊN ---");
        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.printf("%-10s %-25s %-8s %-15s %-12s %-20s\n", "ID", "Tên giáo viên", "Tuổi", "Lương", "Kinh nghiệm", "Môn dạy");
        System.out.println("-------------------------------------------------------------------------------------------");
        for (Teacher t : list) {
            List<String> subs = subjectDAO.getSub(t.getId());
            t.setSubjectNames(subs);
            System.out.printf("%-10s %-25s %-8d %-15.2f %-12s %-20s\n",
                    t.getId(),
                    t.getName(),
                    t.getAge(),
                    t.getSalary(),
                    t.getExp() + " năm",
                    String.join(", ", t.getSubjectNames()));
        }
        System.out.println("-------------------------------------------------------------------------------------------");
        SchoolView.pressEnter();
    }

    public void addTeacher(Teacher t, List<String> subjects) {
        if (t.getAge() <= 0 || t.getSalary() < 0 || t.getExp() < 0) {
            SchoolView.msg("Thông tin không hợp lệ");
            return;
        }
        for (Teacher old : teacherDAO.getListTeacher()) {
            if (old.getId().equals(t.getId())) {
                SchoolView.msg("Mã giáo viên đã tồn tại");
                return;
            }
        }
        teacherDAO.addTeacher(t);
        for (String sub : subjects) {
            subjectDAO.addSub(t.getId(), sub);
        }
        SchoolView.msg("Thêm thành công");
    }

    public void addSubjectToTeacher(String teacherId, String subject) {
        boolean exists = false;
        for (Teacher old : teacherDAO.getListTeacher()) {
            if (old.getId().equals(teacherId)) {
                exists = true;
                break;
            }
        }
        if (!exists) {
            SchoolView.msg("Không tìm thấy giáo viên có mã " + teacherId);
            return;
        }
        List<String> curr = subjectDAO.getSub(teacherId);
        for (String sub : curr) {
            if (sub.equals(subject)) {
                SchoolView.msg("Giáo viên đã được phân công môn này");
                return;
            }
        }
        subjectDAO.addSub(teacherId, subject);
        SchoolView.msg("Phân công môn học mới thành công!");
    }

    public void deleteTeacher(String teacherId) {
        boolean exists = false;
        for (Teacher old : teacherDAO.getListTeacher()) {
            if (old.getId().equals(teacherId)) {
                exists = true;
                break;
            }
        }
        if (!exists) {
            SchoolView.msg("Lỗi: Không tìm thấy giáo viên có mã " + teacherId);
            return;
        }
        subjectDAO.deleteTeacherSubjects(teacherId);
        teacherDAO.deleteTeacher(teacherId);
        SchoolView.msg("Xóa giáo viên hoàn tất!");
    }
}