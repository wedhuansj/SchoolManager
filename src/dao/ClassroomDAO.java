package dao;

import config.JDBCConnectionConfig;
import model.Classroom;
import model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClassroomDAO {
    public void addClassroom(Classroom c) {
        String sql = "INSERT INTO classroom (class_id, class_name, teacher_id) VALUES (?, ?, ?)";
        try (Connection con = JDBCConnectionConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql))
        {
            ps.setString(1, c.getId());
            ps.setString(2, c.getName());
            ps.setString(3, c.getHeadTeacherId());
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
    public List<Classroom> getListClassroom() {
        List<Classroom> classroom = new ArrayList<>();
        String sql = "SELECT * FROM classroom";
        try (Connection con = JDBCConnectionConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery())
        {
            while (rs.next()) {
                Classroom classs = new Classroom(rs.getString("class_id"), rs.getString("class_name"));
                classs.setHeadTeacherId(rs.getString("teacher_id"));
                classroom.add(classs);
                String sql2 = "SELECT student_id FROM student WHERE class_id = ?";
                try (Connection con2 = JDBCConnectionConfig.getConnection();
                     PreparedStatement ps2 = con2.prepareStatement(sql2)) {
                    ps2.setString(1, classs.getId());
                    ResultSet rs2 = ps2.executeQuery();
                    while (rs2.next()) {
                        String student_id = rs2.getString("student_id");
                        classs.getStudentIds().add(student_id);
                    }
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return classroom;
    }
    public Classroom getStudents(String class_id) {
        String sql = "select * from classroom c join student s on s.class_id = c.class_id where c.class_id = ?";
        Classroom c = null;
        try (Connection con = JDBCConnectionConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql))
        {
            ps.setString(1, class_id);
            ResultSet rs = ps.executeQuery();
            c = new Classroom();
            ArrayList<Student> students = new ArrayList<>();
            while (rs.next()) {
                c.setId(rs.getString("class_id"));
                c.setName(rs.getString("class_name"));
                c.setHeadTeacherId(rs.getString("teacher_id"));
                Student s = new Student();
                s.setId(rs.getString("student_id"));
                s.setName(rs.getString("student_name"));
                s.setAge(rs.getInt("student_age"));
                s.setGender(rs.getString("student_gender"));
                s.setAddress(rs.getString("student_address"));
                s.setClassId(rs.getString("class_id"));
                s.setMathScore(rs.getDouble("math_score"));
                s.setLiteratureScore(rs.getDouble("literature_score"));
                s.setEnglishScore(rs.getDouble("english_score"));
                students.add(s);
            }
            c.setStudentList(students);
        } catch (SQLException e) { e.printStackTrace(); }
        return c;
    }
}