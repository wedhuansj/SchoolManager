package model;
public class Student extends Person {
    private String classId;
    private double mathScore;
    private double literatureScore;
    private double englishScore;
    public Student() {}
    public Student(String id, String name, int age, String gender, String address) {
        super(id, name, age, gender, address);
        this.classId = "";
        this.mathScore = 0.0;
        this.literatureScore = 0.0;
        this.englishScore = 0.0;
    }
    public String getClassId() { return classId; }
    public void setClassId(String classId) { this.classId = classId; }
    public double getMathScore() { return mathScore; }
    public void setMathScore(double mathScore) { this.mathScore = mathScore; }
    public double getLiteratureScore() { return literatureScore; }
    public void setLiteratureScore(double literatureScore) { this.literatureScore = literatureScore; }
    public double getEnglishScore() { return englishScore; }
    public void setEnglishScore(double englishScore) { this.englishScore = englishScore; }
    public double getGpa() {
        return (mathScore + literatureScore + englishScore) / 3.0;
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
        return "ID: " + getId() + ", Tên: " + getName() + ", Lớp: " + this.classId + ", GPA: " + String.format("%.2f", getGpa()) + ", Xếp loại: " + getRank();
    }
}