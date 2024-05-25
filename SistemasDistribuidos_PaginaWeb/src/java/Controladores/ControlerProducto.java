package Controladores;

import Entidades.Producto;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author maria
 */
public class ControlerProducto extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String opcion = request.getParameter("Op");
        ArrayList<Producto> Lista = new ArrayList<>();
        Conexion.Conexion conBD = new Conexion.Conexion();
        Connection conn = conBD.Conexion();
        PreparedStatement ps;
        ResultSet rs;

        switch (opcion) {
            case "Listar":

                break;
            case "Eliminar":

                break;
            case "EditarProducto":

                break;

            case "NuevoProducto":                  
                try {
                String idProd = GenerarNuevoId();
                String descripcion = request.getParameter("Descripcion");
                Double costo = Double.valueOf(request.getParameter("costo"));
                Double precio = Double.valueOf(request.getParameter("precio"));
                Double cantidad = Double.valueOf(request.getParameter("cantidad"));

                conn = conBD.Conexion();
                String sql = "INSERT INTO t_producto (Id_Prod, Descripcion, costo, precio, cantidad) VALUES (?, ?, ?, ?, ?)";

                ps = conn.prepareStatement(sql);
                ps.setString(1, idProd);
                ps.setString(2, descripcion);
                ps.setDouble(3, costo);
                ps.setDouble(4, precio);
                ps.setDouble(5, cantidad);

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    response.sendRedirect("NuevoProducto.jsp");
                } else {
                    response.sendRedirect("Error.jsp");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                response.sendRedirect("Error.jsp");
            } finally {
                conBD.Discconet();
            }
            break;
            default:

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private String GenerarNuevoId() {
        Conexion.Conexion conBD = new Conexion.Conexion();
        Connection conn = conBD.Conexion();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sqlLastId = "SELECT Id_Prod FROM t_producto ORDER BY Id_Prod DESC LIMIT 1";
            ps = conn.prepareStatement(sqlLastId);
            rs = ps.executeQuery();

            if (rs.next()) {
                String lastIdWithPrefix = rs.getString("Id_Prod");
                int num = Integer.parseInt(lastIdWithPrefix.substring(1));
                String nextId = String.format("%05d", num + 1);
                return "P" + nextId;
            } else {
                return "P00001";
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return "Error";
        } finally {
            conBD.Discconet();
        }
    }

}
