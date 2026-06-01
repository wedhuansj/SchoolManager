package model;

import java.util.ArrayList;

public class Classroom {
    private  String id = "";
    private  String name;
    private String headTeacherId;
    private  ArrayList<String> studentIds = new ArrayList<>();
    private  ArrayList<Student> students = new ArrayList<>();
    public Classroom() {}
    public Classroom(String id, String name) {
        this.id = id;
        this.name = name;
        this.headTeacherId = "";
    }
    public void setId(String id) { this.id = id; }
    public  String getId() { return id; }
    public  String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getHeadTeacherId() { return headTeacherId; }
    public void setHeadTeacherId(String headTeacherId) { this.headTeacherId = headTeacherId; }
    public  ArrayList<String> getStudentIds() { return studentIds; }
    public void setStudentList(ArrayList<Student> students) { this.students = students; }
    public  ArrayList<Student> getStudentList() { return students; }
}
