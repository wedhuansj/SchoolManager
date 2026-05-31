package service;

import dao.ScheduleDAO;
import dao.SubjectDAO;
import model.Schedule;
import view.SchoolView;
import java.util.List;

public class ScheduleService {
    private static boolean checkConflict(Schedule sched) {
        List<Schedule> schedules = ScheduleDAO.getListSchedule();
        for (Schedule s : schedules) {
            if (sched.getClassId().equals(s.getClassId()) || sched.getTeacherId().equals(s.getTeacherId()) || sched.getRoom().equals(s.getRoom())) {
                return false;
            }
        }
        return true;
    }
    private static boolean checkSub(String teacherId, String subId) {
        List<String> validSub = SubjectDAO.getSub(teacherId);
        for (String sub : validSub) {
            if (sub.equals(subId)) {
                return true;
            }
        }
        return false;
    }
    public static void addSchedule(Schedule schedule) {
        if (!checkSub(schedule.getTeacherId(), schedule.getSubId())) {
            SchoolView.msg("Giáo viên không dạy môn này");
            return;
        }
        if (!checkConflict(schedule)) {
            SchoolView.msg("Bị trùng lịch");
            return;
        }
        ScheduleDAO.addSchedule(schedule);
        SchoolView.msg("Tạo lịch thành công");
    }
    public static void showClassSchedule(String classId) {
        List<Schedule> schedule = ScheduleDAO.getListSchedule();
        for (Schedule s : schedule) {
            if (s.getClassId().equals(classId)) {
                SchoolView.msg("Thứ: " + s.getDay() + "\nTiết: " + s.getSlot() + "\nMôn: " + s.getSubId() + "\nPhòng: " + s.getRoom());
            }
        }
    }
    public static void deleteSchedule(Schedule s) {
        ScheduleDAO.deleteSchedule(s);
        SchoolView.msg("Đã xóa");
    }
}
