<%-- 
    Document   : History
    Created on : Feb 7, 2021, 6:33:36 PM
    Author     : handez
--%>

<%@page import="DAO.HistoryDao"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="DAO.OrderDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            .table{
               
               max-width: 60%;
               margin-top:   50px;
            }
            
            table, th, td{
                
                border: 1px solid;
                padding: auto;
            }
            .Total{
                position: absolute;
                right: 35%;
               
            }
            .History{
                
                position: absolute;
                right: 50%;
            }
        </style>
    </head>
    <body>
        <%
            String action = request.getAttribute("action")+"";
            String number = session.getAttribute("number")+"";
            OrderDao dao = new OrderDao();
            
                request.setAttribute("list", dao.getOrder());
            int month =-1;
        %>
        <c:if test="${not empty month}">
            <%  HistoryDao hs = new HistoryDao();
                request.setAttribute("list", hs.getOrderbyMonthandYear(Integer.parseInt(request.getAttribute("month")+"")) );
                month = Integer.parseInt(request.getAttribute("month")+"");
            %>
        </c:if>
            <div class="History">
        <form action="HistoryServlet">
            Thống kê tháng : 
        <select name="month">
            <%  
                
                for(int i=0;i<12;i++){
                    if(i==month){
                %>
                <option value="<%=i%>" selected=""><%=(i+1)%></option>
                <%}else{
                    
                %>
                
                <option value="<%=i%>"><%=(i+1)%></option>
            <%
            }}
            %>
           
        </select>
            <button>Search</button>
        </form>
            </div>
            <table align="center" style="max-width: 70%;" class="table">
            <tr>
                <th>OrderID</th>
                <th>Date</th>
                <th>User</th>
                <th>Money</th>
                <th></th>
            </tr>
            <c:set var="total" value="0"></c:set>
        <c:forEach var="list" items="${list}">
            <c:set var="total" value="${total + list.total}"></c:set>
            <tr>
                
                <td>${list.orderid}</td>
                <td>${list.date}</td>
                <td>${list.number}</td>
                <td>${list.total}</td>
                <td><a href="DetailServlet?action=detail&id=${list.orderid}">Detail</a></td>
            </tr>
        </c:forEach>
            <div class="Total">   Total:${total}.000VNĐ</div>
            <a href="AdminPage.jsp">Back</a>
        </table>
    </body>
</html>
