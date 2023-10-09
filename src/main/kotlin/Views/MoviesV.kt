package Views

import Controller.DAOCategorias
import Controller.DAOMovies
import Header
import MiniHeader
import ViewButton
import ViewDropDownField
import ViewTable
import ViewTextField
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp


class MoviesV(): Screen {

    @Composable
    override fun Content() {

        var testText by remember { mutableStateOf("algo") }
        var categorias = DAOCategorias.ListAllCategories()
        var movies = DAOMovies.ListMovies()

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
                        modifier = Modifier.fillMaxWidth().weight(0.18f),
                        horizontalArrangement = Arrangement.SpaceBetween
                    )
                    {
                        ViewTextField("ISBN","Enter ISBN value",{})
                        ViewTextField("Name","Enter Name value",{})
                        ViewTextField("Description","Enter description value",{})
                        ViewTextField("Units","Enter Units value",{})
                        testText = ViewDropDownField("Categorias",categorias)
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
                            ViewButton("Agregar",{})
                            ViewButton("Actualizar",{})
                        }
                    }
                    //tabla
                    Row(
                        modifier = Modifier.weight(0.5f)
                    )
                    {
                        ViewTable(movies)
                    }
                }
            }
        }
    }
}
