package Views

import Header
import MiniHeader
import ViewButton
import ViewTextField
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import lBlue

class ManagerV () : Screen {

    @Composable
    override fun Content() {
        var nombre by remember { mutableStateOf("") }
        var correo by remember { mutableStateOf("") }
        var telefono by remember { mutableStateOf("") }
        var direccion by remember { mutableStateOf("") }
        var clave by remember { mutableStateOf("") }
        var confirmarClave by remember { mutableStateOf("") }
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
                        modifier = Modifier.fillMaxWidth().weight(0.3f),
                        horizontalArrangement = Arrangement.SpaceBetween
                    )
                    {
                            MiniHeader("Regristrar")

                    }
                    Column(
                        modifier = Modifier.fillMaxSize().weight(0.7f)
                            .verticalScroll(rememberScrollState())
                    )
                    {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth(0.5f).padding(10.dp)
                        ){
                            Text(
                                text = "Nombre",
                                fontWeight = FontWeight.SemiBold,
                                color = lBlue,
                                fontSize = 25.sp
                            )
                            OutlinedTextField(
                            value = "",
                            onValueChange = { nombre = it },
                            label = { Text(text = "Ingrese su nombre", fontSize = 12.sp) },
                            colors = TextFieldDefaults.textFieldColors(
                                backgroundColor = Color.White,
                                focusedIndicatorColor = Color.DarkGray,
                                unfocusedIndicatorColor = Color.Gray
                            ),
                            shape = RoundedCornerShape(8.dp)
                        )
                        }
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth(0.5f).padding(10.dp)
                        ){
                            Text(
                                text = "Correo",
                                fontWeight = FontWeight.SemiBold,
                                color = lBlue,
                                fontSize = 25.sp
                            )
                            OutlinedTextField(
                                value = "",
                                onValueChange = { correo = it },
                                label = { Text(text = "Ingrese su correo", fontSize = 12.sp) },
                                colors = TextFieldDefaults.textFieldColors(
                                    backgroundColor = Color.White,
                                    focusedIndicatorColor = Color.DarkGray,
                                    unfocusedIndicatorColor = Color.Gray
                                ),
                                shape = RoundedCornerShape(8.dp)
                            )
                        }
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth(0.5f).padding(10.dp)
                        ){
                            Text(
                                text = "Telefono",
                                fontWeight = FontWeight.SemiBold,
                                color = lBlue,
                                fontSize = 25.sp
                            )
                            OutlinedTextField(
                                value = "",
                                onValueChange = { telefono = it },
                                label = { Text(text = "Ingrese su telefono", fontSize = 12.sp) },
                                colors = TextFieldDefaults.textFieldColors(
                                    backgroundColor = Color.White,
                                    focusedIndicatorColor = Color.DarkGray,
                                    unfocusedIndicatorColor = Color.Gray
                                ),
                                shape = RoundedCornerShape(8.dp)
                            )
                        }
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth(0.5f).padding(10.dp)
                        ){
                            Text(
                                text = "Direccion",
                                fontWeight = FontWeight.SemiBold,
                                color = lBlue,
                                fontSize = 25.sp
                            )
                            OutlinedTextField(
                                value = "",
                                onValueChange = { direccion = it },
                                label = { Text(text = "Ingrese su direccion", fontSize = 12.sp) },
                                colors = TextFieldDefaults.textFieldColors(
                                    backgroundColor = Color.White,
                                    focusedIndicatorColor = Color.DarkGray,
                                    unfocusedIndicatorColor = Color.Gray
                                ),
                                shape = RoundedCornerShape(8.dp)
                            )
                        }
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth(0.5f).padding(10.dp)
                        ){
                            Text(
                                text = "Clave",
                                fontWeight = FontWeight.SemiBold,
                                color = lBlue,
                                fontSize = 25.sp
                            )
                            OutlinedTextField(
                                value = "",
                                onValueChange = { clave = it },
                                label = { Text(text = "Cree su clave", fontSize = 12.sp) },
                                colors = TextFieldDefaults.textFieldColors(
                                    backgroundColor = Color.White,
                                    focusedIndicatorColor = Color.DarkGray,
                                    unfocusedIndicatorColor = Color.Gray
                                ),
                                shape = RoundedCornerShape(8.dp)
                            )
                        }
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth(0.5f).padding(10.dp)
                        ){
                            Text(
                                text = "Confirme clave",
                                fontWeight = FontWeight.SemiBold,
                                color = lBlue,
                                fontSize = 25.sp
                            )
                            OutlinedTextField(
                                value = "",
                                onValueChange = { confirmarClave = it },
                                label = { Text(text = "Confirme clave", fontSize = 12.sp) },
                                colors = TextFieldDefaults.textFieldColors(
                                    backgroundColor = Color.White,
                                    focusedIndicatorColor = Color.DarkGray,
                                    unfocusedIndicatorColor = Color.Gray
                                ),
                                shape = RoundedCornerShape(8.dp)
                            )
                        }
                        Row(
                            modifier = Modifier.padding(start = 15.dp)
                        ) {
                            ViewButton("Crear cuenta",{})
                        }
                    }
                }
            }
        }
    }
}
