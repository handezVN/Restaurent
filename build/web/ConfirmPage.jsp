<%-- 
    Document   : ConfirmPage
    Created on : Feb 7, 2021, 7:52:34 AM
    Author     : handez
--%>

<%@page import="DTO.DetailOrderDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="DAO.OrderDao"%>
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
           String orderid = request.getAttribute("orderid")+"";
            
            OrderDao  dao = new OrderDao();
            System.out.println(orderid);    
            ArrayList<DetailOrderDTO> list = dao.getdetailOrder(orderid);
            request.setAttribute("list",list);
            
        %>
        <a class="home" href="HomeServlet">Home</a>
        <h1>${orderid}</h1>
        <table >
            <tr>
                <th>Name</th>
                <th>Quantity</th>
                <th>Price</th>
                <th>Total</th>
                <th></th>
            </tr>
            <c:set var="total" value="0"></c:set>
            <c:forEach items="${list}" var="list">
                    <c:set var="total" value="${total + list.quantity*list.price}"></c:set>
                  <tr> 
                    <td>${list.name}</td>
                    
                    <td>${list.quantity}
                    </td>
                    <td>${list.price}</td>
                    <td>${list.quantity*list.price}
                       
                    
                    
                  </tr>
            </c:forEach>
            <tr>
                <td></td>
                <td>total</td>
                <td>${total}</td>
            </tr>
        </table>
    </body>
</html>
