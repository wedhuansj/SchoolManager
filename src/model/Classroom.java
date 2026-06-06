package model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "classroom")
public class Classroom {
    @Id
    @Column(name = "class_id")
    private  String id = "";
    @Column(name = "class_name")
    private  String name;
    @Column(name = "teacher_id")
    private String headTeacherId;
    @OneToMany(mappedBy = "classroom")
    private List<Student> students = new ArrayList<>();
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
    public void setStudentList(ArrayList<Student> students) { this.students = students; }
    public  List<Student> getStudentList() { return students; }
}
