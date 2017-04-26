<%-- 
    Document   : listaRecordatorios
    Created on : 23/04/2017, 03:14:45 PM
    Author     : ayoro
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <style>
            body{
                background-image: url(https://images.alphacoders.com/529/529751.jpg);
            }
            .logo{
                text-align: right;
            }
            table{
                text-align: center;
            }
        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="logo"><image src="logo_pagina.png"/></div>
        <h1>Lista Recordatorios</h1>
        <%ArrayList registros = (ArrayList) request.getAttribute("recordatorios");%>
        <table>
            <tr>
                <td>ID</td>
                <td>Fecha</td>
                <td>Hora</td>
                <td>Descripcion</td>
            </tr>
            <%for (int i = 0; i < registros.size(); i+=4) {%>
            <tr>
                <td><%=registros.get(i)%></td>
                <td><%=registros.get(i+1)%></td>
                <td><%=registros.get(i+2)%></td>
                <td><%=registros.get(i+3)%></td>
            </tr>
            <%}%>
        </table>
    </body>
</html>
