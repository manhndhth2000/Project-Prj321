/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Admin;
import Model.User;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "UploadImageAdmin", urlPatterns = {"/UploadImageAdmin"})
public class UploadImageAdmin extends HttpServlet {

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
            DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(fileItemFactory);
            String nameimg = "";
            try {
                List<FileItem> fileItems = upload.parseRequest(request);
                for (FileItem fileItem : fileItems) {
                    if (!fileItem.isFormField()) {
                        // x??? l?? file 
                        if (!fileItem.getName().equals("")) {
                            nameimg = request.getParameter("username") + "-" + System.nanoTime() + "." + FilenameUtils.getExtension(fileItem.getName());
                            String dirUrl = "F:\\SU21\\PRJ321\\Project_PRJ321\\web\\image\\Admin\\";
                            File dir = new File(dirUrl);
                            if (!dir.exists()) {
                                dir.mkdir();
                            }
                            String fileImg = dirUrl + nameimg;
                            File file = new File(fileImg);
                            try {
                                fileItem.write(file);
                                System.out.println("UPLOAD TH??NH C??NG...!");
                                System.out.println("???????NG D???N KI???M TRA UPLOAD H??NH ???NH : \n" + fileImg);
                            } catch (Exception e) {
                                System.out.println("C?? L???i TRONG QU?? TR??NH UPLOAD!");
                                e.printStackTrace();
                            }
                        }
                    }
                }
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
            String username = request.getParameter("username");
            Admin admin = new Admin();
            admin.updateImage(nameimg, username);
            session.removeAttribute("user");
            admin = admin.getAllInforByUsername(username);
            session.setAttribute("user", admin);
            request.getRequestDispatcher("View/Redirect3.jsp").forward(request, response);
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
