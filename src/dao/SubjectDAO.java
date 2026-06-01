package dao;

import config.JDBCConnectionConfig;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectDAO {
    public  List<String> getSub(String teacherId) {
        List<String> subjects = new ArrayList<>();
        String sql = "SELECT sub_name FROM teacher_subjects WHERE teacher_id = ?";
        try (Connection con = JDBCConnectionConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, teacherId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                subjects.add(rs.getString("sub_name"));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return subjects;
    }
    public void addSub(String teacherId, String sub) {
        String sql = "INSERT INTO teacher_subjects (teacher_id, sub_name) VALUES (?, ?)";
        try (Connection con = JDBCConnectionConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, teacherId);
            ps.setString(2, sub);
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public  void deleteTeacherSubjects(String teacherId) {
        String sql = "DELETE FROM teacher_subjects WHERE teacher_id = ?";
        try (Connection con = JDBCConnectionConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, teacherId);
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
}