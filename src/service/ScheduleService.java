package service;

import dao.ScheduleDAO;
import dao.SubjectDAO;
import model.Schedule;
import view.SchoolView;
import java.util.List;

public class ScheduleService {
    private final ScheduleDAO scheduleDAO = new ScheduleDAO();
    private final SubjectDAO subjectDAO = new SubjectDAO();
    private boolean checkConflict(Schedule sched) {
        List<Schedule> schedules = scheduleDAO.getListSchedule();
        for (Schedule s : schedules) {
            if (sched.getClassId().equals(s.getClassId()) || sched.getTeacherId().equals(s.getTeacherId()) || sched.getRoom().equals(s.getRoom())) {
                return false;
            }
        }
        return true;
    }
    private boolean checkSub(String teacherId, String subId) {
        List<String> validSub = subjectDAO.getSub(teacherId);
        for (String sub : validSub) {
            if (sub.equals(subId)) {
                return true;
            }
        }
        return false;
    }
    public void addSchedule(Schedule schedule) {
        if (!checkSub(schedule.getTeacherId(), schedule.getSubId())) {
            SchoolView.msg("Giáo viên không dạy môn này");
            return;
        }
        if (!checkConflict(schedule)) {
            SchoolView.msg("Bị trùng lịch");
            return;
        }
        scheduleDAO.addSchedule(schedule);
        SchoolView.msg("Tạo lịch thành công");
    }
    public void showClassSchedule(String classId) {
        List<Schedule> schedule = scheduleDAO.getListSchedule();
        for (Schedule s : schedule) {
            if (s.getClassId().equals(classId)) {
                SchoolView.msg("Thứ: " + s.getDay() + "\nTiết: " + s.getSlot() + "\nMôn: " + s.getSubId() + "\nPhòng: " + s.getRoom());
            }
        }
    }
    public void deleteSchedule(Schedule s) {
        scheduleDAO.deleteSchedule(s);
        SchoolView.msg("Đã xóa");
    }
}