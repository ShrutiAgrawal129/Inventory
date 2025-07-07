
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
import java.sql.*;

@WebServlet(name = "removeRecord", urlPatterns = {"/removeRecord"})
public class removeRecord extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/userinfo","root","Root129@");

        //  String name=request.getParameter("pname");
//            String sql="DELETE FROM productDetails WHERE p_name='"+name+"'";
//            PreparedStatement pt=con.prepareStatement(sql);
//            pt.executeUpdate();
//            out.print("record deleted ....");
        Statement st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
        ResultSet rs=st.executeQuery("select * from productDetails");
        rs.beforeFirst();
        while(rs.next()){
            if(rs.getString("p_name").equals(request.getParameter("pname"))){
                rs.deleteRow();
                rs.updateRow();    
            }  
            else{
                RequestDispatcher rd=request.getRequestDispatcher("deleteProduct.html");
                rd.forward(request,response);
            }
            

        } 

//        String name=request.getParameter("pname");
//        String sql="DELETE FROM productDetails WHERE p_name=?";
//        PreparedStatement pt=con.prepareStatement(sql);
//        pt.setString(1, name);
//        int i = pt.executeUpdate();
//                        if (i > 0) {
//                            out.println("Record deleted!!!<br><br>");
//                        } else {
//                            out.println("No record found with the given product name.<br><br>");
//                        }
        con.close();
        out.println("Record Deleted Successfully!!!<br><br>");
        }
        catch(Exception e){
        out.println("Somethins else try again letter!!!<br><br>"+e.getMessage()+"<br><br>");
        }
        out.println("<a href='deleteProduct.html'>Delete More Record...<br><br>");
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
