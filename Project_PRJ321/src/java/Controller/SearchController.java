/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.BlogPost;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "SearchController", urlPatterns = {"/SearchController"})
public class SearchController extends HttpServlet {

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
            String type ="";
            String input = request.getParameter("input");
            request.setAttribute("input", input);
            
            int index = Integer.parseInt(request.getParameter("index"));
            request.setAttribute("index", index);
            
            int count = 0;
            ArrayList<BlogPost> list = new ArrayList<>();
            if(new BlogPost().getListNUmberByTitle(input) != 0){
                list = new BlogPost().getListByTitle(input,index);
                count = new BlogPost().getListNUmberByTitle(input);
            } else if(new BlogPost().getListNUmberByCategory(input) != 0){
                list = new BlogPost().getListByCategoryName(input, index);
                count = new BlogPost().getListNUmberByCategory(input);
            } else if(new BlogPost().getListNUmberByAuthor(input) != 0){
                list = new BlogPost().getListByAuthor(input, index);
                count = new BlogPost().getListNUmberByAuthor(input);
            } else{
                list = null;
            }
            request.setAttribute("list", list);
            
            int size = 3;
            int endPage = (count % size == 0) ? count / 3 : count / 3 + 1;
            request.setAttribute("endPage", endPage);
            
            request.setAttribute("total", count);
            request.getRequestDispatcher("View/SearchResult.jsp").forward(request, response);
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
