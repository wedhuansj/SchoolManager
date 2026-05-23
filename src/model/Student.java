package model;

import java.util.ArrayList;

public class Student extends Person {
    private String classId;
    private ArrayList<Score> scores = new ArrayList<>();
    public Student(String id, String name, int age, String gender, String address) {
        super(id, name, age, gender, address);
        this.classId = "";
    }
    public String getClassId() { return classId; }
    public void setClassId(String classId) { this.classId = classId; }
    public ArrayList<Score> getScores() { return scores; }
    public double getGpa() {
        if (scores.isEmpty()) return 0.0;
        double sum = 0;
        for (Score s : scores) sum += s.getVal();
        return sum / scores.size();
    }
    public String getRank() {
        double gpa = getGpa();
        if (gpa >= 8.0) return "Giỏi";
        if (gpa >= 6.5) return "Khá";
        if (gpa >= 5.0) return "Trung bình";
        return "Yếu";
    }
    @Override
    public String toString() {
        return "ID: " + getId() + ", Tên: " + getName() + ", Lớp: " + this.classId + ", GPA: " + getGpa() + ", Xếp loại: " + getRank();
    }
}
