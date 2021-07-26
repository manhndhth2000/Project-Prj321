/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class Admin {

    String username, password, name, information, email, image;
    Date dob;

    public Admin() {
    }

    public Admin(String username, String image) {
        this.username = username;
        this.image = image;
    }

    public Admin(String username, String password, String name, String information, String email, String image, Date dob) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.information = information;
        this.email = email;
        this.image = image;
        this.dob = dob;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public ArrayList<Admin> getAll() {
        ArrayList<Admin> list = new ArrayList<>();
        try {
            String sql = "Select * from tblAdmin";
            Connection cnn = new DBContext().getConnection();
            PreparedStatement ps = cnn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                String name = rs.getString("name");
                Date dob = rs.getDate("dob");
                String information = rs.getString("information");
                String email = rs.getString("email");
                String image = rs.getString("image");
                Admin admin = new Admin(username, password, name, information, email, image, dob);
                list.add(admin);
            }
        } catch (Exception e) {
            System.out.println("Loi: " + e.getMessage());
        }
        return list;
    }

    public Admin getAuthorByName(String txt) {
        try {
            String sql = "Select * from tblAdmin where name = ?";
            Connection cnn = new DBContext().getConnection();
            PreparedStatement ps = cnn.prepareStatement(sql);
            ps.setString(1, txt);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                String name = rs.getString("name");
                Date dob = rs.getDate("dob");
                String information = rs.getString("information");
                String email = rs.getString("email");
                String image = rs.getString("image");
                Admin admin = new Admin(username, password, name, information, email, image, dob);
                return admin;
            }
        } catch (Exception e) {
            System.out.println("Loi: " + e.getMessage());
        }
        return null;
    }

    public boolean checkExist(String txt) {
        try {
            String sql = "select * from tblAdmin where username = ?";
            Connection cnn = new DBContext().getConnection();
            PreparedStatement ps = cnn.prepareStatement(sql);
            ps.setString(1, txt);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Login fail: " + e.getMessage());
        }
        return false;
    }

    public Admin getAllInforByUsername(String txt) {
        try {
            String sql = "select * from tblAdmin where username = ?";
            Connection con = new DBContext().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, txt);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                String name = rs.getString("name");
                Date dob = rs.getDate("dob");
                String information = rs.getString("information");
                String email = rs.getString("email");
                String image = rs.getString("image");
                Admin admin = new Admin(username, password, name, information, email, image, dob);
                return admin;
            }
        } catch (Exception e) {
            System.out.println("Loi getAllInforAdmin: " + e.getMessage());
        }
        return null;
    }

    public void updateImage(String nameimg, String username) {
        try {
            String sql = "update tblAdmin set image = ? where username = ?";
            Connection con = new DBContext().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nameimg);
            ps.setString(2, username);
            System.out.println(ps.executeUpdate());
        } catch (Exception e) {
            System.out.println("Update admin image: " + e.getMessage());
        }
    }

    public void updateUser(String username, String name, String infor, String email, java.sql.Date dob) {
        try {
            String sql = "UPDATE [dbo].[tblAdmin]\n"
                    + "   SET [name] = ?\n"
                    + "      ,[dob] = ?\n"
                    + "      ,[information] = ?\n"
                    + "      ,[email] = ?\n"
                    + " WHERE [username] = ?";
            Connection con = new DBContext().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setDate(2, dob);
            ps.setString(3, infor);
            ps.setString(4, email);
            ps.setString(5, username);
            System.out.println(ps.executeUpdate());
        } catch (Exception e) {
            System.out.println("Update admin profile: " + e.getMessage());
        }
    }

    public int getAuthorByUsername(String username) {
        try {
            String sql = "select id from tblAdmin where username = ?";
            Connection con = new DBContext().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("get admin id: " + e.getMessage());
        }
        return 0;
    }

}
