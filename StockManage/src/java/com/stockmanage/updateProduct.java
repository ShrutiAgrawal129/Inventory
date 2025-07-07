package com.stockmanage;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.*;

@WebServlet(name = "updateProduct", urlPatterns = {"/updateProduct"})

public class updateProduct extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String v_name=request.getParameter("name");
        int vqty=Integer.parseInt(request.getParameter("qty"));
        
        try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/userinfo?autoReconnect=true&useSSL=false","root","Root129@");

        if(request.getParameter("radio").equals("add")){
        String sql="update productDetails set quantity=quantity+? where p_name=?";
        
        PreparedStatement pt=con.prepareStatement(sql);
        pt.setInt(1, vqty);
        pt.setString(2, v_name);
        pt.executeUpdate();
        }
        
        else{
        String sql="update productDetails set quantity=quantity-? where p_name=?";
        
        PreparedStatement pt=con.prepareStatement(sql);
        pt.setInt(1, vqty);
        pt.setString(2, v_name);
        pt.executeUpdate();
        }
        }
        catch(Exception e){}
        
        out.println("Record Updated<br><br>");
        out.println("<a href='updateData.html'>Update more records<br><br>");
        out.println("<a href='front.html'>Go Back");
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
