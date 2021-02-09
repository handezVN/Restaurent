/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import DAO.OrderDao;
import DAO.TableDao;
import DTO.DetailTableDTO;
import DTO.ShoppingCart;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
@WebServlet(name = "OrderServlet", urlPatterns = {"/OrderServlet"})
public class OrderServlet extends HttpServlet {

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
            String table = (String) session.getAttribute("table");
            String number = (String) session.getAttribute("number");
            ArrayList<ShoppingCart> list = (ArrayList<ShoppingCart>) session.getAttribute("cart");
            TableDao dao = new TableDao(); 
            OrderDao ordao = new OrderDao();
            ordao.order(list,table);
            ArrayList<DetailTableDTO> listtable = dao.getTable(table);
            dao.checkin(table);
            for(int i=0;i<list.size();i++){
                String productid = list.get(i).getProduct().getProduct_id();
                int quantity = list.get(i).getQuantity();
                int price = list.get(i).getProduct().getPrice();
                String name = list.get(i).getProduct().getName();
                int index = isExisting(productid, listtable);
                if(index!=-1){
                    dao.updateQuantity(productid, table, quantity+listtable.get(index).getQuantity());
                }else{
                    dao.insert(number, productid, table, quantity,price,name);
                }
                
            }
           
            
            session.removeAttribute("cart");
            session.removeAttribute("table");
            response.sendRedirect("UserPage.jsp");
        } catch (SQLException ex) {
            Logger.getLogger(OrderServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private int isExisting(String id, List<DetailTableDTO> cart) {
        for (int i = 0; i < cart.size(); i++) {
           
            String productid = cart.get(i).getProduct_id().trim();
            if (productid.equalsIgnoreCase(id.trim())) {
                return i;
            }
        }
        return -1;
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
