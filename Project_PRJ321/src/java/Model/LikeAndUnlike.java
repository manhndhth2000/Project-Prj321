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
public class LikeAndUnlike {

    int cmtID, userID;

    public LikeAndUnlike() {
    }

    public LikeAndUnlike(int cmtID, int userID) {
        this.cmtID = cmtID;
        this.userID = userID;
    }

    public int getCmtID() {
        return cmtID;
    }

    public void setCmtID(int cmtID) {
        this.cmtID = cmtID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public ArrayList<LikeAndUnlike> getUserLiked(int id) {
        ArrayList<LikeAndUnlike> list = new ArrayList<>();
        try {
            String sql = "select * from tblComment join LikeAndUnlike on id=cmtId where blog_post_id = ? ";
            Connection con = new DBContext().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int cmtID = rs.getInt("cmtId");
                int userID = rs.getInt("user");
                LikeAndUnlike likeAndUnlike = new LikeAndUnlike(cmtID, userID);
                list.add(likeAndUnlike);
            }
        } catch (Exception e) {
            System.out.println("getUserLiked: " + e.getMessage());
        }
        return list;
    }

    public void addLiked(int cmtID, int id) {
        try {
            String sql = "insert into LikeAndUnlike values(?,?)";
            Connection con = new DBContext().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, cmtID);
            ps.setInt(2, id);
            System.out.println(ps.executeQuery());
        } catch (Exception e) {
        }
    }

    public void deleteLiked(int cmtID, int id) {
        try {
            String sql = "delete from LikeAndUnlike where cmtId = ? and [user] = ?";
            Connection con = new DBContext().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, cmtID);
            ps.setInt(2, id);
            System.out.println(ps.executeQuery());
        } catch (Exception e) {
            System.out.println("deleteLiked: " + e.getMessage());
        }
    }
}
