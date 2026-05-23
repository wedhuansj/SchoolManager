package controller;
import model.*;
import view.SchoolView;
import java.util.ArrayList;
import java.util.Comparator;

public class SchoolController {
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
                if (SchoolData.findStudent(id) != null) {
                    SchoolView.msg("Trùng mã!");
                    continue;
                }
                SchoolData.students.add(new Student(id, SchoolView.inpString("Tên"), SchoolView.inpInt("Tuổi"), SchoolView.inpString("Giới tính"), SchoolView.inpString("Địa chỉ")));
            }
            else if (cmd == 2) {
                String id = SchoolView.inpString("Mã HS cần xóa");
                Student s = SchoolData.findStudent(id);
                if (s != null) {
                    SchoolData.students.remove(s);
                    Classroom c = SchoolData.findClass(s.getClassId());
                    if (c != null) c.getStudentIds().remove(id);
                }
                else SchoolView.msg("Không tìm thấy");
            }
            else if (cmd == 3) {
                Student s = SchoolData.findStudent(SchoolView.inpString("Mã HS cần sửa"));
                if (s != null) {
                    s.setName(SchoolView.inpString("Tên mới"));
                    s.setAge(SchoolView.inpInt("Tuổi mới"));
                    s.setGender(SchoolView.inpString("Giới tính mới"));
                    s.setAddress(SchoolView.inpString("Địa chỉ mới"));
                }
                else SchoolView.msg("Không tìm thấy");
            }
            else if (cmd == 4) {
                int opt = SchoolView.inpInt("1. Tìm theo ID | 2. Tìm theo tên | 3. Tìm theo lớp");
                String kw = SchoolView.inpString("Từ khóa");
                for (Student s : SchoolData.students) {
                    if ((opt == 1 && s.getId().equals(kw)) || (opt == 2 && s.getName().contains(kw)) || (opt == 3 && s.getClassId().equals(kw)))
                        SchoolView.msg(s.toString());
                }
            }
            else if (cmd == 5) {
                for (Student s : SchoolData.students) {
                    SchoolView.msg(s.toString());
                }
            }
            else if (cmd == 6) {
                SchoolData.students.sort((s1, s2) -> Double.compare(s2.getGpa(), s1.getGpa()));
            }
        }
    }
    private void mTeacher() {
        while (true) {
            int cmd = SchoolView.menuSub("giáo viên");
            if (cmd == 0) break;
            if (cmd == 1) {
                String id = SchoolView.inpString("Mã GV");
                if (SchoolData.findStudent(id) != null) {
                    SchoolView.msg("Trùng mã");
                    continue;
                }
                SchoolData.teachers.add(new Teacher(id, SchoolView.inpString("Tên"), SchoolView.inpInt("Tuổi"), SchoolView.inpString("Giới tính"), SchoolView.inpString("Địa chỉ"), SchoolView.inpDouble("Lương"), SchoolView.inpInt("Kinh nghiệm")));
            }
            else if (cmd == 2) {
                Teacher t = SchoolData.findTeacher(SchoolView.inpString("Mã GV cần xóa"));
                if (t != null) {
                    SchoolData.teachers.remove(t);
                }
                else SchoolView.msg("Không thấy");
            }
            else if (cmd == 3) {
                Teacher t = SchoolData.findTeacher(SchoolView.inpString("Mã GV cần sửa"));
                if (t != null) {
                    t.setName(SchoolView.inpString("Tên mới"));
                    t.setSalary(SchoolView.inpDouble("Lương mới"));
                    t.setExp(SchoolView.inpInt("Kinh ngiệm mới"));
                }
                else SchoolView.msg("Không thấy");
            }
            else if (cmd == 4) {
                String kw = SchoolView.inpString("Tên GV cần tìm");
                for (Teacher t : SchoolData.teachers) {
                    if (t.getName().contains(kw)) SchoolView.msg(t.toString());
                }
            }
            else if (cmd == 5) {
                for (Teacher t : SchoolData.teachers) {
                    SchoolView.msg(t.toString());
                }
            }
            else if (cmd == 6) {
                Teacher t = SchoolData.findTeacher(SchoolView.inpString("Mã GV"));
                Classroom c = SchoolData.findClass(SchoolView.inpString("Mã lớp"));
                if (t != null && c != null) {
                    c.setHeadTeacherId(t.getId());
                    t.setClassId(t.getId());
                }
                else SchoolView.msg("Sai thông tin");
            }
        }
    }
    private void mClass() {
        while (true) {
            int cmd = SchoolView.menuSub("lớp học");
            if (cmd == 0) break;
            if (cmd == 1) {
                String id = SchoolView.inpString("Mã lớp");
                if (SchoolData.findClass(id) != null) {
                    SchoolView.msg("Trùng mã");
                    continue;
                }
                SchoolData.classrooms.add(new Classroom(id, SchoolView.inpString("Tên Lớp")));
            }
            else if (cmd == 2) {
                Classroom c = SchoolData.findClass(SchoolView.inpString("Mã lớp"));
                Student s = SchoolData.findStudent(SchoolView.inpString("Mã HS"));
                if (c != null && s != null) {
                    if (!c.getStudentIds().contains(s.getId())) {
                        c.getStudentIds().add(s.getId());
                        s.setClassId(c.getId());
                    }
                }
                else SchoolView.msg("Sai thông tin");
            }
            else if (cmd == 3) {
                Classroom c = SchoolData.findClass(SchoolView.inpString("Mã lớp"));
                String id = SchoolView.inpString("Mã HS cần xóa");
                if (c != null && c.getStudentIds().remove(id)) {
                    Student s = SchoolData.findStudent(id);
                    if (s != null) s.setClassId("");
                }
            }
            else if (cmd == 4) {
                Student s = SchoolData.findStudent(SchoolView.inpString("Mã HS"));
                Classroom cOld = SchoolData.findClass(s != null ? s.getClassId() : " ");
                Classroom cNew = SchoolData.findClass(SchoolView.inpString("Mã lớp mới"));
                if (s != null && cNew != null) {
                    if (cOld != null) cOld.getStudentIds().remove(s.getId());
                    cNew.getStudentIds().add(s.getId());
                    s.setClassId(cNew.getId());
                }
                else SchoolView.msg("Sai thông tin");
            }
            else if (cmd == 5) {
                for (Classroom c : SchoolData.classrooms) {
                    SchoolView.msg("Lớp: " + c.getId() + " - " + c.getName() + " | GVCN: " + c.getHeadTeacherId() + " | Sỉ số: " + c.getStudentIds().size());
                }
            }
            else if (cmd == 6) {
                Classroom c = SchoolData.findClass(SchoolView.inpString("Mã lớp"));
                if (c != null) {
                    for (String id : c.getStudentIds()) {
                        Student s = SchoolData.findStudent(id);
                        if (s != null) SchoolView.msg(s.toString());
                    }
                }
            }
        }
    }
    private void mSub() {
        while (true) {
            int cmd = SchoolView.menuSub("môn học");
            if (cmd == 0) break;
            if (cmd == 1) {
                String id = SchoolView.inpString("Mã môn");
                if (SchoolData.findSubject(id) != null) {
                    SchoolView.msg("Trùng mã");
                    continue;
                }
                SchoolData.subjects.add(new Subject(id, SchoolView.inpString("Tên Môn"), SchoolView.inpInt("Số tín chỉ")));
            }
            else if (cmd == 2) {
                Subject sj = SchoolData.findSubject(SchoolView.inpString("Mã môn cần xóa"));
                if (sj != null) {
                    SchoolData.subjects.remove(sj);
                    SchoolView.msg("Đã xóa");
                }
            }
            else if (cmd == 3) {
                Subject sj = SchoolData.findSubject(SchoolView.inpString("Mã Môn cần sửa"));
                if (sj != null) {
                    sj.setName(SchoolView.inpString("Tên mới"));
                    sj.setCredits(SchoolView.inpInt("Tin chỉ mới"));
                }
            }
            else if (cmd == 4) {
                Teacher t = SchoolData.findTeacher(SchoolView.inpString("Mã GV"));
                Subject sj = SchoolData.findSubject(SchoolView.inpString("Mã môn"));
                if (t != null && sj != null) {
                    t.setSubId(sj.getId());
                }
            }
            else if (cmd == 5) {
                Student s = SchoolData.findStudent(SchoolView.inpString("Mã HS"));
                Subject sj = SchoolData.findSubject(SchoolView.inpString("Mã Môn"));
                if (s != null && sj != null) {
                    boolean exist = false;
                    for (Score sc : s.getScores()) {
                        if (sc.getSubId().equals(sj.getId())) exist = true;
                    }
                    if (!exist) {
                        s.getScores().add(new Score(sj.getId(), 0.0));
                    }
                }
            }
        }
    }
    private void mScore() {
        while (true) {
            int cmd = SchoolView.menuSub("điểm");
            if (cmd == 0) break;
            Student s = SchoolData.findStudent(SchoolView.inpString("Mã HS"));
            String subId = SchoolView.inpString("Mã môn");
            if (s == null) {
                SchoolView.msg("Không thấy HS");
                continue;
            }
            Score target = null;
            for (Score sc : s.getScores()) {
                if (sc.getSubId().equals(subId)) target = sc;
            }
            if (cmd == 1 || cmd == 2) {
                double val = SchoolView.inpDouble("Điểm");
                if (target == null)
                    target.setVal(val);
                else {
                    s.getScores().add(new Score(subId, val));
                }
            }
            else if (cmd == 3) {
                if (target != null) {
                    s.getScores().remove(target);
                }
                else
                    SchoolView.msg("Không thấy");
            }
        }
    }
    private void mSchedule() {
        while (true) {
            int cmd = SchoolView.menuSub("thời khóa biểu");
            if (cmd == 0) break;
            if (cmd == 1) {
                String cId = SchoolView.inpString("Mã lớp");
                int d = SchoolView.inpInt("Thứ (2-7)");
                int sl = SchoolView.inpInt("Tiết (1-5)");
                String tId = SchoolView.inpString("Mã GV");
                String sId = SchoolView.inpString("Mã môn");
                String r = SchoolView.inpString("Phòng");
                boolean conflict = false;
                for (Schedule s : SchoolData.schedules) {
                    if (s.getTeacherId().equals(tId) && s.getDay() == d && s.getSlot() == sl)
                        conflict = true;
                }
                if (conflict) SchoolView.msg("Bị trùng");
                else {
                    SchoolData.schedules.add(new Schedule(cId, sId, tId, r, d, sl));
                }
            }
            else if (cmd == 2) {
                String cId = SchoolView.inpString("Mã lớp cần xem");
                for (Schedule s : SchoolData.schedules) {
                    if (s.getClassId().equals(cId))
                        SchoolView.msg("Thứ " + s.getDay() + " | Tiết: " + s.getSlot() + " | Môn: " + s.getSubId() + " | GV: " + s.getTeacherId() + " | Phòng: " + s.getRoom());
                }
            }
        }
    }
}
