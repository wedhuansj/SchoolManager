package model;

import java.util.ArrayList;

public class Classroom {
    private static String id = "";
    private static String name;
    private String headTeacherId;
    private static ArrayList<String> studentIds = new ArrayList<>();
    public Classroom() {}
    public Classroom(String id, String name) {
        this.id = id;
        this.name = name;
        this.headTeacherId = "";
    }
    public static String getId() { return id; }
    public static String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getHeadTeacherId() { return headTeacherId; }
    public void setHeadTeacherId(String headTeacherId) { this.headTeacherId = headTeacherId; }
    public static ArrayList<String> getStudentIds() { return studentIds; }
}
