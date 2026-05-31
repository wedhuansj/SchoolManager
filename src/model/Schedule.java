package model;

import java.util.List;

public class Schedule {
    private String classId, subId, teacherId, room;
    private int day, slot;
    public Schedule(String classId, String subId, String teacherId, String room, int day, int slot) {
        this.classId = classId;
        this.subId = subId;
        this.teacherId = teacherId;
        this.room = room;
        this.day = day;
        this.slot = slot;
    }

    public String getClassId() { return classId; }
    public int getDay() { return day; }
    public int getSlot() { return slot; }
    public String getTeacherId() { return teacherId; }
    public String getSubId() { return subId; }
    public String getRoom() { return room; }
}
