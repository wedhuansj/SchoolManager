package controller;

import model.*;
import service.ClassroomService;
import service.ScheduleService;
import service.StudentService;
import service.TeacherService;
import view.SchoolView;
import java.util.ArrayList;
import java.util.List;

public class SchoolController {
    private boolean checkValid(String s) {
        if (s == null || s.trim().isEmpty()) {
            SchoolView.msg("Không hợp lệ");
            return false;
        }
        return true;
    }
    public void start() {
        while (true) {
            int cmd = SchoolView.menuMain();
            if (cmd == 0) break;
            switch (cmd) {
                case 1:
                    mStudent();
                    break;
                case 2:
                    mTeacher();
                    break;
                case 3:
                    mClass();
                    break;
                case 4:
                    mSub();
                    break;
                case 5:
                    mScore();
                    break;
                case 6:
                    mSchedule();
                    break;
                default:
                    SchoolView.msg("Không hợp lệ!");
            }
        }
    }
    private void mStudent() {
        while (true) {
            int cmd = SchoolView.menuSub("học sinh");
            if (cmd == 0) break;
            if (cmd == 1) {
                String id = SchoolView.inpString("Mã HS");
                if (!checkValid(id)) continue;
                String name = SchoolView.inpString("Tên");
                int age = SchoolView.inpInt("Tuổi");
                String gender = SchoolView.inpString("Giới tính");
                String address = SchoolView.inpString("Địa chỉ");
                StudentService.addStudent(new Student(id, name, age, gender, address));
            } else if (cmd == 2) {
                String id = SchoolView.inpString("Mã HS cần xóa");
                if (!checkValid(id)) continue;
                StudentService.deleteStudent(id);
            } else if (cmd == 3) {
                String id = SchoolView.inpString("Mã HS");
                if (!checkValid(id)) continue;
                String name = SchoolView.inpString("Tên");
                int age = SchoolView.inpInt("Tuổi");
                String gender = SchoolView.inpString("Giới tính");
                String address = SchoolView.inpString("Địa chỉ");
                StudentService.updateStudent(new Student(id, name, age, gender, address));
            } else if (cmd == 4) {
                int opt = SchoolView.inpInt("1. Tìm theo ID | 2. Tìm theo tên | 3. Tìm theo lớp");
                if (!(opt >= 1 && opt <= 3)) {
                    SchoolView.msg("Không hợp lệ");
                    continue;
                }
                String kw = SchoolView.inpString("Từ khóa");
                if (!checkValid(kw)) continue;
            } else if (cmd == 5) {
                StudentService.showAllStudents();
            } else if (cmd == 6) {
                String id = SchoolView.inpString("Nhập mã HS cần xem ĐTB");
                if (!checkValid(id)) continue;
                StudentService.showGPA(id);
            }
        }
    }
    private void mTeacher() {
        while (true) {
            int cmd = SchoolView.menuSub("giáo viên");
            if (cmd == 0) break;
            if (cmd == 1) {
                String id = SchoolView.inpString("Mã GV");
                if (!checkValid(id)) continue;
                String name = SchoolView.inpString("Tên");
                int age = SchoolView.inpInt("Tuổi");
                String gender = SchoolView.inpString("Giới tính");
                String address = SchoolView.inpString("Địa chỉ");
                double salary = SchoolView.inpDouble("Lương");
                int exp = SchoolView.inpInt("Kinh nghiệm");
                String sub = SchoolView.inpString("Môn giảng dạy chính");
                List<String> subs = new ArrayList<>();
                if (checkValid(sub)) subs.add(sub);
                TeacherService.addTeacher(new Teacher(id, name, age, gender, address, salary, exp), subs);
            } else if (cmd == 2) {
                String id = SchoolView.inpString("Mã GV cần xóa");
                if (!checkValid(id)) continue;
                TeacherService.deleteTeacher(id);
            } else if (cmd == 3) {
                String id = SchoolView.inpString("Mã GV cần sửa");
                if (!checkValid(id)) continue;
            } else if (cmd == 4) {
                String kw = SchoolView.inpString("Tên GV cần tìm");
                if (!checkValid(kw)) continue;
            } else if (cmd == 5) {
                TeacherService.showAllTeachers();
            } else if (cmd == 6) {
                String id = SchoolView.inpString("Mã GV");
                if (!checkValid(id)) continue;
                String sub = SchoolView.inpString("Môn dạy bổ sung");
                if (!checkValid(sub)) continue;
                TeacherService.addSubjectToTeacher(id, sub);
            }
        }
    }
    private void mClass() {
        while (true) {
            int cmd = SchoolView.menuSub("lớp học");
            if (cmd == 0) break;
            if (cmd == 1) {
                String id = SchoolView.inpString("Mã lớp");
                if (!checkValid(id)) continue;
                String name = SchoolView.inpString("Tên Lớp");
                String tId = SchoolView.inpString("Mã GVCN");
                ClassroomService.addNewClass(id, name, tId);
            } else if (cmd == 2) {
                String cId = SchoolView.inpString("Mã lớp");
                if (!checkValid(cId)) continue;
                String sId = SchoolView.inpString("Mã HS");
                if (!checkValid(sId)) continue;
            } else if (cmd == 3) {
                String cId = SchoolView.inpString("Mã lớp");
                if (!checkValid(cId)) continue;
                String sId = SchoolView.inpString("Mã HS cần xóa");
                if (!checkValid(sId)) continue;
            } else if (cmd == 4) {
                String sId = SchoolView.inpString("Mã HS");
                if (!checkValid(sId)) continue;
                String cId = SchoolView.inpString("Mã lớp mới");
                if (!checkValid(cId)) continue;
            } else if (cmd == 5) {
                ClassroomService.showAllClass();
            } else if (cmd == 6) {
                String id = SchoolView.inpString("Mã lớp");
                if (!checkValid(id)) continue;
                ClassroomService.showStudentsInClass(id);
            }
        }
    }
    private void mSub() {
        while (true) {
            int cmd = SchoolView.menuSub("môn học");
            if (cmd == 0) break;
            if (cmd == 1) {
                String id = SchoolView.inpString("Mã môn");
                if (!checkValid(id)) continue;
            }
        }
    }
    private void mScore() {
        while (true) {
            int cmd = SchoolView.menuSub("điểm");
            if (cmd == 0) break;
            if (cmd == 1 || cmd == 2) {
                String id = SchoolView.inpString("Mã HS");
                if (!checkValid(id)) continue;
                double math = SchoolView.inpDouble("Điểm Toán");
                double lit = SchoolView.inpDouble("Điểm Văn");
                double eng = SchoolView.inpDouble("Điểm Anh");
                StudentService.inputScore(id, math, lit, eng);
            }
        }
    }
    private void mSchedule() {
        while (true) {
            int cmd = SchoolView.menuSub("thời khóa biểu");
            if (cmd == 0) break;
            if (cmd == 1) {
                String cId = SchoolView.inpString("Mã lớp");
                if (!checkValid(cId)) continue;
                int d = SchoolView.inpInt("Thứ (2-7)");
                int sl = SchoolView.inpInt("Tiết (1-5)");
                String tId = SchoolView.inpString("Mã GV");
                if (!checkValid(tId)) continue;
                String sId = SchoolView.inpString("Mã môn");
                if (!checkValid(sId)) continue;
                String r = SchoolView.inpString("Phòng");
                if (!checkValid(r)) continue;
                ScheduleService.addSchedule(new Schedule(cId, sId, tId, r, d, sl));
            } else if (cmd == 2) {
                String cId = SchoolView.inpString("Mã lớp cần xem");
                if (!checkValid(cId)) continue;
                ScheduleService.showClassSchedule(cId);
            }
        }
    }
}