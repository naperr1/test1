/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import DAO.CustomersDao;
import Model.Customers;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Fattt
 */
@WebServlet(name="UpdateCustomer", urlPatterns={"/updateCustomer"})
public class UpdateCustomer extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdateCustomer</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateCustomer at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String id_raw = request.getParameter("id");
        int id; 
        CustomersDao ctd = new CustomersDao();
        
        try {
            id=Integer.parseInt(id_raw);
            Customers  c = ctd.getCustomerById(id);
            request.setAttribute("customer", c);
            request.getRequestDispatcher("updateCustomer.jsp").forward(request, response);
        } catch (Exception e) {
        }
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id_raw = request.getParameter("id");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String fullname = request.getParameter("fullname");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String admin_raw = request.getParameter("admin");
        
        int id;
        int admin;
        CustomersDao ctd = new CustomersDao();
        
        try {
            
            id = Integer.parseInt(id_raw);
            admin = Integer.parseInt(admin_raw);
            Customers c = ctd.getCustomerById(id);
            System.out.println(id);
            System.out.println(username);
            System.out.println(password);
            System.out.println(fullname);
            System.out.println(address);
            System.out.println(email);
            System.out.println(phone);
            System.out.println(admin);
            
            Customers cNew = new Customers(id, username, password, fullname, address, email, phone, admin);
            System.out.println(cNew.toString());
            ctd.updateCustomer(cNew);
            response.sendRedirect("customerManager");
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
