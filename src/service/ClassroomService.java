package service;

import dao.ClassroomDAO;
import dao.TeacherDAO;
import model.Classroom;
import model.Teacher;
import model.Student;
import view.SchoolView;
import java.util.List;

public class ClassroomService {
    private final ClassroomDAO classroomDAO = new ClassroomDAO();
    private final TeacherDAO teacherDAO = new TeacherDAO();
    public void showList() {
        showAllClass();
    }
    public void showStudentsInClass(String classId) {
        Classroom data = classroomDAO.getStudents(classId);
        if (data == null) {
            SchoolView.msg("Lớp không tồn tại");
            return;
        }
        if (data.getStudentList().isEmpty()) {
            SchoolView.msg("Lớp không có học sinh");
            return;
        }
        System.out.println("\n--- DANH SÁCH HỌC SINH LỚP: " + classId.toUpperCase() + " ---");
        System.out.println("-------------------------------------------------------------");
        System.out.printf("%-10s %-25s %-10s\n", "Mã HS", "Họ tên", "Tuổi");
        System.out.println("-------------------------------------------------------------");
        for (Student s : data.getStudentList()) {
            System.out.printf("%-10s %-25s %-10d\n", s.getId(), s.getName(), s.getAge());
        }
        System.out.println("-------------------------------------------------------------");
        SchoolView.pressEnter();
    }
    public boolean addNewClass(String id, String name, String teacherId) {
        List<Classroom> cls = classroomDAO.getListClassroom();
        for (Classroom c : cls) {
            if (c.getId().equals(id)) {
                SchoolView.msg("Mã lớp đã tồn tại");
                return false;
            }
        }
        Classroom newClass = new Classroom(id, name);
        newClass.setHeadTeacherId(teacherId);
        classroomDAO.addClassroom(newClass);
        SchoolView.msg("Tạo lớp thành công");
        return true;
    }
    public void showAllClass() {
        List<Classroom> cls = classroomDAO.getListClassroom();
        List<Teacher> teach = teacherDAO.getListTeacher();
        if (cls.isEmpty()) {
            SchoolView.msg("Danh sách lớp học trống!");
            return;
        }
        System.out.println("\n--- DANH SÁCH LỚP HỌC ---");
        System.out.println("----------------------------------------------------------------------");
        System.out.printf("%-12s %-18s %-25s %-10s\n", "Mã lớp", "Tên lớp", "GVCN", "Sĩ số");
        System.out.println("----------------------------------------------------------------------");
        for (Classroom c : cls) {
            String teacherName = "Chưa có";
            for (Teacher t : teach) {
                if (t.getId().equals(c.getHeadTeacherId())) {
                    teacherName = t.getName();
                    break;
                }
            }
            int totalStudent = c.getStudentIds().size();
            System.out.printf("%-12s %-18s %-25s %-10d\n", c.getId(), c.getName(), teacherName, totalStudent);
        }
        System.out.println("----------------------------------------------------------------------");
        SchoolView.pressEnter();
    }
}