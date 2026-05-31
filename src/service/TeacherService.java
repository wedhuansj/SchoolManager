package service;

import dao.TeacherDAO;
import dao.SubjectDAO;
import model.Teacher;
import view.SchoolView;

import java.util.List;

public class TeacherService {
    private static final TeacherDAO teacherDAO = new TeacherDAO();
    private static final SubjectDAO subjectDAO = new SubjectDAO();
    public static void showAllTeachers() {
        List<Teacher> list = teacherDAO.getListTeacher();
        if (list.isEmpty()) {
            SchoolView.msg("Danh sách giáo viên trống!");
            return;
        }
        SchoolView.msg("Danh sách giáo viên");
        for (Teacher t : list) {
            List<String> subs = subjectDAO.getSub(t.getId());
            t.setSubjectNames(subs);
            SchoolView.msg("ID: " + t.getId() +
                    "\nTên: " + t.getName() +
                    "\nTuổi: " + t.getAge() +
                    "\nLương: " + t.getSalary() +
                    "\nKinh nghiệm: " + t.getExp() + " năm" +
                    "\nMôn dạy: " + t.getSubjectNames());
        }
    }
    public static void addTeacher(Teacher t, List<String> subjects) {
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
    public static void addSubjectToTeacher(String teacherId, String subject) {
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
    public static void deleteTeacher(String teacherId) {
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