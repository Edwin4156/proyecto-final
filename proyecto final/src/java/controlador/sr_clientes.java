/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

/**
 *
 * @author edwin garcia
 */import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;

public class ClienteServlet extends HttpServlet {
    private ClienteDAO clienteDAO;

    @Override
    public void init() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clientes_db", "root", "password");
            clienteDAO = new ClienteDAO(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("create".equals(action)) {
            String nombres = request.getParameter("nombres");
            String apellidos = request.getParameter("apellidos");
            String nit = request.getParameter("nit");
            String genero = request.getParameter("genero");
            String telefono = request.getParameter("telefono");
            String correo = request.getParameter("correo");
            Date fechaIngreso = new Date(); // o puedes capturar desde el formulario

            Cliente cliente = new Cliente(0, nombres, apellidos, nit, genero, telefono, correo, fechaIngreso);

            try {
                clienteDAO.crearCliente(cliente);
                response.sendRedirect("clientes.jsp");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    
}

public class sr_clientes {
    
}
