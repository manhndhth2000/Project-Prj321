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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "unlikeController", urlPatterns = {"/unlikeController"})
public class unlikeController extends HttpServlet {

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
            HttpSession session = request.getSession();
            User u = (User) session.getAttribute("user");
            int like = Integer.parseInt(request.getParameter("like"));
            int cmtID = Integer.parseInt(request.getParameter("id"));
            int postID = Integer.parseInt(request.getParameter("postId"));
            like = like - 1;
            Comment cmt = new Comment();
            cmt.updateLike(cmtID, like);
            LikeAndUnlike l = new LikeAndUnlike();
            l.deleteLiked(cmtID,u.getId());
            ArrayList<LikeAndUnlike> listLiked = new LikeAndUnlike().getUserLiked(postID);
            ArrayList<LikeAndUnlike> user_liked = new ArrayList<>();
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
            BlogPost blogPost = new BlogPost().getPostById(postID);
            request.setAttribute("BlogPost", blogPost);
            ArrayList<Comment> listComment = new Comment().getCommentByPostId(blogPost.getId());
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
