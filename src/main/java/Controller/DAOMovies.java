package Controller;

import Model.DTOCategorias;
import Model.DTOMovies;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOMovies {
    public static List<DTOMovies> ListMovies() throws SQLException {
        List<DTOMovies> movies = new ArrayList<DTOMovies>();

        PreparedStatement query = SingletonConnection.GetDBConnection().prepareStatement("" +
                "SELECT * FROM peliculas");
        

        ResultSet moviesDB = query.executeQuery();

        while (moviesDB.next())
        {
            DTOMovies movie = new DTOMovies();
            DTOCategorias categoria = new DTOCategorias();

            ArrayList<DTOCategorias> movieCategory =
             (ArrayList<DTOCategorias>) DAOCategorias.GetCategoriaById(moviesDB.getInt("categorias_id"));

            movieCategory.forEach(category -> {
                categoria.setId(category.getId());
                categoria.setNombre(category.getNombre());
                categoria.setDescripcion(category.getDescripcion());
            });

            movie.setId(moviesDB.getInt("id"));
            movie.setIsbn(moviesDB.getString("ISBN"));
            movie.setNombre(moviesDB.getString("nombre"));
            movie.setDescripcion(moviesDB.getString("descripcion"));
            movie.setUnidadesDisponibles(moviesDB.getInt("unidades_disponibles"));
            movie.setCategoria(categoria);

            movies.add(movie);
        }

        return movies;
    }
}
