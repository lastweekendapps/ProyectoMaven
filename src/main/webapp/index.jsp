<%-- 
    Document   : index
    Created on : 11/03/2017, 03:11:18 PM
    Author     : PC USUARIO
--%>
<%@page import="javax.swing.JOptionPane"%>
<%--@page import="Imagenes.*"--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Base de Datos - ExpoTurismo</title>
        <style>
            body {
                background-image: url(Egipto.jpg);
                background-repeat: no-repeat;
                background-size: cover;
                margin: 0;
                padding: 0;
            }
            .wrapper{
                width: 1100px;
                margin: 0 auto;
            }

            .col3{
                float: left;
                width: 33.33%
            }
            .titulo{
                text-align: center;
            }
            .logo{
                text-align: right;
            }
            .usuario{
                text-align: center;
            }
            .clearfix:after {
                visibility: hidden;
                display: block;
                font-size: 0;
                content: " ";
                clear: both;
                height: 0;
            }
            .clearfix { display: inline-block; }
            /* start commented backslash hack \*/
            * html .clearfix { height: 1%; }
            .clearfix { display: block; }
            /* close commented backslash hack */
        </style>
    </head>
    <body>
        <div>
            <div class="col3">&nbsp;</div>
            <div class="col3 titulo"><h3>INICIAR SESIÓN</h3></div>
            <div class="col3 logo"><image src="logo_pagina.png"/></div>
            <div class="usuario">
                <form action="IndexServlet" method="GET">
                    ID: <input type="text" name="id"/></br></br>
                    USUARIO: <input type="text" name="usuario"/></br></br>
                    CONTRASEÑA: <input type="password" name="contrasena"/></br></br>
                    <input type="submit" value="ENTRAR"/>
                    <%System.out.println("aaa>>>>>>>>>>>>>>>    " + request.getAttribute("Mensaje"));%>

                </form>
                <%if (request.getAttribute("Mensaje") == "ok") {%>
                <meta  http-equiv = "Content-Type" content = "text/html; charset=utf-8"> 
                <meta http-equiv = "refresh" content = "0;URL=opciones.jsp" >                  
                <%} %>
                <%if (request.getAttribute("Mensaje1") == "no es numero") {%>


                <script>alert("Error: el id debe ser un número.");</script>


                <%} else if (request.getAttribute("Mensaje") == "error") {%>

                <script>alert("Los valores ingresador no coinciden o el usuario no existe.\n\
Asegúrese de llenar todos lo campos correctamente.");</script>

                
                <%}%>


            </div>
            <div class="clearfix"></div>
        </div>


    </body>
</html>
