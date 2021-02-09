<%-- 
    Document   : Cart
    Created on : Feb 5, 2021, 6:11:51 PM
    Author     : handez
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
/*            .table{
               
               width: 100%;
               text-align: center;
               
            }
            .table-body{
                position: absolute;
                right: 40%;
                     
            }
            
            table, th, td{
                
                border: 1px solid;
                padding: auto;
            }*/
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
        <c:if test="${not empty checkout}">
            <a href="CheckOutPage.jsp">Check Out</a>
        </c:if>
            <a href="OrderPage.jsp">Back </a>
        Table${sessionScope.table}
        <div class="table-body">
        <table class="table" >
            <tr>
                <th>Name</th>
                <th>Quantity</th>
                <th>Price</th>
                <th>Total</th>
                <th></th>
            </tr>
            <c:set var="total" value="0"></c:set>
                <c:forEach var="list" items="${cart}">
                    <c:set var="total" value="${total + list.quantity*list.product.price}"></c:set>
                  <tr> 
                    <td>${list.product.name}</td>
                    
                    <td><form action="UpdateCartServlet" >
                          <input type="hidden" name="id" value="${list.product.product_id}">  
                        <input type="number" name="quantity" value="${list.quantity}" style="width: 30px">
                       <input type="submit" name="action" value="Update">
                        </form>
                       
                    </td>
                    <td>${list.product.price}</td>
                    <td>${list.quantity*list.product.price}
                       
                    <td><a href="UpdateCartServlet?action=remove&id=${list.product.product_id}">Remove</a></td>
                    
                  </tr>
                </c:forEach>
            <tr>
                <td></td>
               
            </tr>
        </table> 
                Total:
                ${total}.000VNĐ
            </div>
            <a href="OrderServlet">Order</a>
    </body>
</html>
