package service;

import dao.ClassroomDAO;
import dao.StudentDAO;
import dao.TeacherDAO;
import model.Classroom;
import model.Student;
import model.Teacher;
import view.SchoolView;
import java.util.ArrayList;
import java.util.List;

public class ClassroomService {
    public static void showList() {
        List<Classroom> cls = ClassroomDAO.getListClassroom();
        List<Teacher> teach = TeacherDAO.getListTeacher();
        for (Classroom c : cls) {
            String teacherName = "Chưa có";
            for (Teacher t : teach) {
                if (t.getId().equals(c.getHeadTeacherId())) {
                    teacherName = t.getName();
                    break;
                }
            }
            int totalStudent = c.getStudentIds().size();
            SchoolView.msg("Mã lớp: " + c.getId() + "\nTên lớp: " + c.getName() + "\nGVCN: " + teacherName + "\nSỉ số: " + totalStudent);
        }
    }
    public static void showStudentsInClass(String classId) {
        List<Classroom> cls = ClassroomDAO.getListClassroom();
        Classroom target = null;
        for (Classroom c : cls) {
            if (c.getId().equals(classId)) {
                target = c;
            }
        }
        if (target == null) {
            SchoolView.msg("Không tìm thấy mã");
            return;
        }
        List<String> studentIds = target.getStudentIds();
        if (studentIds.isEmpty()) {
            SchoolView.msg("Lớp học chưa có học sinh");
            return;
        }
        List<Student> students = StudentDAO.getListStudent();
        SchoolView.msg("Danh sách học sinh lớp " + target.getName());
        for (String id : studentIds) {
            for (Student s : students) {
                if (id.equals(s.getId())) {
                    SchoolView.msg("Mã HS: " + s.getId() + "\nTên: " + s.getName() + "\nTuổi: " + s.getAge());
                }
            }
        }
    }
    public static boolean addNewClass(String id, String name, String teacherId) {
        List<Classroom> cls = ClassroomDAO.getListClassroom();
        for (Classroom c : cls) {
            if (c.getId().equals(id)) {
                SchoolView.msg("Mã lớp đã tồn tại");
                return false;
            }
        }
        Classroom newClass = new Classroom(id, name);
        newClass.setHeadTeacherId(teacherId);
        ClassroomDAO.addClassroom(newClass);
        SchoolView.msg("Tạo lớp thành công");
        return true;
    }
    public static void showAllClass() {
        List<Classroom> cls = ClassroomDAO.getListClassroom();
        List<Teacher> teach = TeacherDAO.getListTeacher();
        if (cls.isEmpty()) {
            SchoolView.msg("Danh sách lớp học trống!");
            return;
        }
        for (Classroom c : cls) {
            String teacherName = "Chưa có";
            for (Teacher t : teach) {
                if (t.getId().equals(c.getHeadTeacherId())) {
                    teacherName = t.getName();
                    break;
                }
            }
            int totalStudent = c.getStudentIds().size();
            SchoolView.msg("Mã lớp: " + c.getId() + "\nTên lớp: " + c.getName() + "\nGVCN: " + teacherName + "\nSỉ số: " + totalStudent + "\n----------------");
        }
    }
}