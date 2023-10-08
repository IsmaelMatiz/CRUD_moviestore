package Controller;

import Model.DTOMovies;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DAOMovies {
    public List<DTOMovies> ListMovies() throws SQLException {
        List<DTOMovies> movies = new ArrayList<DTOMovies>();

        PreparedStatement query = SingletonConnection.GetDBConnection().prepareStatement("" +
                "SELECT * FROM peliculas");
        

        ResultSet moviesDB = query.executeQuery();

        while (moviesDB.next())
        {
            DTOMovies movie = new DTOMovies();


            movie.setId(moviesDB.getInt("id"));
            movie.setIsbn(moviesDB.getString("ISBN"));
            movie.setNombre(moviesDB.getString("nombre"));
            movie.setDescripcion(moviesDB.getString("descripcion"));
            movie.setUnidadesDisponibles(moviesDB.getInt("unidades_disponibles"));

            movies.add(movie);
        }

        return movies;
    }
}
