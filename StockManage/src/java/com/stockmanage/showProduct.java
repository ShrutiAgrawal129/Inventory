
package com.stockmanage;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet(name = "showProduct", urlPatterns = {"/showProduct"})
public class showProduct extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/userinfo?autoReconnect=true&useSSL=false","root","Root129@");

        Statement st=con.createStatement();
        
        ResultSet rs=st.executeQuery("select * from productDetails");
        out.println("<h2>Product Table</h2>");
        out.println("<table  border= '1' width='60%' height='50%'>");
        
        out.println("<tr><th><b><i>Product Id</i></b></th>"
                + "<th><b><i>Product Name</i></b></th>"
                + "<th><b><i>Product Category</i></b></th>"
                + " <th><b><i>Unit Price</i></b></th>"
                + "<th><b><i>Quantity</i></b></th></tr>");
        while(rs.next()){
            
            out.println("<tr>");
            out.println("<td>"+rs.getString(1)+"</td>");
   
            out.println("<td>"+rs.getString(2)+"</td>");
            
            out.println("<td>"+rs.getString(3)+"</td>");
            
            out.println("<td>"+rs.getFloat(4)+"</td>");
            
            out.println("<td>"+rs.getInt(5)+"</td>");
            out.println("</tr>");
            
            

        }
        out.println("</table>");
        
        out.println("<a href='front.html'>GoBack");
        
        con.close();
        }
        catch(Exception e){
        out.println("error");}
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
