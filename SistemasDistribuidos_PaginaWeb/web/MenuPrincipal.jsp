<%-- 
    Document   : index
    Created on : 4 may 2024, 19:29:31
    Author     : danie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Bebas+Neue&family=Jaro:opsz@6..72&display=swap" rel="stylesheet">
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
            justify-content: flex-end;
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

        .contenedor {
            width: 100%;
            height: 100vh;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
        }

        .titulo {
            
            font-size: 20vh;
            margin: 2vh 0 10vh 0;
            font-family: "Bebas Neue", sans-serif;
            font-weight: 400;
            font-style: normal;
        }



        .caja {
            
            padding: 5vh 0;
            background-color: rgba(255, 255, 255, 0.8);
            width: 70%;
            display: flex;
            justify-content: space-around;
            align-items: center;
            border-radius: 4vh;
        }

        .opciones {
            
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
        }

        .imagen {
            
            width: 30vh;
            height: 30vh;
        }

        .opciones a {
            text-decoration: none;
            font-size: 6vh;
            color: black;
            width: 100%;
            text-align: center;
            border-radius: 5vh;
            border: 1px solid black;
            background-color: white;
        }

        .opciones a:hover {
            background-color: black;
            color: white;
        }
    </style>
    <script src="https://kit.fontawesome.com/26a3cc7edf.js" crossorigin="anonymous"></script>
</head>

<script src="ModalSesionExpirada.js"></script>

<body style="position: relative;">
    
    
    <%  
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");  
        if (session.getAttribute("user") == null) {
            response.sendRedirect("Login.jsp");
        }
        // Obtener el ID del usuario de la sesión
        String idUsuario = (String) session.getAttribute("IdUsuario");
        // Verificar si el usuario es administrador
        boolean esAdmin = idUsuario != null && idUsuario.startsWith("A");
    %>
    
    
    <img src="https://png.pngtree.com/thumb_back/fw800/background/20210724/pngtree-blue-white-square-squares-superimposed-stylish-geometric-abstract-background-image_752538.jpg" alt="" style="position: absolute; height: 100vh; width: 100%; z-index: -1; filter: brightness(90%);">
    <div class="cerrarSesion">
        <!--<a href="#" class="regresar"><i class="fa-solid fa-circle-left" style="font-size: 6vh; padding: 2vh 0;"></i>Regresar Pagina</a>-->
        <a href="CerrarSesion" class="sesion"><i class="fa-solid fa-power-off" style="font-size: 6vh; padding: 2vh 0;"></i>Cerrar Sesión</a>
    </div>
    <div class="contenedor">
        <div class="titulo">Menú Principal</div>
        <div class="caja">
            <div class="opciones">
                <div class="imagen">
                    <img src="https://cdn.icon-icons.com/icons2/3560/PNG/512/customer_man_avatar_person_people_thinking_about_shopping_shopping_ideas_icon_225182.png" alt="" style="width: 100%; height: 100%;">
                </div>
                <a href="MenuCliente.jsp">Cliente</a>
            </div>
            <div class="opciones">
                <div class="imagen">
                    <img src="https://cdn-icons-png.flaticon.com/512/3240/3240782.png" alt="" style="width: 100%; height: 100%;">
                </div>
                <a href="">Producto</a>
            </div>
            <div class="opciones">
                <div class="imagen">
                    <img src="https://cdn-icons-png.flaticon.com/512/6384/6384868.png" alt="" style="width: 100%; height: 100%;">
                </div>
                <a href="">Pedidos</a>
            </div>
            <% if (esAdmin) { %> <!-- Habilita la opción "Usuarios" si isAdmin es true -->
            <div class="opciones">
                <div class="imagen">
                    <img src="https://cdn.icon-icons.com/icons2/1485/PNG/512/checklist_102320.png" alt="" style="width: 100%; height: 100%;">
                </div>
                <a href="MenuUsuario.jsp">Usuarios</a>
            </div>
            <% } %>
        </div>
    </div>
        <%@ include file="ModalSesionExpirada.jsp" %>
</body>
</html>

