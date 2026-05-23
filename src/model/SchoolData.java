package model;
import java.util.ArrayList;
public class SchoolData {
    public static ArrayList<Student> students = new ArrayList<>();
    public static ArrayList<Teacher> teachers = new ArrayList<>();
    public static ArrayList<Classroom> classrooms = new ArrayList<>();
    public static ArrayList<Subject> subjects = new ArrayList<>();
    public static ArrayList<Schedule> schedules = new ArrayList<>();
    public static Student findStudent(String id) {
        for (Student s : students) if (s.getId().equals(id)) return s;
        return null;
    }
    public static Teacher findTeacher(String id) {
        for (Teacher t : teachers) if (t.getId().equals(id)) return t;
        return null;
    }
    public static Classroom findClass(String id) {
        for (Classroom c : classrooms) if (c.getId().equals(id)) return c;
        return null;
    }
    public static Subject findSubject(String id) {
        for (Subject s : subjects) if (s.getId().equals(id)) return s;
        return null;
    }
}
