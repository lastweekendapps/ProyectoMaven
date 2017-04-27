<%-- 
    Document   : cerrarSesion
    Created on : 23-abr-2017, 15:03:10
    Author     : Nicolas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
            body {
                background-image: url(Amazonas.jpg);
                background-repeat: no-repeat;
                background-size: cover;
                margin: 0;
                padding: 0;
            }

            .logo{
                text-align: left;
            }
            
            .title{
                text-align: center;
                color: #ffffff;
                margin-top: 200px;
            }
            
            .form{
                text-align: center;
                margin-left: 725px;
            }
            
            .btn {
                background: #3498db;
                background-image: -webkit-linear-gradient(top, #3498db, #2980b9);
                background-image: -moz-linear-gradient(top, #3498db, #2980b9);
                background-image: -ms-linear-gradient(top, #3498db, #2980b9);
                background-image: -o-linear-gradient(top, #3498db, #2980b9);
                background-image: linear-gradient(to bottom, #3498db, #2980b9);
                -webkit-border-radius: 28;
                -moz-border-radius: 28;
                border-radius: 28px;
                text-shadow: 1px 1px 3px #666666;
                font-family: Arial;
                color: #ffffff;
                font-size: 12px;
                padding: 10px 20px 10px 20px;
                text-decoration: none;
            }

            .btn:hover {
                background: #3cb0fd;
                background-image: -webkit-linear-gradient(top, #3cb0fd, #3498db);
                background-image: -moz-linear-gradient(top, #3cb0fd, #3498db);
                background-image: -ms-linear-gradient(top, #3cb0fd, #3498db);
                background-image: -o-linear-gradient(top, #3cb0fd, #3498db);
                background-image: linear-gradient(to bottom, #3cb0fd, #3498db);
                text-decoration: none;
            }
        </style>
        <title>Cerrar Sesión</title>
    </head>
    <body>
        <div class="logo"><a href="opciones.jsp"><image src="logo_pagina.png"/></a></div>
        <div class="title">
            <h1>Está seguro que desea cerrar sesión?</h1>
        </div><br>
        <div class="form">
            <table>
                <tr>
                    <td>
                            <form action="CerrarSesionServlet">
                                <input class="btn" type="submit" value="SI" >
                            </form>
                    </td>
                    <td>
                        <a href="opciones.jsp"><button class="btn">NO</button></a>
                    </td>
                </tr>
            </table>
        </div>
    </body>
</html>
