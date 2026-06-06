package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "teacher")
public class Teacher extends Person {
    @Column(name = "teacher_id")
    private String id;
    @Column(name = "teacher_name")
    private String name;
    @Column(name = "teacher_age")
    private int age;
    @Column(name = "teacher_gender")
    private String gender;
    @Column(name = "teacher_address")
    private String address;
    @Column(name = "teacher_salary")
    private double salary;
    @Column(name = "teacher_exp")
    private int exp;
    @Transient
    private List<String> subjectNames = new ArrayList<>();
    @Transient
    private String classId = "";
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