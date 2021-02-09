<%-- 
    Document   : AdminPage
    Created on : Feb 4, 2021, 6:20:42 PM
    Author     : handez
--%>

<%@page import="DTO.ProductDTO"%>
<%@page import="DAO.ProductDao"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="DAO.TableDao"%>
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
            .updateproduct{
                position: absolute;
                right: 50%;
            }
            .body{
                text-align: center;
            }
            @media 
        only screen and (max-width: 750px),
        (min-device-width: 100px) and (max-device-width: 1024px)  {
            
            .abc{
                font-size: xx-large;
            }
        </style>
    </head>
    <body>
        
        <%  String check =  session.getAttribute("isadmin")+"";
            if(!check.equals("true")){
                response.sendRedirect("Login.jsp");
            }
            TableDao dao = new TableDao();
            request.setAttribute("Table", dao.getTable().size());
        %>
        
        <div class="body">
            Số lượng Bàn : ${Table}<br>
        <a href="TableServlet?action=update"> Update Số Lượng Bàn </a>
        
    <c:if test="${not empty updateTable}">
        
        <form action="UpdateTableServlet">
            <input type="number" value="${Table}" name="size" min="1">
            <input type="submit" value="Update">
        </form>
        
    </c:if>
           <br>
        <br><a href="UserPage.jsp">Đi tới trang Order</a><br>
        <br><a href="ProductServlet?action=add">Thêm Sản Phẩm Mới</a><br>
        <br><a href="History.jsp">Doanh Số Bán Hàng</a>
        <c:if test="${not empty addProduct}">
            <form action="AddProductServlet" method="POST">
                
                Tên:<input type="text" name="name" value="${param.name}"required=""><br>
                Giá:<input type="number" name="price" value="${param.price}" required="" min="1"><br>
            <input type="Submit" value="Add Product">
            </form>
        </c:if>
        
        <c:if test="${not empty updateproduct}">
            <form action="UpdateProductServlet" method="POST">
                <input type="hidden" name="id" value="${updateproduct.product_id}">
            Tên:<input type="text" name="name" value="${updateproduct.name}"><br>
            Giá:<input type="number" name="price" value="${updateproduct.price}"><br>
            <input type="Submit" value="Update Product">
            </form>
        </c:if>    
        </div>
        <%
            ProductDao prddao = new ProductDao();
            request.setAttribute("list", prddao.getProduct());
        %>
        
         
        <table class="table" align="center" style="max-width: 70%;">
            <tr>
                <th>No</th>
                <th>Name</th>
                <th>Price</th>
                <th>Select</th>
                <th></th>
            </tr>
            <c:set var="count" value="1"></c:set>
            <c:forEach var="item" items="${list}">
               <tr> 
                   <td>${count}</td>
                <td style="width:500px; padding: 50px;">${item.name}</td>
                <td style=" padding: 50px;">${item.price}</td>
                <td style=" padding: 50px;"><a href="ProductServlet?action=Update&id=${item.product_id}">Update</a></td>
                <td style=" padding: 50px;"><a href="ProductServlet?action=Remove&id=${item.product_id}" onclick="return confirm('${item.name} Bạn muốn số nó ?') ">Remove</a></td>
                
               </tr>
               <c:set var="count" value="${count+1}"></c:set>
            </c:forEach>
            
        </table>
    </body>
</html>
