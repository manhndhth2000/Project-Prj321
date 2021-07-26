/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.BlogPost;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "uploadImagePost", urlPatterns = {"/uploadImagePost"})
public class uploadImagePost extends HttpServlet {

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
            String title = request.getParameter("title");
            String timePost = request.getParameter("timePost");
            DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(fileItemFactory);
            String nameimg = "";
            try {
                List<FileItem> fileItems = upload.parseRequest(request);
                for (FileItem fileItem : fileItems) {
                    if (!fileItem.isFormField()) {
                        // xử lý file 
                        if (!fileItem.getName().equals("")) {
                            nameimg = request.getParameter("title") + "-" + System.nanoTime() + "." + FilenameUtils.getExtension(fileItem.getName());
                            String dirUrl = "F:\\SU21\\PRJ321\\Project_PRJ321\\web\\image\\Detail\\";
                            File dir = new File(dirUrl);
                            if (!dir.exists()) {
                                dir.mkdir();
                            }
                            String fileImg = dirUrl + nameimg;
                            File file = new File(fileImg);
                            try {
                                fileItem.write(file);
                                System.out.println("UPLOAD THÀNH CÔNG...!");
                                System.out.println("ĐƯỜNG DẪN KIỂM TRA UPLOAD HÌNH ẢNH : \n" + fileImg);
                            } catch (Exception e) {
                                System.out.println("CÓ LỖi TRONG QUÁ TRÌNH UPLOAD!");
                                e.printStackTrace();
                            }
                        }
                    }
                }
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
            BlogPost blogPost = new BlogPost();
            blogPost.updatePost(nameimg, title, timePost);
            blogPost = blogPost.getPostByTitle(title, timePost);
            request.setAttribute("blogPost", blogPost);
            request.getRequestDispatcher("View/Redirect1.jsp").forward(request, response);
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
