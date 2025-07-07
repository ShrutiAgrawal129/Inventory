package com.stockmanage;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.*;

public class showCategory extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/userinfo?autoReconnect=true&useSSL=false","root","Root129@");
        
        Statement st=con.createStatement();
        ResultSet rs=st.executeQuery("select * from productDetails");
        out.println("<div style='align-items: center' >");
        out.println("<b><i>Products of "+request.getParameter("opt")+" Category</i></b><br><br>");
        out.println("<table border='1'>");
        out.println("<tr><b><i><th>Product_Id</th>"
                + "<th>Product_Name</th>"
                + "<th>Product_Category</th>"
                + "<th>Price</th>"
                + "<th>Quantity</th></i></b><tr>");
        while(rs.next()){
            if(rs.getString(3).equals(request.getParameter("opt"))){
                out.println("<tr>");
                out.println("<td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getFloat(4)+"</td><td>"+rs.getInt(5)+"</td>");
                out.println("</tr>");
            }
        }
         out.println("</table><br><br>");
         out.println("</div>");
        }
       
        catch(Exception e){}
        out.println("<a href='showByCategory.html'>Change Category<br><br>");
        out.println("<a href='front.html'>Go Back<br><br>");
        
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
