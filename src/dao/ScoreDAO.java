package dao;

import config.JDBCConnectionConfig;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ScoreDAO {
    public void updateScore(String id, double math, double literature, double english) {
        String sql = "UPDATE student SET math_score = ?, literature_score = ?, english_score = ? WHERE student_id = ?";
        try (Connection con = JDBCConnectionConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDouble(1, math);
            ps.setDouble(2, literature);
            ps.setDouble(3, english);
            ps.setString(4, id);
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
}