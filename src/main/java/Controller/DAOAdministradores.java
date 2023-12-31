package Controller;

import Model.DTOAdministradores;
import Model.DTOClientes;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOAdministradores {
    //medoto para validar login
    public static Integer ValidateCredentials(String usuario, String clave){
        try(Connection con = SingletonConnection.GetDBConnection()) {
            PreparedStatement statement= con.prepareStatement("SELECT clave FROM administrador WHERE correos = ?");
            statement.setString(1,usuario);


            ResultSet claveDevuelta = statement.executeQuery();
            while (claveDevuelta.next()){
                if (claveDevuelta.getString("clave").equals(String.valueOf(clave.hashCode()))){
                    //Credenciales correctas
                    return 0;
                }else {
                    //Usuario existe pero clave es incorrecta
                    return 1;
                }
            }
        }catch (Exception e){
            System.out.println("error al consultar la db y obtener correos: "+e);
        }
        //Usuario Incorrecto o Error
        return 2;
    }

    public static void InsertManager(DTOAdministradores InfoAdmin){
        try(Connection con = SingletonConnection.GetDBConnection()) {
            PreparedStatement statement= con.prepareStatement("" +
                    "INSERT INTO adminitrador(nombres,correos,telefono,direccion,clave) " +
                    "VALUES(?,?,?,?,?)");
            statement.setString(1,InfoAdmin.getNombres());
            statement.setString(2,InfoAdmin.getCorreo());
            statement.setString(3,InfoAdmin.getTelefono());
            statement.setString(4,InfoAdmin.getDireccion());
            statement.setString(5,String.valueOf(InfoAdmin.getClave().hashCode()));

            statement.execute();
            JOptionPane.showMessageDialog(null, "Administrador insertado");


        }catch (Exception e){
            System.out.println("error al insertar un administrador: "+e);
        }
    }

    /*
    public List<DTOClientes> ListClientes(Integer idCliente){
        List<DTOClientes> clientes = new ArrayList<>();

        try(Connection con = SingletonConnection.GetDBConnection()) {
            PreparedStatement query = con
                .prepareStatement("SELECT * FROM clientes where id_cliente = ? ");

            query.setInt(1,idCliente);
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
        }catch (Exception e){

        }
        return clientes;
    }
    */
}
