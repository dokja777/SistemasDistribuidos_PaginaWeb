<%-- 
    Document   : ListaCliente
    Created on : 5 may 2024, 13:29:53
    Author     : danie
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page import="Entidades.Cliente"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    List<Cliente> Lista= (List<Cliente>) request.getAttribute("Lista");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <script src="https://kit.fontawesome.com/26a3cc7edf.js" crossorigin="anonymous"></script>
        
        <link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Bebas+Neue&family=Jaro:opsz@6..72&display=swap" rel="stylesheet">
    </head>  
    
  
  <style>
      * {
            margin: 0;
            padding: 0;
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
            margin: 2vh 0 2vh 0;
            font-family: "Bebas Neue", sans-serif;
            font-weight: 400;
            font-style: normal;
        }
  </style>
    <body style="position: relative; display: flex; justify-content: center; background-image: url(https://img.freepik.com/vector-premium/mosaico-poligonal-fondo-repetitivo-abstracto-ilustracion-vectorial_676179-405.jpg)">
        
        <%  response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");  
            if (session.getAttribute("user")==null){
                response.sendRedirect("Login.jsp");
            }
    %>
        
        
        
        <div class="cerrarSesion">
            <a href="MenuCliente.jsp" class="regresar"><i class="fa-solid fa-circle-left" style="font-size: 6vh; padding: 2vh 0;"></i>Regresar Pagina</a>
            <a href="CerrarSesion" class="sesion"><i class="fa-solid fa-power-off" style="font-size: 6vh; padding: 2vh 0;"></i>Cerrar Sesión</a>
        </div>
        
        <div class="tabla" style=" width: 70%">
            <div class="titulo">LISTA CLIENTES</div>
            
            <table class="table table-striped">
                
                <thead>
                  <tr>
                    
                    <th scope="col">idCliente</th>
                    <th scope="col">Apellidos</th>
                    <th scope="col">Nombres</th>
                    <th scope="col">Direccion</th>
                    <th scope="col">Dni</th>
                    <th scope="col">Telefono</th>
                    <th scope="col">Movil</th>
                    <th scope="col">Estado</th>
                    <th scope="col">En Linea</th>
                    <th scope="col">Editar</th>
                    <th scope="col">Eliminar</th>
                  </tr>
                </thead>
                
                
                <c:forEach var="campo" items="${Lista}">
                <tbody>              
                    
                   
                        
                <tr>
                    
                    <td>${campo.id}</td>
                    <td>${campo.apellidos}</td>
                     <td>${campo.nombres}</td>
                     <td>${campo.direccion}</td>
                     <td>${campo.DNI}</td>
                     
                     <td>${campo.telefono}</td>
                     <td>${campo.movil}</td>
                     <td>${campo.estado}</td>
                     <td>${campo.enLinea}</td>
                     <td><a href="ControlerCliente?Op=EditarCliente&Id=${campo.id}" class="btn btn-primary"><i class="fas fa-edit"></i> Editar</a></td>
                     <td><a href="ControlerCliente?Op=Eliminar&Id=${campo.id}" 
                            class="btn btn-danger" onclick="return confirm('¿Estás seguro de que deseas eliminar este cliente?');">
                            <i class="fas fa-trash-alt"></i> Eliminar</a></td>
                </tr>        
                
                    
                  
                  
                </tbody>
                </c:forEach>
              </table>
            
        </div>
            
        <%@ include file="ModalSesionExpirada.jsp" %>
        
    </body>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
</html>
