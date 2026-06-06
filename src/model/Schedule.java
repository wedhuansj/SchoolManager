package model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.Objects;
class ScheduleId implements Serializable {
    private String classId;
    private int day;
    private int slot;
    public ScheduleId() {}
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScheduleId that = (ScheduleId) o;
        return day == that.day && slot == that.slot && Objects.equals(classId, that.classId);
    }
    @Override
    public int hashCode() { return Objects.hash(classId, day, slot); }
}
@Entity
@Table(name = "schedule")
@IdClass(ScheduleId.class)
public class Schedule {
    @Id
    @Column(name = "class_id")
    private String classId;
    @Id
    private int day;
    @Id
    private int slot;
    @Column(name = "subject_id")
    private String subId;
    @Column(name = "teacher_id")
    private String teacherId;
    private String room;
    public Schedule() {}
    public Schedule(String classId, int day, int slot, String subId, String teacherId, String room) {
        this.classId = classId;
        this.day = day;
        this.slot = slot;
        this.subId = subId;
        this.teacherId = teacherId;
        this.room = room;
    }
    public String getClassId() { return classId; }
    public void setClassId(String classId) { this.classId = classId; }
    public int getDay() { return day; }
    public void setDay(int day) { this.day = day; }
    public int getSlot() { return slot; }
    public void setSlot(int slot) { this.slot = slot; }
    public String getSubId() { return subId; }
    public void setSubId(String subId) { this.subId = subId; }
    public String getTeacherId() { return teacherId; }
    public void setTeacherId(String teacherId) { this.teacherId = teacherId;}
    public String getRoom() { return room; }
    public void setRoom(String room) { this.room = room; }
}