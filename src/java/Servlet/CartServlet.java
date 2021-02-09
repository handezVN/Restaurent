/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import DAO.ProductDao;
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
@WebServlet(name = "CartServlet", urlPatterns = {"/CartServlet"})
public class CartServlet extends HttpServlet {

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
            String action= request.getParameter("action");
            String id = request.getParameter("id");
            HttpSession session = request.getSession();
            ProductDao dao = new ProductDao();
            if(session.getAttribute("cart")==null){
                List<ShoppingCart> cart = new  ArrayList<>();
                cart.add(new ShoppingCart(dao.getProductbyId(id), 1));
                session.setAttribute("cart", cart);
            }else{
                System.out.println("alo");
                List<ShoppingCart> cart = (List<ShoppingCart>) session.getAttribute("cart");
                int index = isExisting(id, cart);
                System.out.println(id);
                System.out.println(index);
                if(index==-1){
                    System.out.println("Ale");
                    cart.add(new ShoppingCart(dao.getProductbyId(id), 1));
                }else{
                    System.out.println("113");
                    int quantity = cart.get(index).getQuantity()+1;
                    cart.get(index).setQuantity(quantity);
                }
                 session.setAttribute("cart", cart);
            }
            request.setAttribute("notify", "Added Product!");
            request.getRequestDispatcher("OrderPage.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CartServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private int isExisting(String id, List<ShoppingCart> cart) {
        for (int i = 0; i < cart.size(); i++) {
           
            String productid = cart.get(i).getProduct().getProduct_id().trim();
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
