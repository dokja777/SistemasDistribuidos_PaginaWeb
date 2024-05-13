package Controladores;

import Entidades.Cliente;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControlerCliente extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String opcion = request.getParameter("Op");
        ArrayList<Cliente> Lista = new ArrayList<>();
        Conexion.Conexion conBD = new Conexion.Conexion();
        Connection conn = conBD.Conexion();
        PreparedStatement ps;
        ResultSet rs;

        switch (opcion) {
            case "Listar":
                try {
                    String sql = "SELECT * FROM t_cliente";
                    ps = conn.prepareStatement(sql);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        Cliente client = new Cliente();
                        client.setId(rs.getString("Id_Cliente"));
                        client.setApellidos(rs.getString("Apellidos"));
                        client.setNombres(rs.getString("Nombres"));
                        client.setDNI(rs.getString("DNI"));
                        client.setDireccion(rs.getString("Direccion"));
                        client.setTelefono(rs.getString("Telefono"));
                        client.setMovil(rs.getString("Movil"));
                        client.setEstado(rs.getString("Estado"));
                        Lista.add(client);
                    }
                    request.setAttribute("Lista", Lista);
                    request.getRequestDispatcher("ListaCliente.jsp").forward(request, response);
                } catch (SQLException ex) {
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
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
