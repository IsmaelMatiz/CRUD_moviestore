package Controller;


import Model.DTOCategorias;
import Model.DTOMovies;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DAOMovies {
    public static List<DTOMovies> ListMovies() {
        List<DTOMovies> movies = new ArrayList<DTOMovies>();
        ResultSet moviesDB;
        try(Connection con = SingletonConnection.GetDBConnection()){
            PreparedStatement query = con.prepareStatement("" +
                    "SELECT * FROM peliculas");

            moviesDB = query.executeQuery();

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
        }catch (Exception e){
            System.out.println("Problema al acceder al ejecutar el query intentalo denuevo");
        }

        return movies;
    }

    public static void InsertMovie(DTOMovies movieInfo){
        try(Connection con = SingletonConnection.GetDBConnection()){
            PreparedStatement statement = con.prepareStatement(
                "INSERT INTO peliculas(ISBN,nombre,descripcion,unidades_disponibles,categorias_id) " +
                        "VALUES(?,?,?,?,?)"
            );

            statement.setString(1,movieInfo.getIsbn());
            statement.setString(2,movieInfo.getNombre());
            statement.setString(3,movieInfo.getDescripcion());
            statement.setInt(4,movieInfo.getUnidadesDisponibles());
            statement.setInt(5,movieInfo.getCategoria().getId());

            statement.execute();

            JOptionPane.showMessageDialog(null, "Pelicula insertada");

        }catch (Exception e){
            System.out.println("Problema al insertar pelicula error "+e);
        }
    }

    public static void UpdateMovie(Integer movieId, DTOMovies movieInfo){
        try(Connection con = SingletonConnection.GetDBConnection()){
            PreparedStatement statement = con.prepareStatement(
                    "UPDATE peliculas " +
                            "SET ISBN = ?, " +
                            "nombre = ?, " +
                            "descripcion = ?, " +
                            "unidades_disponibles = ?, " +
                            "categorias_id = ? " +
                            "WHERE id = ?"
            );

            statement.setString(1,movieInfo.getIsbn());
            statement.setString(2,movieInfo.getNombre());
            statement.setString(3,movieInfo.getDescripcion());
            statement.setInt(4,movieInfo.getUnidadesDisponibles());
            statement.setInt(5,movieInfo.getCategoria().getId());
            statement.setInt(6,movieId);

            statement.execute();

            JOptionPane.showMessageDialog(null, "Pelicula Actualizada");

        }catch (Exception e){
            System.out.println("Error al Actualizar pelicula intentalo de nuevo "+e);
        }
    }

    public static void DeleteMovie(Integer movieId){
        try(Connection con = SingletonConnection.GetDBConnection())
        {
            // Deshabilita las restricciones de clave foránea
            PreparedStatement disableFK = con.prepareStatement("SET FOREIGN_KEY_CHECKS=0;");
            disableFK.execute();

            // Elimina la película
            PreparedStatement deleteStatement = con.prepareStatement("DELETE FROM peliculas WHERE id = ?");
            deleteStatement.setInt(1, movieId);
            deleteStatement.execute();

            // Vuelve a habilitar las restricciones de clave foránea
            PreparedStatement enableFK = con.prepareStatement("SET FOREIGN_KEY_CHECKS=1;");
            enableFK.execute();

            JOptionPane.showMessageDialog(null, "Pelicula Eliminada");

        }catch (Exception e){
            System.out.println("Error al eliminar pelicula error "+e);
        }
    }
}
