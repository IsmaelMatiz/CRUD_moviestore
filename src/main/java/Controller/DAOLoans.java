package Controller;

import Model.DTOLoans;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOLoans {
    public static List<DTOLoans> ListLoans() throws SQLException {
        ArrayList<DTOLoans> loans = new ArrayList<DTOLoans>();

        PreparedStatement query = SingletonConnection.GetDBConnection().prepareStatement("" +
                "SELECT * FROM prestamos");

        ResultSet moviesDB = query.executeQuery();

        while (moviesDB.next())
        {
            DTOLoans loan = new DTOLoans();

            loan.setId(moviesDB.getInt("id"));
            loan.setFecha_prestamo(moviesDB.getDate("fecha_prestamos").toLocalDate());
            loan.setFecha_devolucion(moviesDB.getDate("fecha_devolucion").toLocalDate());
            loan.setPrecio(moviesDB.getDouble("precio"));
            loan.setPeliculas_id(moviesDB.getInt("peliculas_id"));
            loan.setPeliculas_clientes_id_cliente(moviesDB.getInt("peliculas_clientes_id_cliente"));

            loans.add(loan);
        }
        return loans;
    }

    public  static void Insertloans(DTOLoans loanInfo){
        try(Connection con = SingletonConnection.GetDBConnection()) {
            PreparedStatement statement = con.prepareStatement(
                    "INSERT INTO prestamos (fecha_prestamos, fecha_devolucion, precio, peliculas_id, peliculas_clientes_id_cliente)" +
                            "VALUES (?,?,?,?,?)"
            );

            System.out.println("fecha_prestamo:= "+loanInfo.getFecha_prestamo());
            System.out.println("fecha_devolucion: ="+loanInfo.getFecha_devolucion());
            System.out.println("precio: ="+loanInfo.getPrecio());
            System.out.println("peliculas_id: ="+ loanInfo.getPeliculas_id());
            System.out.println("Peliculas_clientes_id_cliente: ="+loanInfo.getPeliculas_clientes_id_cliente());

            statement.setString(1, loanInfo.getFecha_prestamo().toString());
            statement.setString(2, loanInfo.getFecha_devolucion().toString());
            statement.setString(3, loanInfo.getPrecio().toString());
            statement.setInt(4, loanInfo.getPeliculas_id());
            statement.setInt(5, loanInfo.getPeliculas_clientes_id_cliente());

            statement.execute();

            JOptionPane.showMessageDialog(null, "prestamo insertado");

        }catch (Exception e){
            System.out.println("Problema al guardar prestamo"+e);
        }
    }

    public static void UpdateLoan(Integer loanId, DTOLoans loanInfo){
        try(Connection con = SingletonConnection.GetDBConnection()){
            // Deshabilita las restricciones de clave foránea
            PreparedStatement disableFK = con.prepareStatement("SET FOREIGN_KEY_CHECKS=0;");
            disableFK.execute();

            PreparedStatement statement = con.prepareStatement(
                    "UPDATE prestamos " +
                            "SET fecha_prestamos = ?, " +
                            "fecha_devolucion = ?, " +
                            "precio = ?, " +
                            "peliculas_id = ?, " +
                            "peliculas_clientes_id_cliente = ? " +
                            "WHERE id = ?"

            );

            statement.setString(1, loanInfo.getFecha_prestamo().toString());
            statement.setString(2,loanInfo.getFecha_devolucion().toString());
            statement.setString(3,loanInfo.getPrecio().toString());
            statement.setInt(4,loanInfo.getPeliculas_id());
            statement.setInt(5,loanInfo.getPeliculas_clientes_id_cliente());
            statement.setInt(6,loanId);

            statement.execute();

            // Vuelve a habilitar las restricciones de clave foránea
            PreparedStatement enableFK = con.prepareStatement("SET FOREIGN_KEY_CHECKS=1;");
            enableFK.execute();

            JOptionPane.showMessageDialog(null, "Prestamo actualizado");

        }catch (Exception e){
            System.out.println("Error al actualizar"+e);
        }
    }

    public static void DeleteLoan(Integer loanId){
        try(Connection con = SingletonConnection.GetDBConnection())
        {
            // Deshabilita las restricciones de clave foránea
            PreparedStatement disableFK = con.prepareStatement("SET FOREIGN_KEY_CHECKS=0;");
            disableFK.execute();

            // Elimina el prestamo
            PreparedStatement deleteStatement = con.prepareStatement("DELETE FROM prestamos WHERE id = ?");
            deleteStatement.setInt(1, loanId);
            deleteStatement.execute();

            // Vuelve a habilitar las restricciones de clave foránea
            PreparedStatement enableFK = con.prepareStatement("SET FOREIGN_KEY_CHECKS=1;");
            enableFK.execute();

            JOptionPane.showMessageDialog(null, "Prestamo eliminado");

        }catch (Exception e){
            System.out.println("Error al eliminar el prestamo"+e);
        }
    }
}
