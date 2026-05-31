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
    public static void addClassroom(Classroom c) {
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
    public void updateClassroom(Classroom c) {
        String sql = "UPDATE classroom SET class_name = ?, teacher_id = ? WHERE class_id = ?";
        try (Connection con = JDBCConnectionConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql))
        {
            ps.setString(1, c.getName());
            ps.setString(2, c.getHeadTeacherId());
            ps.setString(3, c.getId());
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
    public static List<Classroom> getListClassroom() {
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
                // tim hs thuoc lop
                String sql2 = "SELECT student_id FROM classroom WHERE class_id = ?";
                try (Connection con2 = JDBCConnectionConfig.getConnection();
                     PreparedStatement ps2 = con2.prepareStatement(sql)) {
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
}
