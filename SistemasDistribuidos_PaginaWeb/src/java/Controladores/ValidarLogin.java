package Controladores;

import Entidades.Usuario;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet para validar el login de los usuarios.
 * Bloquea al usuario si se exceden los intentos fallidos de inicio de sesión.
 */
public class ValidarLogin extends HttpServlet {

    private static final int MAX_INTENTOS = 4;
    private static final long BLOQUEO_TIEMPO_MS = 10000; // 10 segundos

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Conexion.Conexion conBD = new Conexion.Conexion();
        Connection conn = conBD.Conexion();
        PreparedStatement ps;
        ResultSet rs;

        String usuario = request.getParameter("txtUsuario");
        String contraseña = request.getParameter("contra");

        // Convertir la contraseña ingresada a su hash MD5 equivalente
        String contraseñaEncriptada = encriptarMD5(contraseña);

        HttpSession session = request.getSession();
        Integer intentosFallidos = (Integer) session.getAttribute("intentosFallidos_" + usuario);
        Long tiempoBloqueo = (Long) session.getAttribute("tiempoBloqueo_" + usuario);

        if (intentosFallidos == null) {
            intentosFallidos = 0;
        }

        long ahora = System.currentTimeMillis();
        if (tiempoBloqueo != null && ahora < tiempoBloqueo) {
            response.sendRedirect("Login.jsp?error=bloqueado");
            return;
        }

        try {
            String sql = "SELECT * FROM t_usuario WHERE IdUsuario=? ";
            ps = conn.prepareStatement(sql);
            ps.setString(1, usuario);

            rs = ps.executeQuery();

            if (rs.next()) {
                String contraseñaAlmacenada = rs.getString("Passwd");
                String estadoUsuario = rs.getString("Estado");

                if ("activo".equalsIgnoreCase(estadoUsuario)) {
                    if (contraseñaAlmacenada.equals(contraseñaEncriptada)) {
                        // Autenticación exitosa
                        Usuario nuser = new Usuario(usuario, contraseña);
                        session.setAttribute("user", nuser);
                        session.setAttribute("IdUsuario", usuario); // Asegúrate de que el ID del usuario se configura en la sesión
                        session.removeAttribute("intentosFallidos_" + usuario);
                        session.removeAttribute("tiempoBloqueo_" + usuario);
                        request.getRequestDispatcher("MenuPrincipal.jsp").forward(request, response);
                    } else {
                        // Contraseña incorrecta
                        intentosFallidos++;
                        session.setAttribute("intentosFallidos_" + usuario, intentosFallidos);
                        if (intentosFallidos >= MAX_INTENTOS) {
                            session.setAttribute("tiempoBloqueo_" + usuario, ahora + BLOQUEO_TIEMPO_MS);
                            response.sendRedirect("Login.jsp?error=usuario_bloqueado");
                        } else {
                            response.sendRedirect("Login.jsp?error=incorrecto");
                        }
                    }
                } else {
                    // Usuario inactivo
                    response.sendRedirect("Login.jsp?error=inactivo");
                }
            } else {
                // Usuario no encontrado
                response.sendRedirect("Login.jsp?error=incorrecto");
            }
        } catch (SQLException ex) {
            System.out.println("Error de SQL..." + ex.getMessage());
        } 
    }

    private String encriptarMD5(String contraseña) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashBytes = md.digest(contraseña.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet para validar el login de los usuarios.";
    }
}
