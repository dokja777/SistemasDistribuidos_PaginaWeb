

<%@page import="Entidades.Producto"%>
<%-- 
    Document   : ModificarCliente
    Created on : 13 may. 2024, 16:46:40
    Author     : Alexandra
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%@page import="Entidades.Cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Producto> Lista= (List<Producto>) request.getAttribute("Lista");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar Producto</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <script src="https://kit.fontawesome.com/26a3cc7edf.js" crossorigin="anonymous"></script>
        
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Bebas+Neue&family=Jaro:opsz@6..72&display=swap" rel="stylesheet">
    </head>
    <style>
        * {
            margin: 0;
        }

        .sesion:hover {
            background-color: red;
        }

        .cerrarSesion {
            width: 100%;    
            display: flex;
            justify-content: space-between;
            position: absolute;
            margin: 3vh 0 0 0;
        }
        .sesion {
            background-color: white;
            border-radius: 20%;
            padding: 1vh;
            text-decoration: none;
            color: black;
            
            margin: 0 4vh;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
        }
        
        .regresar {
            background-color: white;
            border-radius: 20%;
            padding: 1vh;
            text-decoration: none;
            color: black;
            
            margin: 0 4vh;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
        }
        .regresar:hover{
            background-color: greenyellow;
        }

        
        .titulo {
            display: flex;
            justify-content: center;
            align-items: center;
            font-size: 8vh;
            font-family: "Bebas Neue", sans-serif;
            font-weight: 400;
            font-style: normal;
        }
        
        .form-main{
            min-height: 100vh;
            padding:  40px 15px;
            display: flex;
            align-items: center;
            justify-content: center;
        }
        
        .form-content{  
            backdrop-filter: blur(20px);
            width: 100%;
            max-width: 500px;
            position: relative;
            z-index: 1;
            box-shadow: 0 0 30px #000;
            margin: auto;
        }
        
        .form-content .boxy{
            border-color: #004a8c;
            padding: 40px 50px;
            box-shadow: 0 4px 9.4px 0.6px rgba(3,39,61,.1);
            -webkit-box-shadow: 0 4px 9.4px 0.6px rgba(3,39,61,.1);
            

        .form-nu .input-box{
            margin-bottom: 20px;
        }
        
        .form-nu .input-box label{
            display: block;
            font-weight: bold;
            margin-bottom: 5px
        }
        
        .form-nu .input-control{
            height: 35px;
            width: 100%;
            border: 1px solid #ccc;
            border-radius: 5px;
            padding: 5px;
        }

        .form-nu .input-control:focus{
            outline: none;
        }
        
        .form-nu .btn{
            height: 40px;
            width: 100%;
            font-size: 16px;
            font-weight: 500;
            text-transform: capitalize;
            font-family: inherit;
            cursor: pointer;
            border-radius: 25px;
            user-select: none;
            background: #004a8c;
            border: none;
            color: #f6f6f6;
            margin-top: 20px;
        }
        
       
    </style>
    
    
    <%  response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");  
            if (session.getAttribute("user")==null){
                response.sendRedirect("Login.jsp");
            }
    %>

    <body style="position: relative; justify-content: center; background-image: url(https://img.freepik.com/vector-premium/mosaico-poligonal-fondo-repetitivo-abstracto-ilustracion-vectorial_676179-405.jpg)">
        <div class="cerrarSesion">
            <a href="MenuCliente.jsp" class="regresar"><i class="fa-solid fa-circle-left" style="font-size: 6vh; padding: 2vh 0;"></i>Regresar Pagina</a>
            <a href="CerrarSesion" class="sesion"><i class="fa-solid fa-power-off" style="font-size: 6vh; padding: 2vh 0;"></i>Cerrar Sesión</a>
        </div>
        
        <section class="form-main">
            <div class="form-content">
                <div class="boxy">
                    <h3 class="titulo">EDITAR PRODUCTO</h3>

                    <form class="form-nu" action="ControlerProducto" method="POST">
                        <c:forEach var="campo" items="${Lista}">
                            <div class="input-box">
                                <label>Id Cliente:</label>
                                <input type="text" name="idProd" value="${campo.idProd}" readonly/>
                            </div>
                            <div class="input-box">
                                <label>Apellidos:</label>
                                <input type="text" name="descripcion" value="${campo.descripcion}" required/>
                            </div>
                            <div class="input-box">
                                <label>Nombres:</label>
                                <input type="text" name="costo" value="${campo.costo}" required/>
                            </div>
                            <div class="input-box">
                                <label>DNI:</label>
                                <input type="text" name="precio" value="${campo.precio}" required/>
                            </div>
                            <div class="input-box">
                                <label>Dirección:</label>
                                <input type="text" name="cantidad" value="${campo.cantidad}" required/>
                            </div>                            
                        </c:forEach>
                        <button type="submit" class="btn"> Modificar</button>
                    </form>
                </div>
            </div>
        </section>



        <%@ include file="ModalSesionExpirada.jsp" %>
    </body>
</html>