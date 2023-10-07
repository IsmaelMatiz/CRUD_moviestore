package Controller;

import Model.DTOClientes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOClientes {
    public List<DTOClientes> ListClientes() throws SQLException {
        List<DTOClientes> clientes = new ArrayList<>();

        Connection connection = SingletonConnection.GetDBConnection();

        PreparedStatement query = connection.prepareStatement("SELECT * FROM clientes");

        ResultSet clientesDB = query.executeQuery();

        while (clientesDB.next()) {
            DTOClientes cliente = new DTOClientes();

            cliente.setId_cliente(clientesDB.getInt("id_cliente"));
            cliente.setNombres(clientesDB.getString("nombres"));
            cliente.setTelefono(clientesDB.getString("telefono"));
            cliente.setDireccion(clientesDB.getString("direccion"));
            cliente.setCorreo(clientesDB.getString("correo"));

            clientes.add(cliente);
        }

        connection.close();

        return clientes;
    }
}

