<%-- 
    Document   : opciones
    Created on : 11/03/2017, 06:03:54 PM
    Author     : PC USUARIO
--%>

<%@page import="vo.UsuarioVO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administrador de Base de Datos</title>
        <style>
            body {
                background-image: url(Hawaii.jpg);
                background-repeat: no-repeat;
                background-size: cover;
                margin: 0;
                padding: 0;
            }

            .logo{
                text-align: center;
            }

            input{
                margin-right: 20px;
            }

            .h4{
                text-align: center;
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



            #header {
                margin-left:600px;
                width:500px;
                font-family:Arial;
            }

            ul {
                list-style:none;
            }

           .nav > li {
                float:right;
            }

            .nav li a {
                background: #3498db;
                color:#fff;
                text-decoration:none;
                padding:10px 12px;
                display:block;
                background-image: -webkit-linear-gradient(top, #3498db, #2980b9);
                background-image: -moz-linear-gradient(top, #3498db, #2980b9);
                background-image: -ms-linear-gradient(top, #3498db, #2980b9);
                background-image: -o-linear-gradient(top, #3498db, #2980b9);
                background-image: linear-gradient(to bottom, #3498db, #2980b9);
                -webkit-border-radius: 28;
                -moz-border-radius: 28;
                border-radius: 28px;
            }

            .nav li a:hover {
               background: #3cb0fd;
                background-image: -webkit-linear-gradient(top, #3cb0fd, #3498db);
                background-image: -moz-linear-gradient(top, #3cb0fd, #3498db);
                background-image: -ms-linear-gradient(top, #3cb0fd, #3498db);
                background-image: -o-linear-gradient(top, #3cb0fd, #3498db);
                background-image: linear-gradient(to bottom, #3cb0fd, #3498db);
                text-decoration: none;
            }

            .nav li ul {
                display:none;
                position:absolute;
                min-width:100px;
            }

            .nav li:hover > ul {
                display:block;
            }

            .nav li ul li {
                position:relative;
            }

            .contenedor{
                margin-left: 230px;
            }
        </style>
    </head>
    <body>
        <%HttpSession mySession = request.getSession();
            String name = (String) mySession.getAttribute("nombre");
        %>
        <div id="header">
            <ul class="nav">


                <li><a href=""><%=name%> </a>
                    <ul>
                        <li><a href="editarUsuario.jsp">Cambiar Contraseña</a></li>
                        <li><a href="cerrarSesion.jsp">Cerrar Sesión</a></li>
                    </ul>
                </li>

            </ul>
        </div>
        <br><br><br><br>
        <div class="logo"><image src="logo_pagina.png"/></div><br>

        

        <div class="h4"><h3>Escoge una de las siguientes opciones</h3></div><br><br>
        <div class="contenedor">
            <table>
                <tr>
                    <td>
                        <form target="_blank" action="nuevoCliente.jsp">
                            <input class="btn" type="submit" value="Nuevo Cliente" style="margin-left: 200px">
                        </form>
                    </td>

                    <td> 
                        <form target="_blank" action="buscarCliente.jsp">
                            <input class="btn" type="submit" value="Buscar Cliente">
                        </form>
                    </td>

                    <td>
                        <form target="_blank" action="editarCliente.jsp">
                            <input class="btn" type="submit" value="Editar Cliente">
                        </form>
                    </td>

                    <td>
                        <form target="_blank" action="nuevoUsuario.jsp">
                            <input class="btn" type="submit" value="Nuevo Usuario">
                        </form>
                    </td>

                    <td>
                        <form target="_blank" action="nuevoRecordatorio.jsp">
                            <input class="btn" type="submit" value="Nuevo Recordatorio">
                        </form>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form action="nuevoViaje.jsp">
                            <input class="btn" type="submit" value="Nuevo Viaje">
                        </form>
                    </td>
                    <td>
                        <form action="borrarUsuario.jsp">
                            <input class="btn" type="submit" value="Borrar Usuario">
                        </form>
                    </td>
                    <td>
                        <form action="buscarRecordatorios.jsp">
                            <input class="btn" type="submit" value="Buscar Recordatorios">
                        </form>
                    </td>
                    <td>
                        <form action="coincidencias.jsp">
                            <input class="btn" type="submit" value="Viajeros Solos">
                        </form>
                    </td>
                </tr>
            </table>
        </div>
    </body>
</html>
