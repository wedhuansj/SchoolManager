package model;

public class Teacher extends Person {
    private String subId, classId;
    private double salary;
    private int exp;
    public Teacher (String id, String name, int age, String gender, String address, double salary, int exp) {
        super(id, name, age, gender, address);
        this.salary = salary;
        this.exp = exp;
        this.subId = "";
        this.classId = "";
    }
    public String getSubId() { return subId; }
    public void setSubId(String subId) { this.subId = subId; }
    public String getClassId() { return classId; }
    public void setClassId(String classId) { this.classId = classId; }
    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }
    public int getExp() { return exp; }
    public void setExp(int exp) { this.exp = exp; }
    @Override
    public String toString() {
        return "ID: " + getId() + ", Tên: " + getName() + ", Tuổi: " + getAge() + ", Môn" + this.subId + ", Lớp:" + this.classId + ", Lương:" + this.salary + ", Kinh nghiệm: " + this.exp;
    }
}
