/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import DAO.OrderDao;
import DAO.TableDao;
import DTO.DetailTableDTO;
import DTO.OrderDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author handez
 */
@WebServlet(name = "PaymentServlet", urlPatterns = {"/PaymentServlet"})
public class PaymentServlet extends HttpServlet {

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
            String tableid = request.getParameter("tableid");
            TableDao dao = new TableDao();
            HttpSession session = request.getSession();
            ArrayList<DetailTableDTO> list = dao.getTable(tableid);
            String number = (String) session.getAttribute("number");
            int total = 0;
            for(int i=0;i<list.size();i++){
                total += (list.get(i).getPrice()*list.get(i).getQuantity());
            }
            OrderDao ordao = new OrderDao();
            ArrayList<OrderDTO> listOr = ordao.getOrder();
            String orderid = "OD"+(listOr.size()+1);
            ordao.insertOrder(orderid, number, total,tableid);
            for(int i=0;i<list.size();i++){
                String name = list.get(i).getName();
                int quantity = list.get(i).getQuantity();
                int price = list.get(i).getPrice();
                String productid = list.get(i).getProduct_id();
                ordao.insertDetailOrder(orderid, name, quantity, price, productid);
            }
            dao.checkoutTalbe(tableid);
            dao.checkout(tableid);
            ordao.Bill(orderid);
            request.setAttribute("orderid", orderid);
            request.getRequestDispatcher("ConfirmPage.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(PaymentServlet.class.getName()).log(Level.SEVERE, null, ex);
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
