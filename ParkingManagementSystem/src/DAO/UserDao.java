/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import MODELS.User;
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
public class UserDao {
    private String jdbcUrl = "jdbc:postgresql://localhost:5432/parking_management_system_db";
    private String dbUsername = "postgres";
    private String dbPassword = "Faundation10990";

    public Integer createUser(User userObj){
        try{
            Connection con = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);
            String sql = "INSERT INTO users (username, password, role, status) VALUES (?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, userObj.getUsername());
            pst.setString(2, userObj.getPassword());
            pst.setString(3, userObj.getRole());
            pst.setString(4, userObj.getStatus());

            int rowsAffected = pst.executeUpdate();
            con.close();
            return rowsAffected;

        }catch(Exception ex){
            ex.printStackTrace();
            return 0;
        }
    }

    public User login(String username, String password){
        try{
            Connection con = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);
            String sql = "SELECT * FROM users WHERE username=? AND password=? AND status='active'";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, password);

            ResultSet rs = pst.executeQuery();
            User user = null;

            if(rs.next()){
                user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                user.setStatus(rs.getString("status"));
            }

            con.close();
            return user;

        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    public List<User> findAllUsers(){
        try{
            Connection con = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);
            String sql = "SELECT * FROM users";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            List<User> users = new ArrayList<>();

            while(rs.next()){
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                user.setStatus(rs.getString("status"));

                users.add(user);
            }

            con.close();
            return users;

        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
}
