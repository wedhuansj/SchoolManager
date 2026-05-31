package model;

import java.util.ArrayList;
import java.util.List;

public class Teacher extends Person {
    private List<String> subjectNames;
    private String classId;
    private double salary;
    private int exp;
    public Teacher() { this.subjectNames = new ArrayList<>(); }
    public Teacher(String id, String name, int age, String gender, String address, double salary, int exp) {
        super(id, name, age, gender, address);
        this.salary = salary;
        this.exp = exp;
        this.classId = "";
        this.subjectNames = new ArrayList<>();
    }
    public List<String> getSubjectNames() { return subjectNames; }
    public void setSubjectNames(List<String> subjectNames) { this.subjectNames = subjectNames; }
    public String getClassId() { return classId; }
    public void setClassId(String classId) { this.classId = classId; }
    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }
    public int getExp() { return exp; }
    public void setExp(int exp) { this.exp = exp; }
    @Override
    public String toString() {
        return "ID: " + getId() + ", Tên: " + getName() + ", Tuổi: " + getAge() + ", Môn: " + String.join(", ", this.subjectNames) + ", Chủ nhiệm: " + this.classId + ", Lương: " + this.salary + ", Kinh nghiệm: " + this.exp;
    }
}