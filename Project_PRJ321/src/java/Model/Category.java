/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class Category {
    String name , shortDes  ;
    int id;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public Category(String name, String shortDes, int id) {
        this.name = name;
        this.shortDes = shortDes;
        this.id = id;
    }

    public String getShortDes() {
        return shortDes;
    }

    public void setShortDes(String shortDes) {
        this.shortDes = shortDes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public int getMaxCategory() {
        String sql = "select count(*) from tblAdmin join tblBlogPost on tblAdmin.id = tblBlogPost.author_id\n"
                + "join tblCategory on tblBlogPost.category_id = tblCategory.id";
        int count = 0;
        try {
            Connection con = new DBContext().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Lỗi :" + e.getMessage());
        }
        return 0;
    }
    
    public ArrayList<BlogPost> getAllCategory(){
        ArrayList list = new ArrayList();
        String sql = "select * from tblCategory order by id asc";
        try {
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String shortDes = rs.getString("shortDescription");
                Category category = new Category(name, shortDes, id);
                list.add(category);
            }
        } catch (Exception e) {
            System.out.println("Lỗi :" + e.getMessage());
        }
        return list;
    }
    
    public Category getCategoryByName(String txt){
        Category category;
        String sql = "select * from tblCategory where name like ?";
        try {
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%"+ txt + "%");
            rs = ps.executeQuery();
            while(rs.next()){
                String name = rs.getString(2);
                int id = rs.getInt(1);
                String shortDes = rs.getString(3);
                category = new Category(name, shortDes, id);
                return category;
            }
        } catch (Exception e) {
            System.out.println("Lỗi :" + e.getMessage());
        }
        return null;
    }

    public void addCategory(String nameString, String desString) {
        try {
            String sql = "insert into tblCategory values(?,?)";
            Connection con = new DBContext().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nameString);
            ps.setString(2, desString);
            System.out.println(ps.executeUpdate());
        } catch (Exception e) {
        }
    }

    public int getIdByName(String category) {
        try {
            String sql = "select id from tblCategory where name = ?";
            Connection con = new DBContext().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, category);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("get category id: " + e.getMessage());
        }
        return 0;
    }

    public void deleteCategoryById(int id) {
        try {
            String sql = "delete from tblCategory where id = ?";
            Connection con = new DBContext().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            System.out.println(ps.executeUpdate());
        } catch (Exception e) {
            System.out.println("delete category: " + e.getMessage());
        }
    }
}
