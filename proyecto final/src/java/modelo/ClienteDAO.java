/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author edwin garcia
 */

public class ClienteDAO {
    private Connection connection;

    public ClienteDAO(Connection connection) {
        this.connection = connection;
    }

    // Métodos CRUD

    // Crear cliente
    public void crearCliente(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO clientes (nombres, apellidos, nit, genero, telefono, correo, fecha_ingreso) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, cliente.getNombres());
            statement.setString(2, cliente.getApellidos());
            statement.setString(3, cliente.getNit());
            statement.setString(4, cliente.getGenero());
            statement.setString(5, cliente.getTelefono());
            statement.setString(6, cliente.getCorreo());
            statement.setDate(7, new java.sql.Date(cliente.getFechaIngreso().getTime()));
            statement.executeUpdate();
        }
    }

    // Leer cliente por ID
    public Cliente obtenerClientePorId(int id) throws SQLException {
        String sql = "SELECT * FROM clientes WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Cliente(
                        resultSet.getInt("id"),
                        resultSet.getString("nombres"),
                        resultSet.getString("apellidos"),
                        resultSet.getString("nit"),
                        resultSet.getString("genero"),
                        resultSet.getString("telefono"),
                        resultSet.getString("correo"),
                        resultSet.getDate("fecha_ingreso")
                    );
                }
            }
        }
        return null;
    }

    // Actualizar cliente
    public void actualizarCliente(Cliente cliente) throws SQLException {
        String sql = "UPDATE clientes SET nombres = ?, apellidos = ?, nit = ?, genero = ?, telefono = ?, correo = ?, fecha_ingreso = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, cliente.getNombres());
            statement.setString(2, cliente.getApellidos());
            statement.setString(3, cliente.getNit());
            statement.setString(4, cliente.getGenero());
            statement.setString(5, cliente.getTelefono());
            statement.setString(6, cliente.getCorreo());
            statement.setDate(7, new java.sql.Date(cliente.getFechaIngreso().getTime()));
            statement.setInt(8, cliente.getId());
            statement.executeUpdate();
        }
    }

    // Eliminar cliente
    public void eliminarCliente(int id) throws SQLException {
        String sql = "DELETE FROM clientes WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    // Listar clientes
    public List<Cliente> listarClientes() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Cliente cliente = new Cliente(
                    resultSet.getInt("id"),
                    resultSet.getString("nombres"),
                    resultSet.getString("apellidos"),
                    resultSet.getString("nit"),
                    resultSet.getString("genero"),
                    resultSet.getString("telefono"),
                    resultSet.getString("correo"),
                    resultSet.getDate("fecha_ingreso")
                );
                clientes.add(cliente);
            }
        }
        return clientes;
    }
}

public class cliente {
    
}
