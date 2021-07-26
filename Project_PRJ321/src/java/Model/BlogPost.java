/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class BlogPost {

    String category, author, title, description, image, shortDes;
    Date timePost;
    int id;

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public BlogPost() {
    }

    public BlogPost(String category, String author, String title, String description, String image, String shortDes, Date timePost) {
        this.category = category;
        this.author = author;
        this.title = title;
        this.description = description;
        this.image = image;
        this.shortDes = shortDes;
        this.timePost = timePost;
    }

    public BlogPost(String category, String author, String title, String description, String image, String shortDes, Date timePost, int id) {
        this.category = category;
        this.author = author;
        this.title = title;
        this.description = description;
        this.image = image;
        this.shortDes = shortDes;
        this.timePost = timePost;
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getShortDes() {
        return shortDes;
    }

    public void setShortDes(String shortDes) {
        this.shortDes = shortDes;
    }

    public Date getTimePost() {
        return timePost;
    }

    public void setTimePost(Date timePost) {
        this.timePost = timePost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<BlogPost> getTop3ListPost() {
        ArrayList<BlogPost> list = new ArrayList<BlogPost>();
        String sql = "select top 3 tblCategory.name as CategoryName, tblAdmin.name as Author,"
                + " title, description, tblBlogPost.image, timePost, shortDes "
                + "from tblBlogPost join tblAdmin on tblBlogPost.author_id = tblAdmin.id "
                + "join tblCategory on tblBlogPost.category_id = tblCategory.id"
                + " order by timePost desc";
        try {
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String categoryname = rs.getString(1);
                String author = rs.getString(2);
                String title = rs.getString(3);
                String description = rs.getString(4);
                String image = rs.getString(5);
                Date timePost = rs.getTimestamp(6);
                String shortDes = rs.getString(7);
                BlogPost blog = new BlogPost(category, author, title, description, image, shortDes, timePost);
                list.add(blog);
            }
            return list;
        } catch (Exception e) {
            System.out.println("Lỗi :" + e.getMessage());
        }
        return null;
    }

    public int getListNUmber() {
        try {
            String sql = "select count(*) from tblBlogPost";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
        return 0;
    }

    public int getListNUmber(String s) {
        try {
            String sql = "select count(*) from tblBlogPost where title like ?";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + s + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
        return 0;
    }

    public int getListNUmberByAuthor(String s) {
        try {
            String sql = "select count(*) from (\n"
                    + "	select tblCategory.name as CategoryName, tblAdmin.name as Author,\n"
                    + "	title, description, tblBlogPost.image, timePost, shortDes \n"
                    + "	from tblBlogPost join tblAdmin on tblBlogPost.author_id = tblAdmin.id \n"
                    + "    join tblCategory on tblBlogPost.category_id = tblCategory.id\n"
                    + "    where tblAdmin.name like ?) as t";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + s + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
        return 0;
    }

    public int getListNUmberByCategory(String s) {
        try {
            String sql = "select count(*) from (\n"
                    + "	select tblCategory.name as CategoryName, tblAdmin.name as Author,\n"
                    + "	title, description, tblBlogPost.image, timePost, shortDes \n"
                    + "	from tblBlogPost join tblAdmin on tblBlogPost.author_id = tblAdmin.id \n"
                    + "    join tblCategory on tblBlogPost.category_id = tblCategory.id\n"
                    + "    where tblCategory.name like ?) as t";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + s + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
        return 0;
    }

    public int getListNUmberByTitle(String s) {
        try {
            String sql = "select count(*) from (\n"
                    + "	select tblCategory.name as CategoryName, tblAdmin.name as Author,\n"
                    + "	title, description, tblBlogPost.image, timePost, shortDes \n"
                    + "	from tblBlogPost join tblAdmin on tblBlogPost.author_id = tblAdmin.id \n"
                    + "    join tblCategory on tblBlogPost.category_id = tblCategory.id\n"
                    + "    where title like ?) as t";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + s + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
        return 0;
    }

    public ArrayList<BlogPost> getListNUmber(int index) {
        ArrayList<BlogPost> list = new ArrayList<BlogPost>();
        try {
            String sql = "select * from \n"
                    + "(select ROW_NUMBER() over (order by id asc) as RowNumber, * \n"
                    + "	from(\n"
                    + "		select tblBlogPost.id,tblCategory.name as CategoryName, tblAdmin.name as Author, title, description, tblBlogPost.image, timePost, shortDes\n"
                    + "		from tblBlogPost join tblAdmin on tblBlogPost.author_id = tblAdmin.id \n"
                    + "		join tblCategory on tblBlogPost.category_id = tblCategory.id\n"
                    + "	) as T \n"
                    + ") as x where RowNumber between ?*3-2 and ?*3";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, index);
            ps.setInt(2, index);
            rs = ps.executeQuery();
            while (rs.next()) {
                String categoryname = rs.getString(3);
                String author = rs.getString(4);
                String title = rs.getString(5);
                String description = rs.getString(6);
                String image = rs.getString(7);
                Date timePost = rs.getTimestamp(8);
                String shortDes = rs.getString(9);
                BlogPost blog = new BlogPost(category, author, title, description, image, shortDes, timePost);
                list.add(blog);
            }
            return list;
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
        return null;
    }

    public ArrayList<BlogPost> getListByCategoryName(String txt, int index) {
        ArrayList<BlogPost> list = new ArrayList<>();
        try {
            String sql = "select * from (\n"
                    + "	select ROW_NUMBER() over (order by id asc) as RowNumber, * \n"
                    + "	from(\n"
                    + "		select tblBlogPost.id,tblCategory.name as CategoryName, tblAdmin.name as Author, title, description, tblBlogPost.image, timePost, shortDes\n"
                    + "		from tblBlogPost join tblAdmin on tblBlogPost.author_id = tblAdmin.id \n"
                    + "		join tblCategory on tblBlogPost.category_id = tblCategory.id\n"
                    + "	) as T \n"
                    + "	where CategoryName like ?\n"
                    + ") as x where RowNumber between ?*3-2 and ?*3";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + txt + "%");
            ps.setInt(2, index);
            ps.setInt(3, index);
            rs = ps.executeQuery();
            while (rs.next()) {
                String categoryname = rs.getString(3);
                String author = rs.getString(4);
                String title = rs.getString(5);
                String description = rs.getString(6);
                String image = rs.getString(7);
                Date timePost = rs.getTimestamp(8);
                String shortDes = rs.getString(9);
                BlogPost blog = new BlogPost(category, author, title, description, image, shortDes, timePost);
                list.add(blog);
            }
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
        return list;
    }

    public BlogPost getPostByTitle(String txt, java.sql.Timestamp time) {
        BlogPost blog;
        try {
            String sql = "select tblBlogPost.id,tblCategory.name as CategoryName, tblAdmin.name as Author,\n"
                    + "title, description, tblBlogPost.image, timePost, shortDes \n"
                    + "from tblBlogPost join tblAdmin on tblBlogPost.author_id = tblAdmin.id \n"
                    + "join tblCategory on tblBlogPost.category_id = tblCategory.id\n"
                    + "where title = ? and timePost = ?";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, txt);
            ps.setTimestamp(2, time);
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("true");
                int id = rs.getInt(1);
                String categoryname = rs.getString(2);
                String author = rs.getString(3);
                String title = rs.getString(4);
                String description = rs.getString(5);
                String image = rs.getString(6);
                Date timePost = rs.getTimestamp(7);
                String shortDes = rs.getString(8);
                blog = new BlogPost(categoryname, author, title, description, image, shortDes, timePost, id);
                return blog;
            }
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
        return null;
    }

    public BlogPost getPostById(int a) {
        BlogPost blog;
        try {
            String sql = "select tblBlogPost.id,tblCategory.name as CategoryName, tblAdmin.name as Author,\n"
                    + "title, description, tblBlogPost.image, timePost, shortDes \n"
                    + "from tblBlogPost join tblAdmin on tblBlogPost.author_id = tblAdmin.id \n"
                    + "join tblCategory on tblBlogPost.category_id = tblCategory.id\n"
                    + "where tblBlogPost.id = ?";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, a);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String categoryname = rs.getString(2);
                String author = rs.getString(3);
                String title = rs.getString(4);
                String description = rs.getString(5);
                String image = rs.getString(6);
                Date timePost = rs.getTimestamp(7);
                String shortDes = rs.getString(8);
                blog = new BlogPost(categoryname, author, title, description, image, shortDes, timePost, id);
                return blog;
            }
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
        return null;
    }

    public ArrayList<BlogPost> getListByAuthor(String txt) {
        ArrayList<BlogPost> list = new ArrayList<BlogPost>();
        try {
            String sql = "select tblCategory.name as CategoryName, tblAdmin.name as Author,\n"
                    + "title, description, tblBlogPost.image, timePost, shortDes \n"
                    + "from tblBlogPost join tblAdmin on tblBlogPost.author_id = tblAdmin.id \n"
                    + "join tblCategory on tblBlogPost.category_id = tblCategory.id\n"
                    + "where tblAdmin.name like ?";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + txt + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                String categoryname = rs.getString(1);
                String author = rs.getString(2);
                String title = rs.getString(3);
                String description = rs.getString(4);
                String image = rs.getString(5);
                Date timePost = rs.getTimestamp(6);
                String shortDes = rs.getString(7);
                BlogPost blog = new BlogPost(categoryname, author, title, description, image, shortDes, timePost);
                list.add(blog);
            }
            return list;
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
        return null;
    }

    public ArrayList<BlogPost> getListByTitle(String txt, int index) {
        ArrayList<BlogPost> list = new ArrayList<BlogPost>();
        try {
            String sql = "select * from (\n"
                    + "	select ROW_NUMBER() over (order by id asc) as RowNumber, * \n"
                    + "	from(\n"
                    + "		select tblBlogPost.id,tblCategory.name as CategoryName, tblAdmin.name as Author, title, description, tblBlogPost.image, timePost, shortDes\n"
                    + "		from tblBlogPost join tblAdmin on tblBlogPost.author_id = tblAdmin.id \n"
                    + "		join tblCategory on tblBlogPost.category_id = tblCategory.id\n"
                    + "	) as T \n"
                    + "	where title like ?\n"
                    + ") as x where RowNumber between ?*3-2 and ?*3";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + txt + "%");
            ps.setInt(2, index);
            ps.setInt(3, index);
            rs = ps.executeQuery();
            while (rs.next()) {
                String categoryname = rs.getString(3);
                String author = rs.getString(4);
                String title = rs.getString(5);
                String description = rs.getString(6);
                String image = rs.getString(7);
                Date timePost = rs.getTimestamp(8);
                String shortDes = rs.getString(9);
                BlogPost blog = new BlogPost(category, author, title, description, image, shortDes, timePost);
                list.add(blog);
            }
            return list;
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
        return null;
    }

    public ArrayList<BlogPost> getListByAuthor(String txt, int index) {
        ArrayList<BlogPost> list = new ArrayList<BlogPost>();
        try {
            String sql = "select * from (\n"
                    + "	select ROW_NUMBER() over (order by id asc) as RowNumber, * \n"
                    + "	from(\n"
                    + "		select tblBlogPost.id,tblCategory.name as CategoryName, tblAdmin.name as Author, title, description, tblBlogPost.image, timePost, shortDes\n"
                    + "		from tblBlogPost join tblAdmin on tblBlogPost.author_id = tblAdmin.id \n"
                    + "		join tblCategory on tblBlogPost.category_id = tblCategory.id\n"
                    + "	) as T \n"
                    + "	where Author like ?\n"
                    + ") as x where RowNumber between ?*3-2 and ?*3";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + txt + "%");
            ps.setInt(2, index);
            ps.setInt(3, index);
            rs = ps.executeQuery();
            while (rs.next()) {
                String categoryname = rs.getString(3);
                String author = rs.getString(4);
                String title = rs.getString(5);
                String description = rs.getString(6);
                String image = rs.getString(7);
                Date timePost = rs.getTimestamp(8);
                String shortDes = rs.getString(9);
                BlogPost blog = new BlogPost(category, author, title, description, image, shortDes, timePost);
                list.add(blog);
            }
            return list;
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
        return null;
    }

    public void deletePost(String s, String txt) {
        try {
            String sql = "delete from tblBlogPost where title = ? and timePost = ?";
            Connection con = new DBContext().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, s);
            ps.setString(2, txt);
            System.out.println(ps.executeUpdate());
        } catch (Exception e) {
            System.out.println("delete post: " + e.getMessage());
        }
    }

    public void updatePost(String title, String category, String shortDes, String description, int id) {
        try {
            String sql = "update tblBlogPost \n"
                    + "set category_id = (select id from tblCategory where [name] = ?),\n"
                    + "title = ?, shortDes = ?, description = ? \n"
                    + "where id = ?";
            Connection con = new DBContext().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, category);
            ps.setString(2, title);
            ps.setString(3, shortDes);
            ps.setString(4, description);
            ps.setInt(5, id);
            System.out.println(ps.executeUpdate());
        } catch (Exception e) {
            System.out.println("update post error: " + e.getMessage());
        }
    }

    public void updatePost(String nameimg, String titleReq, String timePost) {
        try {
            String sql = "update tblBlogPost \n"
                    + "set image = ? \n"
                    + "where title=? and timePost = ?";
            Connection con = new DBContext().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nameimg);
            ps.setString(2, titleReq);
            ps.setString(3, timePost);
            System.out.println(ps.executeUpdate());
        } catch (Exception e) {
            System.out.println("update post error: " + e.getMessage());
        }
    }

    public BlogPost getPostByTitle(String txt, String time) {
        BlogPost blog;
        try {
            String sql = "select tblBlogPost.id,tblCategory.name as CategoryName, tblAdmin.name as Author,\n"
                    + "title, description, tblBlogPost.image, timePost, shortDes \n"
                    + "from tblBlogPost join tblAdmin on tblBlogPost.author_id = tblAdmin.id \n"
                    + "join tblCategory on tblBlogPost.category_id = tblCategory.id\n"
                    + "where title = ? and timePost = ?";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, txt);
            ps.setString(2, time);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String categoryname = rs.getString(2);
                String author = rs.getString(3);
                String title = rs.getString(4);
                String description = rs.getString(5);
                String image = rs.getString(6);
                Date timePost = rs.getTimestamp(7);
                String shortDes = rs.getString(8);
                blog = new BlogPost(categoryname, author, title, description, image, shortDes, timePost, id);
                return blog;
            }
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
        return null;
    }

    public void addPost(String title, int category_id, int author_id, String shortdes, String image, String des) {
        System.out.println(category_id + " " + author_id + " " + title + " " + des + " " + image + " " + shortdes);
        try {
            String sql = "INSERT INTO [dbo].[tblBlogPost]\n"
                    + "           ([category_id]\n"
                    + "           ,[author_id]\n"
                    + "           ,[title]\n"
                    + "           ,[description]\n"
                    + "           ,[image]\n"
                    + "           ,[timePost]\n"
                    + "           ,[shortDes])\n"
                    + "     VALUES(?,?,?,?,?,CURRENT_TIMESTAMP,?)";
            Connection con = new DBContext().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, category_id);
            ps.setInt(2, author_id);
            ps.setString(3, title);
            ps.setString(4, des);
            ps.setString(5, image);
            ps.setString(6, shortdes);
            System.out.println(ps.executeUpdate());
        } catch (Exception e) {
            System.out.println("add new post: " + e.getMessage());
        }
    }

    public BlogPost getNewPost() {
        try {
            String sql = " select top 1 tblCategory.name as category_name,"
                    + " tblAdmin.name as author_name, tblBlogPost.title, "
                    + "tblBlogPost.[description], tblBlogPost.[image],"
                    + " tblBlogPost.timePost, tblBlogPost.shortDes, tblBlogPost.id   \n"
                    + " from tblBlogpost join tblAdmin on tblBlogPost.author_id = tblAdmin.id\n"
                    + " join tblCategory on tblBlogPost.category_id = tblCategory.id\n"
                    + " order by id desc";
            Connection con = new DBContext().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String category = rs.getString(1);
                String author = rs.getString(2);
                String title = rs.getString(3);
                String des = rs.getString(4);
                String image = rs.getString(5);
                Date timepost = rs.getTimestamp(6);
                String shortDes = rs.getString(7);
                BlogPost blogPost = new BlogPost(category, author, title, des, image, shortDes, timepost);
                return blogPost;
            }
        } catch (Exception e) {
            System.out.println("get new post: " + e.getMessage());

        }
        return null;
    }
}
