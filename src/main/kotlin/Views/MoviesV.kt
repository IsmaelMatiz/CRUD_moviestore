package Views

import AlertView
import Controller.DAOCategorias
import Controller.DAOMovies
import Header
import MiniHeader
import Model.DTOCategorias
import Model.DTOMovies
import ViewButton
import ViewDropDownField
import ViewTable
import ViewTextField
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp


class MoviesV(): Screen {

    @Composable
    override fun Content() {

        var categorias = DAOCategorias.ListAllCategories()
        var movies by remember { mutableStateOf(DAOMovies.ListMovies()) }
        val movieToInsertInfo = remember { mutableStateMapOf(
            "ISBN" to "",
            "nombre" to "",
            "descripcion" to "",
            "unidadesDisponibles" to 0
        ) }
        val categoryInfo = remember { mutableStateOf<DTOCategorias>(DTOCategorias()) }

        MaterialTheme {
            Column {
                Column(
                    modifier = Modifier.weight(0.2f)
                )
                {
                    Header()
                }
                Column(
                    modifier = Modifier.weight(0.8f)
                )
                {
                    //Mini cabecera con titulo y boton de volver
                    Row(
                        modifier = Modifier.weight(0.22f),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        MiniHeader("Movies")
                    }
                    //campos
                    Row(
                        modifier = Modifier.fillMaxWidth().weight(0.2f),
                        horizontalArrangement = Arrangement.SpaceBetween
                    )
                    {
                        movieToInsertInfo["ISBN"] = ViewTextField("ISBN","Enter ISBN value",{})
                        movieToInsertInfo["nombre"] = ViewTextField("Name","Enter Name value",{})
                        movieToInsertInfo["descripcion"] = ViewTextField("Description","Enter description value",{})
                        movieToInsertInfo["unidadesDisponibles"] = ViewTextField("Units","Enter Units value",{}).toIntOrNull()?:0
                        categoryInfo.value = ViewDropDownField("Categorias",categorias,0)
                    }
                    // Botones
                    Row(
                        modifier = Modifier.fillMaxWidth().weight(0.1f).padding(end = 10.dp),
                        horizontalArrangement = Arrangement.End
                    )
                    {
                        Row (
                            horizontalArrangement = Arrangement.spacedBy(5.dp)
                        ){
                            ViewButton("Agregar",{
                                var movieToInsert = DTOMovies()
                                movieToInsert.isbn = movieToInsertInfo["ISBN"].toString()
                                movieToInsert.nombre = movieToInsertInfo["nombre"].toString()
                                movieToInsert.descripcion = movieToInsertInfo["descripcion"].toString()
                                movieToInsert.unidadesDisponibles = movieToInsertInfo["unidadesDisponibles"].toString().toInt()
                                movieToInsert.categoria = categoryInfo.value

                                DAOMovies.InsertMovie(movieToInsert)
                            })
                            ViewButton("Actualizar",{
                                movies = DAOMovies.ListMovies()
                            })
                        }
                    }
                    //tabla
                    Row(
                        modifier = Modifier.weight(0.48f)
                    )
                    {
                        ViewTable(
                            movies,
                            categorias)
                    }
                }
            }
        }
    }
}
