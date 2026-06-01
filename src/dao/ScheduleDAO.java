package dao;

import config.JDBCConnectionConfig;
import model.Schedule;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ScheduleDAO {
    public List<Schedule> getListSchedule() {
        List<Schedule> schedule = new ArrayList<>();
        String sql = "SELECT * FROM schedule";
        try (Connection con = JDBCConnectionConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery())
        {
            while (rs.next()) {
                Schedule s = new Schedule(rs.getString("class_id"), rs.getString("sub_id"), rs.getString("teacher_id"), rs.getString("room"), rs.getInt("day"), rs.getInt("slot"));
                schedule.add(s);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return schedule;
    }
    public void addSchedule(Schedule s) {
        String sql = "INSERT INTO schedule (class_id, sub_id, teacher_id, room, day, slot) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = JDBCConnectionConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql))
        {
            ps.setString(1, s.getClassId());
            ps.setString(2, s.getSubId());
            ps.setString(3, s.getTeacherId());
            ps.setString(4, s.getRoom());
            ps.setInt(5, s.getDay());
            ps.setInt(6, s.getSlot());
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
    public void updateSchedule(Schedule s) {
        String sql = "UPDATE schedule SET sub_id = ?, teacher_id = ?, room = ? WHERE class_id = ? AND day = ? AND slot = ?";
        try (Connection con = JDBCConnectionConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql))
        {
            ps.setString(1, s.getSubId());
            ps.setString(2, s.getTeacherId());
            ps.setString(3, s.getRoom());
            ps.setString(4, s.getClassId());
            ps.setInt(5, s.getDay());
            ps.setInt(6, s.getSlot());
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
    public void deleteSchedule(Schedule s) {
        String sql = "DELETE FROM schedule WHERE class_id = ? AND day = ? AND slot = ?";
        try (Connection con = JDBCConnectionConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql))
        {
            ps.setString(1, s.getClassId());
            ps.setInt(2, s.getDay());
            ps.setInt(3, s.getSlot());
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
}