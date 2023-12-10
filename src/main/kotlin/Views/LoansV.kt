package Views

import Controller.DAOLoans
import Header
import MiniHeader
import Model.DTOLoans
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import ViewButton
import ViewDropDownField
import ViewTable
import ViewTextField
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.swing.JOptionPane

class LoansV : Screen {

    @Composable
    override fun Content() {

        var Loans by remember { mutableStateOf(DAOLoans.ListLoans()) }
        var fechaPrestamo by remember { mutableStateOf("") }
        var fechaDevolucion by remember { mutableStateOf("") }
        var precio by remember { mutableStateOf("") }
        var peliculasId by remember { mutableStateOf("") }
        var idCliente by remember { mutableStateOf("") }

        MaterialTheme {
            Column {
                // Cabecera
                Header()
                MiniHeader("Loans")
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    // Campos de la primera fila
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        fechaPrestamo = ViewTextField("fecha prestamo", "Ingrese fecha prestamo", {})
                        fechaDevolucion = ViewTextField("fecha limite", "Ingrese fecha limite", {})
                        precio = ViewTextField("precio", "Ingrese precio", {})
                        peliculasId = ViewTextField("peliculas id", "Ingrese pelicula id", {})
                        idCliente = ViewTextField("id cliente", "Ingrese id cliente", {})
                    }
                    // Botones
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(5.dp)
                        ) {
                            ViewButton("Agregar", {
                                var loan = DTOLoans()
                                try {
                                    val fechaP = LocalDate.parse(fechaPrestamo)
                                    val fechaD = LocalDate.parse(fechaDevolucion)
                                    loan.fecha_prestamo = fechaP
                                    loan.fecha_devolucion = fechaD
                                    loan.precio = precio.toDouble()
                                    loan.peliculas_id = peliculasId.toInt()
                                    loan.peliculas_clientes_id_cliente = idCliente.toInt()
                                    DAOLoans.Insertloans(loan)
                                }catch(e:Exception){
                                    JOptionPane.showMessageDialog(null, "Campo invalido"+e)
                                }

                            })
                            ViewButton("Actualizar", {
                                Loans = DAOLoans.ListLoans()
                            })
                        }
                    }
                    // Tabla
                    ViewTable(
                        Loans
                    )
                }

            }
        }
    }
}


