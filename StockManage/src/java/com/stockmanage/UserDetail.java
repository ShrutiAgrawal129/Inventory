
package com.stockmanage;

import jakarta.servlet.RequestDispatcher;
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

@WebServlet(name = "UserDetail", urlPatterns = {"/UserDetail"})
public class UserDetail extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/userinfo?autoReconnect=true&useSSL=false","root","Root129@");

        Statement st=con.createStatement();
        
        ResultSet rs=st.executeQuery("select * from userlogin");
       
        String vname=request.getParameter("name");
        String vpass=request.getParameter("pass");
        
        boolean check=false;
        while(rs.next()){
            
            if( vname.equals(rs.getString("username")) && vpass.equals(rs.getString("password"))) {
              check=true;
              break;
            } 
        }
        RequestDispatcher rd;
        if(check)  {
          rd=request.getRequestDispatcher("front.html");
          rd.forward(request, response);
        }
        else{
          rd=request.getRequestDispatcher("index.html");
          rd.forward(request, response);
        } 
        //out.println("</table>");
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
