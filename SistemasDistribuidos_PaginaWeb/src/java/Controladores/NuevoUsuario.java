/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controladores;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Dayanna
 */
@WebServlet(name = "NuevoUsuario", urlPatterns = {"/NuevoUsuario"})
public class NuevoUsuario extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        String idUsuario = request.getParameter("idUsuario"); 
        String apellidos = request.getParameter("apellidos");
        String nombres = request.getParameter("nombres");
        String direccion = request.getParameter("direccion");
        String dni = request.getParameter("dni");
        String telefono = request.getParameter("telefono");
        String movil = request.getParameter("movil");
        String password = request.getParameter("password"); 
        String estado = "activo";

        Conexion.Conexion conBD = new Conexion.Conexion();
        Connection conn = conBD.Conexion();
        PreparedStatement ps = null;
        String sql = "INSERT INTO t_usuario (IdUsuario, Apellidos, Nombres, Direccion, DNI, Telefono, Movil, Passwd, Estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, idUsuario);
            ps.setString(2, apellidos);
            ps.setString(3, nombres);
            ps.setString(4, direccion);
            ps.setString(5, dni);
            ps.setString(6, telefono);
            ps.setString(7, movil);
            ps.setString(8, password); 
            ps.setString(9, estado);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                response.sendRedirect("MenuUsuario.jsp");
            } else {
                response.sendRedirect("Error.jsp");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            response.sendRedirect("Error.jsp");
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                conBD.Discconet(); 
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }    
    }


    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
