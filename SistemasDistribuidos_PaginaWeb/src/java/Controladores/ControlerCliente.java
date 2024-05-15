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
            case "Eliminar":
                try {
                    String Id = request.getParameter("Id");
                    String sqlGetEstado = "SELECT estado FROM t_cliente WHERE Id_Cliente = ?";
                    String sqlUpdateEstado = "";

                    conn = conBD.Conexion();

                    // Obtener el estado actual del cliente
                    ps = conn.prepareStatement(sqlGetEstado);
                    ps.setString(1, Id);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        String estadoActual = rs.getString("estado");

                        // Si el estado actual es 'activo', cambia a 'inactivo'; si es 'inactivo', cambia a 'activo'
                        if (estadoActual == null || estadoActual.isEmpty() || estadoActual.equals("activo")) {
                            sqlUpdateEstado = "UPDATE t_cliente SET estado = 'inactivo' WHERE Id_Cliente = ?";
                        } else if (estadoActual.equals("inactivo")) {
                            sqlUpdateEstado = "UPDATE t_cliente SET estado = 'activo' WHERE Id_Cliente = ?";
                        }

                        // Ejecutar la actualización de estado
                        ps = conn.prepareStatement(sqlUpdateEstado);
                        ps.setString(1, Id);
                        ps.executeUpdate();
                    }
                    
                    // para volver a listar clientes
                    
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
            case "EditarCliente":
                try{
                    String Id=request.getParameter("Id");
                    String sql="select * from t_cliente where Id_Cliente=?";
                    ps= conn.prepareStatement(sql);
                    ps.setString(1, Id);
                    rs= ps.executeQuery();
                    Cliente client=new Cliente();
                    while(rs.next()){
                        client.setId(rs.getString("Id_Cliente"));
                        client.setApellidos(rs.getString("Apellidos"));
                        client.setNombres(rs.getString("Nombres"));
                        client.setDNI(rs.getString("DNI"));
                        client.setDireccion(rs.getString("Direccion"));
                        client.setTelefono(rs.getString("Telefono"));
                        client.setMovil(rs.getString("Movil"));
                        Lista.add(client);
                    }
                    request.setAttribute("Lista", Lista);
                    request.getRequestDispatcher("EditarCliente.jsp").forward(request, response);
                }catch(SQLException ex){
                    System.out.println("Error de SQL..."+ex.getMessage());
                } finally{
                    conBD.Discconet();
                }                 
                
                break;   
            default:

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        

String Id =request.getParameter("Id");       
        String Apellidos=request.getParameter("apellidos"); 
        String Nombres=request.getParameter("nombres"); 
        String DNI=request.getParameter("DNI");
        String Direccion=request.getParameter("direccion"); 
        String Telefono=request.getParameter("telefono"); 
        String Movil=request.getParameter("movil"); 
        Cliente client=new Cliente();
        
        client.setId(Id);
        client.setApellidos(Apellidos);
        client.setNombres(Nombres);
        client.setDNI(DNI);
        client.setDireccion(Direccion);
        client.setTelefono(Telefono);
        client.setMovil(Movil);     
        
        Conexion.Conexion conBD = new Conexion.Conexion();
        Connection conn = conBD.Conexion();
        PreparedStatement ps;
        ResultSet rs;        
        
  
            String sql="update t_cliente set apellidos=?, nombres=?, DNI=?, direccion=?, telefono=?, movil=? where Id_Cliente=?";

            try{
                ps= conn.prepareStatement(sql);
                ps.setString(1, client.getApellidos());
                ps.setString(2, client.getNombres());
                ps.setString(3, client.getDNI());
                ps.setString(4, client.getDireccion());
                ps.setString(5, client.getTelefono());
                ps.setString(6, client.getMovil());
                ps.setString(7, client.getId());
                ps.executeUpdate(); 
            }catch(SQLException ex){
                System.out.println("Error actualizando tabla..."+ex.getMessage());
            } finally{
                conBD.Discconet();
            }  
            response.sendRedirect("MenuCliente.jsp");
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
