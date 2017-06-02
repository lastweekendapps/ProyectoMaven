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
        <title>Graficos</title>
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
            
            .enjoy-input {
                display: inline-block;
                -webkit-box-sizing: content-box;
                -moz-box-sizing: content-box;
                box-sizing: content-box;
                padding: 5px 20px;
                border: 1px solid #b7b7b7;
                -webkit-border-radius: 3px;
                border-radius: 3px;
                font: normal medium/normal Arial, Helvetica, sans-serif;
                color: rgba(0,142,198,1);
                -o-text-overflow: clip;
                text-overflow: clip;
                background: rgba(252,252,252,1);
                -webkit-box-shadow: 2px 2px 2px 0 rgba(0,0,0,0.2) inset;
                box-shadow: 2px 2px 2px 0 rgba(0,0,0,0.2) inset;
                text-shadow: 1px 1px 0 rgba(255,255,255,0.66) ;
                -webkit-transition: all 200ms cubic-bezier(0.42, 0, 0.58, 1);
                -moz-transition: all 200ms cubic-bezier(0.42, 0, 0.58, 1);
                -o-transition: all 200ms cubic-bezier(0.42, 0, 0.58, 1);
                transition: all 200ms cubic-bezier(0.42, 0, 0.58, 1);
            }
            .enjoy-input:hover {
                border: 1px solid #a3a3a3;
                background: rgba(255,255,255,1);
                -webkit-transition: all 100ms cubic-bezier(0.42, 0, 0.58, 1);
                -moz-transition: all 100ms cubic-bezier(0.42, 0, 0.58, 1);
                -o-transition: all 100ms cubic-bezier(0.42, 0, 0.58, 1);
                transition: all 100ms cubic-bezier(0.42, 0, 0.58, 1);
            }
            .enjoy-input:focus {
                border: 1px solid #018dc4;
                -webkit-box-shadow: 4px 4px 4px 0 rgba(0,0,0,0.2) inset;
                box-shadow: 4px 4px 4px 0 rgba(0,0,0,0.2) inset;
                -webkit-transition: all 50ms cubic-bezier(0.42, 0, 0.58, 1);
                -moz-transition: all 50ms cubic-bezier(0.42, 0, 0.58, 1);
                -o-transition: all 50ms cubic-bezier(0.42, 0, 0.58, 1);
                transition: all 50ms cubic-bezier(0.42, 0, 0.58, 1);
            }
        </style>
    </head>
    <body>
        <%HttpSession mySession = request.getSession();
            String nname = (String) mySession.getAttribute("nombre");
        %>
        
        <%if(nname==null){%>
      
        <META HTTP-EQUIV="REFRESH" CONTENT="0;URL=index.jsp">
        
        <%}%>
        <div>
            <div class="col3 titulo"><h3>GRÁFICOS Y DESCARGA EN EXCEL</h3></div>
            <div class="col3 logo"><image src="logo_pagina.png"/></div>
             
        </div>
        <img src="ChartServlet" /><br/>
        Descargar Excel <a href="HSSFCreate">Descargar</a>

    </body>
</html>
