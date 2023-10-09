package Controller;

import Model.DTOCategorias;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOCategorias {

    public static List<DTOCategorias> ListAllCategories() throws SQLException {
        List<DTOCategorias> categorias = new ArrayList<DTOCategorias>();

        PreparedStatement query = SingletonConnection.GetDBConnection()
                .prepareStatement("SELECT * FROM categorias");


        ResultSet categoriasDB = query.executeQuery();

        while (categoriasDB.next())
        {
            DTOCategorias categoria = new DTOCategorias();


            categoria.setId(categoriasDB.getInt("id"));
            categoria.setNombre(categoriasDB.getString("nombre"));
            categoria.setDescripcion(categoriasDB.getString("descripcion"));

            categorias.add(categoria);
        }

        return categorias;
    }

    public static List<DTOCategorias> GetCategoriaById(Integer categoriaId) throws SQLException {
        List<DTOCategorias> categorias = new ArrayList<DTOCategorias>();

        PreparedStatement query = SingletonConnection.GetDBConnection()
                .prepareStatement("SELECT * FROM categorias WHERE id = ?");
        query.setInt(1, categoriaId);

        ResultSet categoriasDB = query.executeQuery();

        while (categoriasDB.next())
        {
            DTOCategorias categoria = new DTOCategorias();
            categoria.setId(categoriasDB.getInt("id"));
            categoria.setNombre(categoriasDB.getString("nombre"));
            categoria.setDescripcion(categoriasDB.getString("descripcion"));

            categorias.add(categoria);
        }

        return categorias;
    }
}
