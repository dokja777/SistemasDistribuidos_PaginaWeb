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

@WebServlet(name = "NuevoCliente", urlPatterns = {"/NuevoCliente"})
public class NuevoCliente extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idCliente = GenerarnuevoId(); 
        String apellidos = request.getParameter("apellidos");
        String nombres = request.getParameter("nombres");
        String direccion = request.getParameter("direccion");
        String dni = request.getParameter("dni");
        String telefono = request.getParameter("telefono");
        String movil = request.getParameter("movil");
        String estado = "ACTIVADO";

        Conexion.Conexion conBD = new Conexion.Conexion();
        Connection conn = conBD.Conexion();
        PreparedStatement ps = null;
        String sql = "INSERT INTO t_cliente (Id_Cliente, Apellidos, Nombres, Direccion, DNI, Telefono, Movil, Estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, idCliente);
            ps.setString(2, apellidos);
            ps.setString(3, nombres);
            ps.setString(4, direccion);
            ps.setString(5, dni);
            ps.setString(6, telefono);
            ps.setString(7, movil);
            ps.setString(8, estado);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                response.sendRedirect("MenuCliente.jsp");
            } else {
                response.sendRedirect("error.jsp");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            response.sendRedirect("error.jsp");
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

    private String GenerarnuevoId() {
        Conexion.Conexion conBD = new Conexion.Conexion();
        Connection conn = conBD.Conexion();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sqlLastId = "SELECT Id_Cliente FROM t_cliente ORDER BY Id_Cliente DESC LIMIT 1";
            ps = conn.prepareStatement(sqlLastId);
            rs = ps.executeQuery();

            if (rs.next()) {
                String lastIdWithPrefix = rs.getString("Id_Cliente");
                int num = Integer.parseInt(lastIdWithPrefix.substring(1));
                String nextId = String.format("%05d", num + 1); 
                return "C" + nextId; 
            } else {
                return "C00001"; 
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return "Error";
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
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
        return "error";
    }
}
