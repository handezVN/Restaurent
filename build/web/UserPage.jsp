<%-- 
    Document   : UserPage
    Created on : Feb 4, 2021, 6:20:06 PM
    Author     : handez
--%>

<%@page import="DTO.TableDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DAO.TableDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            .square {
            padding: 100px;
            background-color: #ccc;
            text-align: center;
            max-width: 200px;
            margin: 50px;
          }
          .square-block {
            padding: 100px;
            background-color: coral;
            text-align: center;
            max-width: 200px;
            margin: 50px;
          }
          .display{
              display: flex;
              flex-wrap: wrap;  
              justify-content: space-around;
          }
          @media 
        only screen and (max-width: 750px),
        (min-device-width: 100px) and (max-device-width: 1024px)  {
            
            body{
                font-size: xx-large;
            }
        </style>
    </head>
    <body>
        
        <%  String number = session.getAttribute("number")+"";
            System.out.println(number);
            if(number.isEmpty()){
                response.sendRedirect("Login.jsp");
            }
        %>
       
        <div class="display">
        <%
            TableDao dao = new TableDao();
            ArrayList<TableDTO> list = dao.getTable();
            for(int i=0;i<list.size();i++){
                if(!list.get(i).isStatus()){
                %>
                <a href="TableServlet?action=checkin&id=<%=list.get(i).getTable_id()%>"><div class="square">Bàn 0<%=i+1%> </div></a>
                <%
                }else{
                %>
                <a href="TableServlet?action=checkout&id=<%=list.get(i).getTable_id()%>"><div class="square-block">Bàn 0<%=i+1%> </div></a>
                <%
                }
            }
        %>
        </div>
    </body>
</html>
