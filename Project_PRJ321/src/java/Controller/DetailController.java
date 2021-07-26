/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Admin;
import Model.BlogPost;
import Model.Comment;
import Model.LikeAndUnlike;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "DetailController", urlPatterns = {"/DetailController"})
public class DetailController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            System.out.println(request.getParameter("timePost"));
            String title = request.getParameter("title");
            Date time = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(request.getParameter("timePost"));
            java.sql.Timestamp timePost = new java.sql.Timestamp(time.getTime());
            BlogPost blog = new BlogPost().getPostByTitle(title, timePost);
            request.setAttribute("BlogPost", blog);
            HttpSession session = request.getSession();
            if (session.getAttribute("admin") != null) {
                Admin admin = (Admin) session.getAttribute("admin");
                if (admin.getName().equals(blog.getAuthor())) {
                    request.setAttribute("bool", "true");
                } else {
                    request.setAttribute("bool", "false");
                }
            } else{
                request.setAttribute("bool", "false");
            }
            if (session.getAttribute("admin") == null) {
                ArrayList<LikeAndUnlike> listLiked = new LikeAndUnlike().getUserLiked(blog.getId());
                ArrayList<LikeAndUnlike> user_liked = new ArrayList<>();
                User u = (User) session.getAttribute("user");
                boolean check = false;
                int cmID = 0;
                int userid = u.getId();
                for (LikeAndUnlike obj : listLiked) {
                    if (obj.getUserID() == userid) {
                        check = true;
                        user_liked.add(obj);
                    }
                }
                request.setAttribute("check", check);
                request.setAttribute("user_liked", user_liked);
            }
            ArrayList<Comment> listComment = new Comment().getCommentByPostId(blog.getId());
            request.setAttribute("listComment", listComment);
            request.getRequestDispatcher("View/Detail.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(DetailController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(DetailController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
