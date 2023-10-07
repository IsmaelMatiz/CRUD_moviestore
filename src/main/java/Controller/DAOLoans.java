package Controller;

import Model.DTOLoans;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DAOLoans {
    public List<DTOLoans> ListLoans() throws SQLException {
        ArrayList<DTOLoans> loans = new ArrayList<DTOLoans>();

        PreparedStatement query = SingletonConnection.GetDBConnection().prepareStatement("" +
                "SELECT * FROM prestamos");

        ResultSet moviesDB = query.executeQuery();

        while (moviesDB.next())
        {
            DTOLoans loan = new DTOLoans();

            loan.setId(moviesDB.getInt("id"));
            loan.setId_cliente(moviesDB.getInt("id_cliente"));
            loan.setFecha_prestamo(moviesDB.getDate("fecha_prestamo").toLocalDate());
            loan.setFecha_devolucion(moviesDB.getDate("fecha_devolucion").toLocalDate());
            loan.setPrecio(moviesDB.getDouble("precio"));
            loan.setPeliculas_id(moviesDB.getInt("peliculas_id"));
            loan.setPeliculas_clientes_id_cliente(moviesDB.getInt("peliculas_clientes_id_cliente"));

            loans.add(loan);
        }

        return loans;
    }
}
