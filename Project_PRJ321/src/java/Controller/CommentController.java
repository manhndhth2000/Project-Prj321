/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.BlogPost;
import Model.Comment;
import Model.LikeAndUnlike;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ADMIN
 */
public class CommentController extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String option = request.getParameter("submit");
            String comment = request.getParameter("comment");
            System.out.println(request.getParameter("blog_id"));
            int blog_id = Integer.parseInt(request.getParameter("blog_id"));
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            int user_id = user.getUserId(user.getUsername());
            if (option.equals("Send")) {
                new Comment().addComment(comment, user_id, blog_id);
                ArrayList<Comment> list = new Comment().getCommentByPostId(blog_id);
                request.setAttribute("listComment", list);
                if (session.getAttribute("admin") == null) {
                    ArrayList<LikeAndUnlike> listLiked = new LikeAndUnlike().getUserLiked(blog_id);
                    User u = (User) session.getAttribute("user");
                    boolean check = false;
                    int cmID = 0;
                    int userid = u.getId();
                    for (LikeAndUnlike obj : listLiked) {
                        if (obj.getUserID() == userid) {
                            check = true;
                            cmID = obj.getCmtID();
                        }
                    }
                    request.setAttribute("check", check);
                    request.setAttribute("cmID", cmID);
                }
                request.setAttribute("BlogPost", new BlogPost().getPostById(blog_id));
                request.getRequestDispatcher("View/Detail.jsp").forward(request, response);
            }
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
        processRequest(request, response);
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
        processRequest(request, response);
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
