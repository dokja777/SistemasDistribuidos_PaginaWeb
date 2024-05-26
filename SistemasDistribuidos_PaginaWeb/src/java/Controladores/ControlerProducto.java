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

        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        String opcion = request.getParameter("Op");
        ArrayList<Producto> Lista = new ArrayList<>();
        Conexion.Conexion conBD = new Conexion.Conexion();
        Connection conn = conBD.Conexion();
        PreparedStatement ps;
        ResultSet rs;

        switch (opcion) {
            case "Listar":
                try {
                    String sql = "SELECT * FROM t_producto";
                    ps = conn.prepareStatement(sql);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        Producto producto = new Producto();
                        producto.setIdProd(rs.getString("Id_Prod"));
                        producto.setDescripcion(rs.getString("Descripcion"));
                        producto.setCosto(rs.getDouble("costo"));
                        producto.setPrecio(rs.getDouble("precio"));
                        producto.setCantidad(rs.getDouble("cantidad"));                        
                        Lista.add(producto);
                    }
                    request.setAttribute("Lista", Lista);
                    request.getRequestDispatcher("ListaProducto.jsp").forward(request, response);
                } catch (SQLException ex) {
                    System.out.println("Error de SQL..." + ex.getMessage());
                } finally {
                    conBD.Discconet();
                }
                break;
            case "Eliminar":
                try {
                    String Id = request.getParameter("Id");
                    String sqlGetEstado = "SELECT estado FROM t_producto WHERE Id_Prod = ?";
                    String sqlUpdateEstado = "";

                    conn = conBD.Conexion();

                    // Obtener el estado actual del cliente
                    ps = conn.prepareStatement(sqlGetEstado);
                    ps.setString(1, Id);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        //String estadoActual = rs.getString("estado");

//                        // Si el estado actual es 'activo', cambia a 'inactivo'; si es 'inactivo', cambia a 'activo'
//                        if (estadoActual == null || estadoActual.isEmpty() || estadoActual.equals("activo")) {
//                            sqlUpdateEstado = "UPDATE t_producto SET estado = 'inactivo' WHERE Id_Cliente = ?";
//                        } else if (estadoActual.equals("inactivo")) {
//                            sqlUpdateEstado = "UPDATE t_cliente SET estado = 'activo' WHERE Id_Cliente = ?";
//                        }

                        // Ejecutar la actualización de estado
                        ps = conn.prepareStatement(sqlUpdateEstado);
                        ps.setString(1, Id);
                        ps.executeUpdate();
                    }
                    
                    // para volver a listar clientes
                    
                    String sql = "SELECT * FROM t_producto";
                    ps = conn.prepareStatement(sql);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        Producto producto = new Producto();
                        producto.setIdProd(rs.getString("Id_Prod"));
                        producto.setDescripcion(rs.getString("Descipcion"));
                        producto.setCosto(rs.getDouble("costo"));
                        producto.setPrecio(rs.getDouble("precio"));
                        producto.setCantidad(rs.getDouble("cantidad"));
                        
                        Lista.add(producto);
                    }
                    request.setAttribute("Lista", Lista);
                    request.getRequestDispatcher("ListaProducto.jsp").forward(request, response);

                } catch (SQLException ex) {
                    System.out.println("Error de SQL..." + ex.getMessage());
                } finally {
                    conBD.Discconet();
                }         
                break;
            case "EditarProducto":
                try{
                    String Id=request.getParameter("Id");
                    String sql="select * from t_producto where Id_Prod=?";
                    ps= conn.prepareStatement(sql);
                    ps.setString(1, Id);
                    rs= ps.executeQuery();
                    Producto producto=new Producto();
                    while(rs.next()){
                        
                        producto.setIdProd(rs.getString("Id_Prod"));
                        producto.setDescripcion(rs.getString("Descripcion"));
                        producto.setCosto(rs.getDouble("costo"));
                        producto.setPrecio(rs.getDouble("precio"));
                        producto.setCantidad(rs.getDouble("cantidad"));                        
                        Lista.add(producto);
                    }
                    request.setAttribute("Lista", Lista);
                    request.getRequestDispatcher("EditarProducto.jsp").forward(request, response);
                }catch(SQLException ex){
                    System.out.println("Error de SQL..."+ex.getMessage());
                } finally{
                    conBD.Discconet();
                }        
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        String Id =request.getParameter("idProd");       
        String descripacion=request.getParameter("descripcion"); 
        String costo=request.getParameter("costo"); 
        String precio=request.getParameter("precio");
        String cantidad=request.getParameter("cantidad"); 
//        String Telefono=request.getParameter("telefono"); 
//        String Movil=request.getParameter("movil"); 
        Producto producto=new Producto();
        
        producto.setIdProd(Id);
        producto.setDescripcion(descripacion);
        producto.setCosto(Double.parseDouble(costo));        
        producto.setPrecio(Double.parseDouble(precio));
        producto.setCantidad(Double.parseDouble(cantidad));
        
        
        Conexion.Conexion conBD = new Conexion.Conexion();
        Connection conn = conBD.Conexion();
        PreparedStatement ps;
        ResultSet rs;        
        
  
            String sql="update t_producto set descripcion=?, costo=?, precio=?, cantidad=? where Id_Prod=?";

            try{
                ps= conn.prepareStatement(sql);
                ps.setString(1, producto.getDescripcion());
                ps.setDouble(2, producto.getCosto());
                ps.setDouble(3, producto.getPrecio());
                ps.setDouble(4, producto.getCantidad());
                ps.setString(5, producto.getIdProd());
                
                ps.executeUpdate(); 
            }catch(SQLException ex){
                System.out.println("Error actualizando tabla..."+ex.getMessage());
            } finally{
                conBD.Discconet();
            }  
            response.sendRedirect("MenuProducto.jsp");
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
