/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class User {

    String username, password, email, name, gender, address, dateofbirth, image;
    Date dateDefault = new Date();
    int id;

    public User() {
        connect();
    }

    public User(String username, String password, String email, String name, String gender, String address, String dateofbirth, String image, int id) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.dateofbirth = dateofbirth;
        this.image = image;
        this.id = id;
    }

    public User(String username, String password, String email, String name, String image) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.image = image;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(String dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    

    private void connect() {
        try {
            Connection cnn = (new DBContext().getConnection());
            System.out.println("Connect successfully");
        } catch (Exception e) {
            System.out.println("Lỗi connect: " + e.getMessage());
        }
    }

    public boolean checkExist(String txt) {
        try {
            String sql = "select * from tblUser where username = ?";
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

    public void addUser(String username, String password, String name, String email) {
        try {
            String sql = "insert into tblUser(username, password, email, name, image) values(?,?,?,?,?)";
            Connection con = new DBContext().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, email);
            ps.setString(4, name);
            ps.setString(5, "anhdaidien.jpg");
            System.out.println(ps.executeUpdate());
        } catch (Exception e) {
            System.out.println("Login error: " + e.getMessage());
        }
    }

    public User getAllInforByUsername(String txt) {
        try {
            String sql = "select * from tblUser where username = ?";
            Connection con = new DBContext().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, txt);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String image = rs.getString("image");
                String gender = rs.getString("gender") == null ? "" : rs.getString("gender");
                String address = rs.getString("address") == null ? "" : rs.getString("address");
                String dob = new SimpleDateFormat("yyyy-MM-dd").format(rs.getDate("dateofbirth") == null ? dateDefault : rs.getDate("dateofbirth"));
                User user = new User(username, password, email, name, gender, address, dob, image, id);
                return user;
            }
        } catch (Exception e) {
            System.out.println("Login error: " + e.getMessage());
        }
        return null;
    }

    public int getUserId(String txt) {
        try {
            String sql = "select * from tbluser where username = ?";
            Connection con = new DBContext().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, txt);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
        return 0;
    }

    public void updateUser(String name, String email, String gender, String address, String dob, String username) {
        try {
            String sql = "update tblUser set email = ?, name = ?, gender = ?,"
                    + " address = ?, dateofbirth = ? where username = ?";
            Connection con = new DBContext().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, name);
            ps.setString(3, gender);
            ps.setString(4, address);
            Date time = new SimpleDateFormat("yyyy-MM-dd").parse(dob);
            java.sql.Date timePost = new java.sql.Date(time.getTime());
            ps.setDate(5, timePost);
            ps.setString(6, username);
            System.out.println(ps.executeUpdate());
        } catch (Exception e) {
            System.out.println("Update user: " + e.getMessage());
        }
    }

    public void updateImage(String image, String txt) {
        try {
            String sql = "update tblUser set image = ? where username = ?";
            Connection con = new DBContext().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, image);
            ps.setString(2, txt);
            System.out.println(ps.executeUpdate());
        } catch (Exception e) {
            System.out.println("Update user: " + e.getMessage());
        }
    }
}
