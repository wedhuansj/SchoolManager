package dao;

import model.Student;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import config.JDBCConnectionConfig;

public class StudentDAO {
    public List<Student> getListStudent() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM student";
        try (Connection con = JDBCConnectionConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery())
        {
            while (rs.next()) {
                Student student = new Student();
                student.setName(rs.getString("student_name"));
                student.setId(rs.getString("student_id"));
                student.setAge(rs.getInt("student_age"));
                student.setGender(rs.getString("student_gender"));
                student.setAddress(rs.getString("student_address"));
                students.add(student);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return students;
    }
    public void addStudent(Student s) {
        String sql = "INSERT INTO student (student_id, student_name, student_age, student_gender, student_address) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = JDBCConnectionConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql))
        {
            ps.setString(1, s.getId());
            ps.setString(2, s.getName());
            ps.setInt(3, s.getAge());
            ps.setString(4, s.getGender());
            ps.setString(5, s.getAddress());
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
    public void updateStudent(Student s) {
        String sql = "UPDATE student SET student_name = ?, student_age = ?, student_gender = ?, student_address = ? WHERE student_id = ?";
        try (Connection con = JDBCConnectionConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql))
        {
            ps.setString(1, s.getName());
            ps.setInt(2, s.getAge());
            ps.setString(3, s.getGender());
            ps.setString(4, s.getAddress());
            ps.setString(5, s.getId());
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
    public void deleteStudent(String id) {
        String sql = "DELETE FROM student WHERE student_id = ?";
        try (Connection con = JDBCConnectionConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql))
        {
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
    public Student findById(String studentId) {
        String sql = "SELECT * FROM student WHERE student_id=?";
        try (Connection con = JDBCConnectionConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql))
        {
            ps.setString(1, studentId);
            try(ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    return mapSet(rs);
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }
    private Student mapSet(ResultSet rs) throws SQLException {
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
        return s;
    }
}