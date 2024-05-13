<%-- 
    Document   : Login
    Created on : 5 may 2024, 13:29:10
    Author     : danie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    
    <link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Abel&family=EB+Garamond:ital,wght@0,400..800;1,400..800&display=swap" rel="stylesheet">
    
    
</head>

<style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            border-radius: 10vh;
            background-image: url(https://img.freepik.com/vector-premium/mosaico-poligonal-fondo-repetitivo-abstracto-ilustracion-vectorial_676179-405.jpg);
        }

        .todo {
            height: 92vh;
            width: 80%;
            display: flex;
        }

        .caja1 {
            background-color: white;
            width: 60%;
            
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            border-radius: 10vh 0vh 0vh 10vh;
        }

        .imagen {
            width: 38vh;
            height: 38vh;
            
        }

        .imagen img {
            width: 100%;
            height: 100%;
        }

        

        form {
            
            display: flex;
            flex-direction: column;
            align-items: center;
            width: 60%;
            margin: 0 auto;
            margin: 0.5vh;
        }

        input[type="text"],
        input[type="password"] {
            width: 100%;
            margin-bottom: 10px;
            height: 7vh;
            margin: 3vh;
        }

        button[type="submit"] {
            background-color: #4CAF50; /* Green */
            border: none;
            color: white;
            padding: 15px 32px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin: 4px 2px;
            transition-duration: 0.4s;
            cursor: pointer;
            border-radius: 10px;
            box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
            outline: none;
        }

        h3 {
            margin: 4vh;
            font-size: 2vh;
        }

        .caja2 {
            background-color: greenyellow;
            width: 60%;
            
            display: flex;
            justify-content: center;
            align-items: center;
            border-radius: 0vh 10vh 10vh 0vh;
        }

        h4 {
            width: 80%;
            text-align: justify;
            font-size: 4vh;
                
            font-family: "Abel", sans-serif;
            font-weight: 400;
            font-style: normal;


        }
    </style>


<body>
    
    
    
    <div class="todo">
        <div class="caja1">
            <div class="imagen">
                <img src="https://png.pngtree.com/png-clipart/20220213/original/pngtree-e-letter-logo-ecommerce-shop-store-design-png-image_7265997.png" alt="alt">
            </div>
            <h1>Ingrese sus datos</h1>
            <form action="ValidarLogin" method="POST">
                <input type="text" placeholder="Usuario" name="txtUsuario" autocomplete="off">
                <input type="password" placeholder="Contraseña" name="contra">
                
                <%
                   String error = request.getParameter("error");
                    if (error != null && error.equals("incorrecto")) {
                %>
                        <p style="color: red;">Los datos son incorrectos. Por favor, inténtelo de nuevo.</p>
                <%
                    }
                %>
                
                <button type="submit">Enviar</button>
                
            </form>
            <h3>Recuerda No Olvidar tus Datos Personales</h3>
        </div>

        <div class="caja2">
            <h4>
                ¡Descubre la excelencia de nuestra tienda de abarrotes! Encontrarás una amplia gama de productos frescos y de alta calidad, desde frutas y verduras recién cosechadas hasta carnes selectas y productos gourmet. Además, ofrecemos una variedad de productos de limpieza, cuidado personal y alimentos básicos a precios accesibles. Nuestro equipo está comprometido a brindarte un servicio excepcional, ayudándote a encontrar lo que necesitas y garantizando una experiencia de compra agradable. ¡Ven y descubre todo lo que tenemos para ofrecerte en nuestra tienda de abarrotes!
            </h4>
        </div>
    </div>
</body>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
</html>
