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
public class Comment {

    String user, blogPost, description, image;
    Date timePost;
    int id, like;

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }
    
    public Comment() {
    }

    public Comment(String user, String blogPost, String description, Date timePost, String image, int id, int like) {
        this.user = user;
        this.blogPost = blogPost;
        this.description = description;
        this.timePost = timePost;
        this.image = image;
        this.id = id;
        this.like = like;
    }

    public Comment(String user, String blogPost, String description, Date timePost, int id) {
        this.user = user;
        this.blogPost = blogPost;
        this.description = description;
        this.timePost = timePost;
        this.id = id;
    }

    public Comment(String user, String blogPost, String description, String image, Date timePost, int id) {
        this.user = user;
        this.blogPost = blogPost;
        this.description = description;
        this.image = image;
        this.timePost = timePost;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getBlogPost() {
        return blogPost;
    }

    public void setBlogPost(String blogPost) {
        this.blogPost = blogPost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getTimePost() {
        return timePost;
    }

    public void setTimePost(Date timePost) {
        this.timePost = timePost;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ArrayList<Comment> getCommentByPostId(int a) {
        ArrayList<Comment> list = new ArrayList<>();
        try {
            String sql = "select [user_id],tblUser.name as [User Name], "
                    + "tblUser.image as image,tblComment.timePost,"
                    + "blog_post_id, tblBlogPost.title as Title, tblComment.description,"
                    + " tblComment.id, [like]\n"
                    + "from tblComment join tblUser on tblComment.[user_id] = tblUser.id\n"
                    + "join tblBlogPost on tblBlogPost.id = tblComment.blog_post_id "
                    + "where blog_post_id = ?";
            Connection cnn = new DBContext().getConnection();
            PreparedStatement ps = cnn.prepareStatement(sql);
            ps.setInt(1, a);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String user = rs.getString("User Name");
                String title = rs.getString("Title");
                String description = rs.getString("description");
                Date timePost = rs.getTimestamp("timePost");
                String image = rs.getString("image");
                int like = rs.getInt("like");
                Comment comment = new Comment(user, title, description, timePost, image, id, like);
                list.add(comment);
            }
        } catch (Exception e) {
            System.out.println("Lỗi getCommentByPostId: " + e.getMessage());
        }
        return list;
    }

    public ArrayList<Comment> getCommentByUserId(int id) {
        ArrayList<Comment> list = new ArrayList<>();
        try {
            String sql = "select ROW_NUMBER() over (order by id asc) as RowNumber, * \n"
                    + "from(\n"
                    + "    select tblComment.id,[user_id],tblUser.name as [User Name], tblUser.image as image,tblComment.timePost,\n"
                    + "    blog_post_id, tblBlogPost.title as Title, tblComment.description\n"
                    + "    from tblComment join tblUser on tblComment.[user_id] = tblUser.id\n"
                    + "    join tblBlogPost on tblBlogPost.id = tblComment.blog_post_id \n"
                    + "	where [user_id] = ?\n"
                    + ") as T";
            Connection cnn = new DBContext().getConnection();
            PreparedStatement ps = cnn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int comment_id = rs.getInt("RowNumber");
                String user = rs.getString("User Name");
                String title = rs.getString("Title");
                String description = rs.getString("description");
                Date timePost = rs.getTimestamp("timePost");
                String image = rs.getString("image");
                Comment comment = new Comment(user, title, description, image, timePost, comment_id);
                list.add(comment);
            }
        } catch (Exception e) {
            System.out.println("Lỗi getCommentByUserId: " + e.getMessage());
        }
        return list;
    }

    public void addComment(String txt, int a, int b) {
        try {
            String sql = "insert into tblComment(user_id, blog_post_id, timePost"
                    + ", description) values(?, ?, CURRENT_TIMESTAMP, ?)";
            Connection con = new DBContext().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, a);
            ps.setInt(2, b);
            ps.setString(3, txt);
            ps.executeQuery();
            System.out.println(ps.executeUpdate());
        } catch (Exception e) {
            System.out.println("Error on adding new comment: " + e.getMessage());
        }
    }

    public ArrayList<Comment> getListCommentAutherAccount(String username) {
        ArrayList<Comment> list = new ArrayList<>();
        try {
            String sql = "select tblComment.id as Comment_ID, tblUser.username as [User],"
                    + " tblBlogPost.title as Blog_Title, tblComment.description as [description],"
                    + " tblComment.timePost as Time_Post, tblAdmin.username as Author_account\n"
                    + "from tblAdmin join tblBlogPost on tblAdmin.id = tblBlogPost.author_id\n"
                    + "join tblComment on tblBlogPost.id = tblComment.blog_post_id\n"
                    + "join tblUser on tblComment.user_id = tblUser.id\n"
                    + "where tblAdmin.username = ?";
            Connection con = new DBContext().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String user = rs.getString(2);
                String title = rs.getString(3);
                String des = rs.getString(4);
                Date timePost = rs.getTimestamp(5);
                Comment comment = new Comment(user, title, des, timePost, id);
                list.add(comment);
            }
        } catch (Exception e) {
            System.out.println("getListCommentAutherAccount:" + e.getMessage());
        }
        return list;
    }

    public void deleteComment(int id) {
        try {
            String sql = "DELETE FROM [dbo].[tblComment]\n"
                    + "      WHERE id = ?";
            Connection con = new DBContext().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            System.out.println(ps.executeUpdate());
        } catch (Exception e) {
            System.out.println("deleteComment:" + e.getMessage());
        }
    }

    public void updateLike(int cmtID, int like) {
        try {
            String sql = "update tblComment set [like] = ? where id = ?";
            Connection con = new DBContext().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, like);
            ps.setInt(2, cmtID);
            System.out.println(ps.executeUpdate());
        } catch (Exception e) {
            System.out.println("error updateLike: " + e.getMessage());
        }
    }
}
