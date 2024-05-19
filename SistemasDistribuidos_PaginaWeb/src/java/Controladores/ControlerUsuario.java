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
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
