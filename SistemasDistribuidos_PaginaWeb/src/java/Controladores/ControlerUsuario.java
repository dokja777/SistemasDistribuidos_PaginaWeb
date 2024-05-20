/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controladores;

import Entidades.Usuario;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "ControlerUsuario", urlPatterns = {"/ControlerUsuario"})
public class ControlerUsuario extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String opcion = request.getParameter("Op");
        ArrayList<Usuario> Lista = new ArrayList<>();
        Conexion.Conexion conBD = new Conexion.Conexion();
        Connection conn = conBD.Conexion();
        PreparedStatement ps;
        ResultSet rs;
        
        switch (opcion) {
    case "Listar":
        try {
            String sql = "SELECT * FROM t_usuario";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Usuario user = new Usuario();
                user.setId_usuario(rs.getString("IdUsuario"));
                user.setApellidos(rs.getString("Apellidos"));
                user.setNombres(rs.getString("Nombres"));
                user.setDireccion(rs.getString("Direccion"));
                user.setDNI(rs.getString("DNI"));
                user.setTelefono(rs.getString("Telefono"));
                user.setMovil(rs.getString("Movil"));
                user.setEnlinea(rs.getString("EnLinea"));
                user.setEstado(rs.getString("Estado"));
                Lista.add(user);
            }
            request.setAttribute("Lista", Lista);
            request.getRequestDispatcher("ListaUsuario.jsp").forward(request, response);
        } catch (SQLException ex) {
            System.out.println("Error de SQL..." + ex.getMessage());
        } finally {
            conBD.Discconet();
        }
        break;
        case "EliminarUsuario":
               try {
                    String idUsuario = request.getParameter("IdUsuario");
                    String sqlGetEstado = "SELECT Estado FROM t_usuario WHERE IdUsuario = ?";
                    String sqlUpdateEstado = "";

                    conn = conBD.Conexion();

                    // Obtener el estado actual del usuario
                    ps = conn.prepareStatement(sqlGetEstado);
                    ps.setString(1, idUsuario);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        String estadoActual = rs.getString("Estado");

                        // Si el estado actual es 'activo', cambia a 'inactivo'; si es 'inactivo', cambia a 'activo'
                        if (estadoActual == null || estadoActual.isEmpty() || estadoActual.equals("activo")) {
                            sqlUpdateEstado = "UPDATE t_usuario SET Estado = 'inactivo' WHERE IdUsuario = ?";
                        } else if (estadoActual.equals("inactivo")) {
                            sqlUpdateEstado = "UPDATE t_usuario SET Estado = 'activo' WHERE IdUsuario = ?";
                        }

                        // Ejecutar la actualización de estado
                        ps = conn.prepareStatement(sqlUpdateEstado);
                        ps.setString(1, idUsuario);
                        ps.executeUpdate();
                    }

                    // Volver a listar usuarios
                    String sql = "SELECT * FROM t_usuario";
                    ps = conn.prepareStatement(sql);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        Usuario user = new Usuario();
                        user.setId_usuario(rs.getString("IdUsuario"));
                        user.setApellidos(rs.getString("Apellidos"));
                        user.setNombres(rs.getString("Nombres"));
                        user.setDireccion(rs.getString("Direccion"));
                        user.setDNI(rs.getString("DNI"));
                        user.setTelefono(rs.getString("Telefono"));
                        user.setMovil(rs.getString("Movil"));
                        user.setEstado(rs.getString("Estado"));
                        Lista.add(user);
                    }
                    request.setAttribute("Lista", Lista);
                    request.getRequestDispatcher("ListaUsuario.jsp").forward(request, response);

                } catch (SQLException ex) {
                    System.out.println("Error de SQL..." + ex.getMessage());
                } finally {
                    conBD.Discconet();
                }                                 
                break;
            case "EditarUsuario":
                try{
                    String idUsuario = request.getParameter("IdUsuario");
                    String sql = "SELECT * FROM t_usuario WHERE IdUsuario=?";
                    ps = conn.prepareStatement(sql);
                    ps.setString(1, idUsuario);
                    rs = ps.executeQuery();
                    Usuario user = new Usuario();
                    while(rs.next()) {
                        user.setId_usuario(rs.getString("IdUsuario"));
                        user.setApellidos(rs.getString("Apellidos"));
                        user.setNombres(rs.getString("Nombres"));
                        user.setDNI(rs.getString("DNI"));
                        user.setDireccion(rs.getString("Direccion"));
                        user.setTelefono(rs.getString("Telefono"));
                        user.setMovil(rs.getString("Movil"));
                        Lista.add(user);
                    }
                    request.setAttribute("Lista", Lista);
                    request.getRequestDispatcher("EditarUsuario.jsp").forward(request, response);
                } catch(SQLException ex) {
                    System.out.println("Error de SQL..." + ex.getMessage());
                } finally {
                    conBD.Discconet();
                }                 
                break;   
            default:
        }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       String idUsuario = request.getParameter("IdUsuario");       
        String apellidos = request.getParameter("apellidos"); 
        String nombres = request.getParameter("nombres"); 
        String DNI = request.getParameter("DNI");
        String direccion = request.getParameter("direccion"); 
        String telefono = request.getParameter("telefono"); 
        String movil = request.getParameter("movil"); 
        Usuario user = new Usuario();
        
        user.setId_usuario(idUsuario);
        user.setApellidos(apellidos);
        user.setNombres(nombres);
        user.setDNI(DNI);
        user.setDireccion(direccion);
        user.setTelefono(telefono);
        user.setMovil(movil);     
        
        Conexion.Conexion conBD = new Conexion.Conexion();
        Connection conn = conBD.Conexion();
        PreparedStatement ps;
        
        try {
            String sql = "UPDATE t_usuario SET Apellidos=?, Nombres=?, DNI=?, Direccion=?, Telefono=?, Movil=? WHERE IdUsuario=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getApellidos());
            ps.setString(2, user.getNombres());
            ps.setString(3, user.getDNI());
            ps.setString(4, user.getDireccion());
            ps.setString(5, user.getTelefono());
            ps.setString(6, user.getMovil());
            ps.setString(7, user.getId_usuario());
            ps.executeUpdate(); 
        } catch (SQLException ex) {
            System.out.println("Error actualizando tabla..." + ex.getMessage());
        } finally {
            conBD.Discconet();
        }  
        response.sendRedirect("MenuUsuario.jsp");
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
