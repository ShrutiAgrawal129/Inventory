package com.stockmanage;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.*;

@WebServlet(name = "insertProduct", urlPatterns = {"/insertProduct"})
public class insertProduct extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/userinfo?autoRecconect=true&&useSSL=false","root","Root129@");
            
            String v_id=request.getParameter("pid");
            String v_name=request.getParameter("pname");
            String v_cat=request.getParameter("cat");
            float v_price=Float.parseFloat(request.getParameter("price"));
            int v_qty=Integer.parseInt(request.getParameter("qty"));

            String sql="insert into productDetails values(?,?,?,?,?)";
            PreparedStatement pt=con.prepareStatement(sql);
            pt.setString(1, v_id);
            pt.setString(2, v_name);
            pt.setString(3, v_cat);
            pt.setFloat(4, v_price);
            pt.setInt(5, v_qty);
            
            pt.executeUpdate();
            
            out.println("Product Added to the table<br><br>");
            out.println("<a href='addProduct.html'>Do you want to add more product<br><br>");
            out.println("<a href='front.html'>Go back");
          //  }
            con.close();
        }
        catch(Exception e){}
    
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



//