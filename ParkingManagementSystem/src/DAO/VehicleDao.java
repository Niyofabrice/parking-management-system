/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import MODELS.Vehicle;
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
public class VehicleDao {
    private String jdbcUrl = "jdbc:postgresql://localhost:5432/parking_management_system_db";
    private String dbUsername = "postgres";
    private String dbPassword = "Faundaiton10990";

    // INSERT VEHICLE
    public Integer addVehicle(Vehicle vehicle){
        try{
            Connection con = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);
            String sql = "INSERT INTO vehicle (plate_number, vehicle_type, owner_name, phone) VALUES (?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, vehicle.getPlateNumber());
            pst.setString(2, vehicle.getVehicleType());
            pst.setString(3, vehicle.getOwnerName());
            pst.setString(4, vehicle.getPhone());

            int rowsAffected = pst.executeUpdate();
            con.close();
            return rowsAffected;

        }catch(Exception ex){
            ex.printStackTrace();
            return 0;
        }
    }

    // FIND VEHICLE BY PLATE NUMBER
    public Vehicle findVehicleByPlate(String plate){
        try{
            Connection con = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);
            String sql = "SELECT * FROM vehicle WHERE plate_number=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, plate);

            ResultSet rs = pst.executeQuery();
            Vehicle v = null;

            if(rs.next()){
                v = new Vehicle();
                v.setVehicleId(rs.getInt("vehicle_id"));
                v.setPlateNumber(rs.getString("plate_number"));
                v.setVehicleType(rs.getString("vehicle_type"));
                v.setOwnerName(rs.getString("owner_name"));
                v.setPhone(rs.getString("phone"));
            }

            con.close();
            return v;

        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    // LIST ALL VEHICLES
    public List<Vehicle> getAllVehicles(){
        try{
            Connection con = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);
            String sql = "SELECT * FROM vehicle";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            List<Vehicle> list = new ArrayList<>();

            while(rs.next()){
                Vehicle v = new Vehicle();
                v.setVehicleId(rs.getInt("vehicle_id"));
                v.setPlateNumber(rs.getString("plate_number"));
                v.setVehicleType(rs.getString("vehicle_type"));
                v.setOwnerName(rs.getString("owner_name"));
                v.setPhone(rs.getString("phone"));

                list.add(v);
            }

            con.close();
            return list;

        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
}
