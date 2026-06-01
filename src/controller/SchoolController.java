package controller;

import model.Student;
import model.Teacher;
import service.ClassroomService;
import service.StudentService;
import service.TeacherService;
import view.SchoolView;
import java.util.ArrayList;
import java.util.List;

public class SchoolController {
    private final StudentService studentService = new StudentService();
    private final TeacherService teacherService = new TeacherService();
    private final ClassroomService classroomService = new ClassroomService();
    private boolean checkValid(String s) {
        if (s == null || s.trim().isEmpty()) {
            SchoolView.msg("Dữ liệu nhập không được trống!");
            return false;
        }
        return true;
    }
    public void start() {
        while (true) {
            int cmd = SchoolView.menuMain();
            if (cmd == 0) break;
            switch (cmd) {
                case 1: mStudent(); break;
                case 2: mTeacher(); break;
                case 3: mClass(); break;
                case 4: mSub(); break;
                case 5: mScore(); break;
                default: SchoolView.msg("Chức năng không hợp lệ!");
            }
        }
    }
    private void mStudent() {
        while (true) {
            int cmd = SchoolView.menuSub("học sinh");
            if (cmd == 0) break;
            switch (cmd) {
                case 1:
                    String id = SchoolView.inpString("Mã HS");
                    if (!checkValid(id)) continue;
                    String name = SchoolView.inpString("Tên");
                    int age = SchoolView.inpInt("Tuổi");
                    String gender = SchoolView.inpString("Giới tính");
                    String address = SchoolView.inpString("Địa chỉ");
                    studentService.addStudent(new Student(id, name, age, gender, address));
                    break;
                case 2:
                    String updateId = SchoolView.inpString("Mã HS cần sửa");
                    if (!checkValid(updateId)) continue;
                    String uName = SchoolView.inpString("Tên mới");
                    int uAge = SchoolView.inpInt("Tuổi mới");
                    String uGender = SchoolView.inpString("Giới tính mới");
                    String uAddress = SchoolView.inpString("Địa chỉ mới");
                    studentService.updateStudent(new Student(updateId, uName, uAge, uGender, uAddress));
                    break;
                case 3:
                    String delId = SchoolView.inpString("Mã HS cần xóa");
                    if (!checkValid(delId)) continue;
                    studentService.deleteStudent(delId);
                    break;
                case 4:
                    String searchId = SchoolView.inpString("Mã HS cần tìm");
                    if (!checkValid(searchId)) continue;
                    studentService.showGPA(searchId);
                    break;
                case 5:
                    studentService.showAllStudents();
                    break;
                default:
                    SchoolView.msg("Lựa chọn không hợp lệ!");
            }
        }
    }
    private void mTeacher() {
        while (true) {
            int cmd = SchoolView.menuSub("giáo viên");
            if (cmd == 0) break;
            switch (cmd) {
                case 1:
                    String id = SchoolView.inpString("Mã GV");
                    if (!checkValid(id)) continue;
                    String name = SchoolView.inpString("Tên GV");
                    int age = SchoolView.inpInt("Tuổi");
                    String gender = SchoolView.inpString("Giới tính");
                    String address = SchoolView.inpString("Địa chỉ");
                    double salary = SchoolView.inpDouble("Lương");
                    int exp = SchoolView.inpInt("Kinh nghiệm");
                    String sub = SchoolView.inpString("Môn giảng dạy chính");
                    List<String> subs = new ArrayList<>();
                    if (checkValid(sub)) subs.add(sub);
                    teacherService.addTeacher(new Teacher(id, name, age, gender, address, salary, exp), subs);
                    break;
                case 2:
                    SchoolView.msg("Tính năng đang được cập nhật phần dữ liệu DAO.");
                    break;
                case 3:
                    String delId = SchoolView.inpString("Mã GV cần xóa");
                    if (!checkValid(delId)) continue;
                    teacherService.deleteTeacher(delId);
                    break;
                case 4:
                    String tId = SchoolView.inpString("Mã GV");
                    if (!checkValid(tId)) continue;
                    String newSub = SchoolView.inpString("Môn dạy bổ sung");
                    if (!checkValid(newSub)) continue;
                    teacherService.addSubjectToTeacher(tId, newSub);
                    break;
                case 5:
                    teacherService.showAllTeachers();
                    break;
                default:
                    SchoolView.msg("Lựa chọn không hợp lệ!");
            }
        }
    }
    private void mClass() {
        while (true) {
            int cmd = SchoolView.menuSub("lớp học");
            if (cmd == 0) break;
            switch (cmd) {
                case 1:
                    String id = SchoolView.inpString("Mã lớp");
                    if (!checkValid(id)) continue;
                    String name = SchoolView.inpString("Tên Lớp");
                    String tId = SchoolView.inpString("Mã GVCN");
                    classroomService.addNewClass(id, name, tId);
                    break;
                case 2:
                    classroomService.showAllClass();
                    break;
                case 3:
                    String clId = SchoolView.inpString("Mã lớp cần xem");
                    if (!checkValid(clId)) continue;
                    classroomService.showStudentsInClass(clId);
                    break;
                default:
                    SchoolView.msg("Lựa chọn không hợp lệ!");
            }
        }
    }
    private void mSub() {
        while (true) {
            int cmd = SchoolView.menuSub("môn học");
            if (cmd == 0) break;
            SchoolView.msg("Tính năng môn học đang phát triển.");
        }
    }
    private void mScore() {
        while (true) {
            int cmd = SchoolView.menuSub("điểm");
            if (cmd == 0) break;
            if (cmd == 1) {
                String id = SchoolView.inpString("Mã HS");
                if (!checkValid(id)) continue;
                double math = SchoolView.inpDouble("Điểm Toán");
                double lit = SchoolView.inpDouble("Điểm Văn");
                double eng = SchoolView.inpDouble("Điểm Anh");
                studentService.inputScore(id, math, lit, eng);
            }
        }
    }
}