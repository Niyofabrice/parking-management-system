/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import MODELS.ParkingSlot;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fabrice
 */
public class ParkingSlotDao {
    private String jdbcUrl = "jdbc:postgresql://localhost:5432/parking_management_system_db";
    private String dbUsername = "postgres";
    private String dbPassword = "Faundation10990";

    public Integer createSlot(ParkingSlot slot){
        try{
            Connection con = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);
            String sql = "INSERT INTO parking_slot (slot_number, slot_type, is_occupied) VALUES (?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, slot.getSlotNumber());
            pst.setString(2, slot.getSlotType());
            pst.setBoolean(3, slot.isIsOccupied());

            int rows = pst.executeUpdate();
            con.close();
            return rows;

        }catch(Exception ex){
            ex.printStackTrace();
            return 0;
        }
    }


    public Integer updateOccupation(int slotId, boolean occupied){
        try{
            Connection con = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);
            String sql = "UPDATE parking_slot SET is_occupied=? WHERE slot_id=?";
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setBoolean(1, occupied);
            pst.setInt(2, slotId);

            int rows = pst.executeUpdate();
            con.close();
            return rows;

        }catch(Exception ex){
            ex.printStackTrace();
            return 0;
        }
    }
    
    
    public ParkingSlot findSlotById(int slotId){
        try{
            Connection con = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);
            String sql = "SELECT * FROM parking_slot WHERE slot_id=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, slotId);

            ResultSet rs = pst.executeQuery();
            ParkingSlot slot = null;

            if(rs.next()){
                slot = new ParkingSlot(
                        rs.getInt("slot_id"),
                        rs.getString("slot_number"),
                        rs.getString("slot_type"),
                        rs.getBoolean("is_occupied")
                );
            }

            con.close();
            return slot;

        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }


    public ParkingSlot findSlotByNumber(String slotNumber){
        try{
            Connection con = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);
            String sql = "SELECT * FROM parking_slot WHERE slot_number=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, slotNumber);

            ResultSet rs = pst.executeQuery();
            ParkingSlot slot = null;

            if(rs.next()){
                slot = new ParkingSlot(
                        rs.getInt("slot_id"),
                        rs.getString("slot_number"),
                        rs.getString("slot_type"),
                        rs.getBoolean("is_occupied")
                );
            }

            con.close();
            return slot;

        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }


    public List<ParkingSlot> getAllSlots(){
        try{
            Connection con = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);
            String sql = "SELECT * FROM parking_slot ORDER BY slot_number ASC";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            List<ParkingSlot> list = new ArrayList<>();

            while(rs.next()){
                ParkingSlot slot = new ParkingSlot(
                        rs.getInt("slot_id"),
                        rs.getString("slot_number"),
                        rs.getString("slot_type"),
                        rs.getBoolean("is_occupied")
                );
                list.add(slot);
            }

            con.close();
            return list;

        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }


    public List<ParkingSlot> getAvailableSlots(){
        try{
            Connection con = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);
            String sql = "SELECT * FROM parking_slot WHERE is_occupied=false ORDER BY slot_number ASC";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            List<ParkingSlot> list = new ArrayList<>();

            while(rs.next()){
                ParkingSlot slot = new ParkingSlot(
                        rs.getInt("slot_id"),
                        rs.getString("slot_number"),
                        rs.getString("slot_type"),
                        rs.getBoolean("is_occupied")
                );
                list.add(slot);
            }

            con.close();
            return list;

        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
    
    public ParkingSlot findFreeSlot() {
        try {
            List<ParkingSlot> list = new ArrayList<>();
            list = getAvailableSlots();
            return list.get(0);
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }


    public Integer deleteSlot(int slotId){
        try{
            Connection con = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);
            String sql = "DELETE FROM parking_slot WHERE slot_id=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, slotId);

            int rows = pst.executeUpdate();
            con.close();
            return rows;

        }catch(Exception ex){
            ex.printStackTrace();
            return 0;
        }
    }
    
    public int countSlots() {
        try {
            Connection con = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);
            String sql = "SELECT COUNT(*) AS total FROM parking_slot";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                return rs.getInt("total");
            }

            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }
    
    public ParkingSlot getLastSlot() {
        try {
            Connection con = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);
            String sql = "SELECT * FROM parking_slot ORDER BY slot_id DESC LIMIT 1";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                return new ParkingSlot(
                    rs.getInt("slot_id"),
                    rs.getString("slot_number"),
                    rs.getString("slot_type"),
                    rs.getBoolean("is_occupied")
                );
            }

            con.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public List<ParkingSlot> getSlotsByType(String type) {
        try {
            Connection con = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);
            String sql = "SELECT * FROM parking_slot WHERE slot_type=? ORDER BY slot_number ASC";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, type);

            ResultSet rs = pst.executeQuery();
            List<ParkingSlot> list = new ArrayList<>();

            while (rs.next()) {
                ParkingSlot slot = new ParkingSlot(
                    rs.getInt("slot_id"),
                    rs.getString("slot_number"),
                    rs.getString("slot_type"),
                    rs.getBoolean("is_occupied")
                );
                list.add(slot);
            }

            con.close();
            return list;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public List<ParkingSlot> getAvailableSlotsByType(String type) {
        try {
            Connection con = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);
            String sql = "SELECT * FROM parking_slot WHERE slot_type=? AND is_occupied=false ORDER BY slot_number ASC";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, type);

            ResultSet rs = pst.executeQuery();
            List<ParkingSlot> slots = new ArrayList<>();

            while (rs.next()) {
                ParkingSlot slot = new ParkingSlot(
                    rs.getInt("slot_id"),
                    rs.getString("slot_number"),
                    rs.getString("slot_type"),
                    rs.getBoolean("is_occupied")
                );
                slots.add(slot);
            }

            con.close();
            return slots;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public ParkingSlot findFreeSlotByType(String type) {
        try {
            List<ParkingSlot> availableSlots = getAvailableSlotsByType(type);

            if (availableSlots != null && !availableSlots.isEmpty()) {
                return availableSlots.get(0);
            }

            return null;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    
    public ResultSet getOccupiedSlots() {        
        try{
            Connection con = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);
            String sql = "SELECT v.plate_number, s.slot_number, s.slot_type, p.time_in "
                   + "FROM parking_record p JOIN parking_slot s ON p.slot_id = s.slot_id "
                   + "JOIN vehicle v ON p.vehicle_id = v.vehicle_id "
                   + "WHERE s.is_occupied = true";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            con.close();
            return rs;

        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
    
    public ResultSet getTodayRevenueDetails() {
        try{
            Connection con = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);
            String sql = "SELECT v.plate_number, p.time_in, p.time_out, p.fee "
                   + "FROM parking_record p JOIN vehicle v ON p.vehicle_id = v.vehicle_id "
                   + "WHERE DATE(p.time_out) = CURRENT_DATE";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            con.close();
            return rs;
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    public double getTodayTotalRevenue() {
        try{
            Connection con = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);
            String sql = "SELECT SUM(fee) AS total FROM parking_record WHERE DATE(time_out) = CURRENT_DATE";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getDouble("total");
            }
            con.close();
            return 0.0;
        }catch(Exception ex){
            ex.printStackTrace();
            return 0.0;
        }
    }
    
    public ResultSet getAvailableSlotsRs(){
        try{
            Connection con = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);
            String sql = "SELECT * FROM parking_slot WHERE is_occupied=false ORDER BY slot_number ASC";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            con.close();
            return rs;

        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
    
    
    public int countSlotsByType(String slotType) {
        try{
            Connection con = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);
            String sql = "SELECT COUNT(*) FROM parking_slot WHERE slot_type = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, slotType); 
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public List<ParkingSlot> findRemovableSlotsByType(String slotType, int limit) {
        try{
            List<ParkingSlot> slots = new ArrayList<>();
            String sql = "SELECT * FROM parking_slot WHERE slot_type = ? AND is_occupied = false ORDER BY slot_id DESC LIMIT ?";
            Connection con = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, slotType);
            pst.setInt(2, limit);

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    // Populate ParkingSlot object here (similar to your other DAO methods)
                    ParkingSlot slot = new ParkingSlot();
                    slot.setSlotId(rs.getInt("slot_id"));
                    slot.setSlotNumber(rs.getString("slot_number"));
                    slot.setSlotType(rs.getString("slot_type"));
                    slot.setIsOccupied(rs.getBoolean("is_occupied"));
                    slots.add(slot);
                }
            }
            return slots;
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
