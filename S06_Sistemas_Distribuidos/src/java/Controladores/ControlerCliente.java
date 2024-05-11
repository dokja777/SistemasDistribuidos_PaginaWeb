/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controladores;

import Entidades.Cliente;
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
import javax.servlet.http.HttpUtils;

/**
 *
 * @author danie
 */
public class ControlerCliente extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet ControlerCliente</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet ControlerCliente at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
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
//        processRequest(request, response);
        String opcion=request.getParameter("Op");
        ArrayList<Cliente> Lista= new ArrayList<Cliente>();
        Conexion.Conexion conBD = new Conexion.Conexion();
        Connection conn = conBD.Conexion();
        PreparedStatement ps;
        ResultSet rs;
        
        switch (opcion) {
            case "Listar":
                try{
                    String sql="SELECT * FROM t_cliente";
                    ps= conn.prepareStatement(sql);
                    rs= ps.executeQuery();
                    while(rs.next()){
                        Cliente client=new Cliente();
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
                    request.getRequestDispatcher("ListaCliente.jsp").forward(request, response);
                }catch(SQLException ex){
                    System.out.println("Error de SQL..."+ex.getMessage());
                }finally{
                    conBD.Discconet();
                }
                
                break;
            default:
                
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
