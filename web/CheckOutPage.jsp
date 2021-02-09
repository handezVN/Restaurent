<%-- 
    Document   : CheckOutPage
    Created on : Feb 5, 2021, 11:12:00 PM
    Author     : handez
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="DTO.DetailTableDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DAO.TableDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            table { 
              width: 100%; 
              border-collapse: collapse; 
            }
            /* Zebra striping */
            tr:nth-of-type(odd) { 
              background: #eee; 
            }
            th { 
              background: #333; 
              color: white; 
              font-weight: bold; 
            }
            td, th { 
              padding: 6px; 
              border: 1px solid #ccc; 
              text-align: left; 
            }
            @media 
        only screen and (max-width: 750px),
        (min-device-width: 100px) and (max-device-width: 1024px)  {
            .table-body{
                margin-top: 40%;
                font-size: xx-large;
            }
            body{
                font-size: xx-large;
            }
        }
        </style>
    </head>
    <body>
        <%
            TableDao dao = new TableDao();
            String table = session.getAttribute("table")+"";
            ArrayList<DetailTableDTO> list = dao.getTable(table);
            request.setAttribute("list", list);
        %>
        <a href="OrderPage.jsp">Back </a><br>
        Table: ${table}
        <table >
            <tr>
                <th>Name</th>
                <th>Quantity</th>
                <th>Price</th>
                <th>Total</th>
                <th></th>
            </tr>
            <c:set var="total" value="0"></c:set>
                <c:forEach var="list" items="${list}">
                    <c:set var="total" value="${total + list.quantity*list.price}"></c:set>
                  <tr> 
                    <td>${list.name}</td>
                    
                    <td><form action="UpdateCheckOutServlet" >
                          <input type="hidden" name="id" value="${list.product_id}">  
                          <input type="number" name="quantity" value="${list.quantity}" id="quantity" style="width: 30px; height: 30px;" min="1">
                          <input type="submit" name="action" value="Update" onclick="return confirm('Quantity ${list.quantity} -> ${param.quantity} ')">
                        </form>
                    </td>
                    <td>${list.price}</td>
                    <td>${list.quantity*list.price}
                    <td><a href="UpdateCheckOutServlet?action=remove&id=${list.product_id}" onclick="return confirm(' Remove ?')">Remove</a></td>
                    
                    
                  </tr>
                </c:forEach>
            <tr>
                <td></td>
                <td>total</td>
                <td>${total}</td>
            </tr>
        </table>
            
            <a href="PaymentServlet?action=payment&tableid=${table}"> Payment</a>
    </body>
</html>
