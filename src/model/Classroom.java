package model;

import java.util.ArrayList;

public class Classroom {
    private final String id;
    private String name, headTeacherId;
    private ArrayList<String> studentIds = new ArrayList<>();
    public Classroom(String id, String name) {
        this.id = id;
        this.name = name;
        this.headTeacherId = "";
    }
    public String getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getHeadTeacherId() { return headTeacherId; }
    public void setHeadTeacherId(String headTeacherId) { this.headTeacherId = headTeacherId; }
    public ArrayList<String> getStudentIds() { return studentIds; }
}
