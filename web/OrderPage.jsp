<%-- 
    Document   : OrderPage
    Created on : Feb 4, 2021, 11:45:12 PM
    Author     : handez
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="DAO.ProductDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            .table{
               
               max-width: 60%;
               margin-top:   200px;
            }
            
            table, th, td{
                
                border: 1px solid;
                padding: auto;
            }
            .cart{
                position: fixed;
                top: 7%;
                right: 10%;
            }
            .cart span{
                position: fixed;
                top :5%;
                right: 8%;
                background: crimson;
                font-size: xx-large;
                color: white;
                padding: 3px 5px;
                border-radius: 50%;
                z-index: -1;
                
            }
            .alert{
                position: fixed;
                width: 100%;
                border-color:  #c3e6cb;
                background: #d4edda;
                position: fixed;
                color: #155724;
                top: 0%;
                display: block;
                z-index: 100000;
            }
            .checkout{
                position: fixed;
                left: 5%;
                font-size: xx-large;
                background: activecaption;
                top: 7%;
                
            }
            .home{
                position: fixed;
                top: 7%;
                right: 50%;
                font-size: xx-large;
            }
            @media 
        only screen and (max-width: 750px),
        (min-device-width: 100px) and (max-device-width: 1024px)  {
            
            .abc{
                font-size: xx-large;
            }
        }
        </style>
        
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
            <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
            <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
            <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
            <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>


        <c:if test="${notify!=null}">
        <div class="alert alert-success alert-dismissible">
          <button type="button" class="close" data-dismiss="alert">×</button>
           ${notify}
        </div>
        </c:if>
    </head>
    <body>
        <div class="abc">
        <%
            ProductDao dao = new ProductDao();
            request.setAttribute("list", dao.getProduct());
        %>
        <a class="home" href="HomeServlet">Home</a>
        <c:if test="${not empty checkout}">
            <div class="checkout">
                <a href="CheckOutPage.jsp" style="color: crimson">${table}:Check Out</a>
            </div>
        </c:if>
        <div class="cart">
            <span>${cart.size()}</span>
            <a href="Cart.jsp" ><img src="shopping-cart-solid.svg" width="100px" ></a>
        </div>
        <table class="table" align="center" style="max-width: 70%;">
            <tr>
                <th>Name</th>
                <th>Price</th>
                <th>Select</th>
            </tr>
            
            <c:forEach var="item" items="${list}">
               <tr> <td style="width:500px; padding: 50px;">${item.name}</td>
                <td style=" padding: 50px;">${item.price}</td>
                <td style=" padding: 50px;"><a href="CartServlet?action=add&id=${item.product_id}">Add To Cart</a></td>
               </tr>
            </c:forEach>
            
        </table>
            </div>
    </body>
</html>
