/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import MODELS.ParkingRecord;
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
public class ParkingRecordDao {
    private String jdbcUrl = "jdbc:postgresql://localhost:5432/parking_management_system_db";
    private String dbUsername = "postgres";
    private String dbPassword = "Faundation10990";


    public Integer createEntry(ParkingRecord pr){
        try{
            Connection con = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);
            String sql = "INSERT INTO parking_record (vehicle_id, slot_id, status) VALUES (?, ?, 'active')";
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setInt(1, pr.getVehicleId());
            pst.setInt(2, pr.getSlotId());

            int rows = pst.executeUpdate();
            con.close();
            return rows;

        }catch(Exception ex){
            ex.printStackTrace();
            return 0;
        }
    }


    public Integer completeExit(ParkingRecord record){
        try{
            Connection con = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);

            String sql = "UPDATE parking_record SET time_out=?, fee=?, status='completed' WHERE record_id=?";
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setObject(1, record.getTimeOut());
            pst.setDouble(2, record.getFee());
            pst.setInt(3, record.getRecordId());

            int rows = pst.executeUpdate();
            con.close();
            return rows;

        }catch(Exception ex){
            ex.printStackTrace();
            return 0;
        }
    }


    public ParkingRecord findActiveRecordByVehicle(int vehicleId){
        try{
            Connection con = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);

            String sql = "SELECT * FROM parking_record WHERE vehicle_id=? AND status='active'";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, vehicleId);

            ResultSet rs = pst.executeQuery();
            ParkingRecord pr = null;

            if(rs.next()){
                pr = new ParkingRecord(
                        rs.getInt("record_id"),
                        rs.getInt("vehicle_id"),
                        rs.getInt("slot_id"),
                        rs.getTimestamp("time_in").toLocalDateTime(),
                        null,
                        rs.getDouble("fee"),
                        rs.getString("status")
                );
            }

            con.close();
            return pr;

        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }


    public List<ParkingRecord> getActiveRecords(){
        try{
            Connection con = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);
            String sql = "SELECT * FROM parking_record WHERE status='active'";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            List<ParkingRecord> list = new ArrayList<>();

            while(rs.next()){
                ParkingRecord pr = new ParkingRecord(
                        rs.getInt("record_id"),
                        rs.getInt("vehicle_id"),
                        rs.getInt("slot_id"),
                        rs.getTimestamp("time_in").toLocalDateTime(),
                        null,
                        rs.getDouble("fee"),
                        rs.getString("status")
                );

                list.add(pr);
            }

            con.close();
            return list;

        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }


    public List<ParkingRecord> getAllRecords(){
        try{
            Connection con = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);
            String sql = "SELECT * FROM parking_record ORDER BY time_in DESC";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            List<ParkingRecord> list = new ArrayList<>();

            while(rs.next()){
                ParkingRecord pr = new ParkingRecord(
                        rs.getInt("record_id"),
                        rs.getInt("vehicle_id"),
                        rs.getInt("slot_id"),
                        rs.getTimestamp("time_in").toLocalDateTime(),
                        rs.getTimestamp("time_out") != null ? rs.getTimestamp("time_out").toLocalDateTime() : null,
                        rs.getDouble("fee"),
                        rs.getString("status")
                );

                list.add(pr);
            }

            con.close();
            return list;

        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
    
    public ParkingRecord findByVehicleNumber(String plateNumber) {
        try{
            Connection con = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);

            String sql = "SELECT * FROM parking_record WHERE plate_number=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, plateNumber);

            ResultSet rs = pst.executeQuery();
            ParkingRecord pr = null;

            if(rs.next()){
                pr = new ParkingRecord(
                        rs.getInt("record_id"),
                        rs.getInt("vehicle_id"),
                        rs.getInt("slot_id"),
                        rs.getTimestamp("time_in").toLocalDateTime(),
                        null,
                        rs.getDouble("fee"),
                        rs.getString("status")
                );
            }

            con.close();
            return pr;

        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
    
    public ResultSet getActiveParkingRecords(){
        try{
            Connection con = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);
            String sql = "SELECT V.plate_number, V.vehicle_type, S.slot_number, R.time_in " +
                 "FROM parking_record R " +
                 "JOIN vehicle V ON R.vehicle_id = V.vehicle_id " +
                 "JOIN parking_slot S ON R.slot_id = S.slot_id " +
                 "WHERE R.status = 'active'";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            con.close();
            return rs;

        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
}
