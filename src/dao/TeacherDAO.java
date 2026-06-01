package dao;

import config.JDBCConnectionConfig;
import model.Teacher;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherDAO {
    public  void addTeacher(Teacher t) {
        String sql = "INSERT INTO teacher (teacher_id, teacher_name, teacher_age, teacher_gender, teacher_address, teacher_salary, teacher_exp) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = JDBCConnectionConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, t.getId());
            ps.setString(2, t.getName());
            ps.setInt(3, t.getAge());
            ps.setString(4, t.getGender());
            ps.setString(5, t.getAddress());
            ps.setDouble(6, t.getSalary());
            ps.setInt(7, t.getExp());
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
    public  void updateTeacher(Teacher t) {
        String sql = "UPDATE teacher SET teacher_name = ?, teacher_age = ?, teacher_gender = ?, teacher_address = ?, teacher_salary = ?, teacher_exp = ? WHERE teacher_id = ?";
        try (Connection con = JDBCConnectionConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, t.getName());
            ps.setInt(2, t.getAge());
            ps.setString(3, t.getGender());
            ps.setString(4, t.getAddress());
            ps.setDouble(5, t.getSalary());
            ps.setInt(6, t.getExp());
            ps.setString(7, t.getId());
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
    public  List<Teacher> getListTeacher() {
        List<Teacher> teach = new ArrayList<>();
        String sql = "SELECT * FROM teacher";
        try (Connection con = JDBCConnectionConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Teacher t = new Teacher();
                t.setId(rs.getString("teacher_id"));
                t.setName(rs.getString("teacher_name"));
                t.setAge(rs.getInt("teacher_age"));
                t.setGender(rs.getString("teacher_gender"));
                t.setAddress(rs.getString("teacher_address"));
                t.setSalary(rs.getDouble("teacher_salary"));
                t.setExp(rs.getInt("teacher_exp"));
                teach.add(t);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return teach;
    }
    public  void deleteTeacher(String id) {
        String sql = "DELETE FROM teacher WHERE teacher_id = ?";
        try (Connection con = JDBCConnectionConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
}