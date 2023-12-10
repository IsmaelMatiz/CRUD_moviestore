package Views

import Controller.DAOAdministradores
import Header
import Header1
import MiniHeader
import ViewButton
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import navController.SharedScreen
import javax.swing.JOptionPane


class LoginV(): Screen {

    @Composable
    override fun Content() {
        var userName by remember { mutableStateOf("") }
        var passWord by remember { mutableStateOf("") }

        val navigator = LocalNavigator.currentOrThrow
        val homeScreen = rememberScreen(SharedScreen.HomeV)

        // Creating a variable to store toggle state
        var passwordVisible by remember { mutableStateOf(false) }

        MaterialTheme {
            Column {
                Column(
                    modifier = Modifier.weight(0.2f)
                ) {
                    Header1()
                }
                Column(
                    modifier = Modifier.weight(0.8f)
                ) {
                    //Mini cabecera con titulo y boton de volver
                    Row(
                        verticalAlignment = Alignment.CenterVertically,

                        ) {
                        Row (
                            modifier = Modifier.fillMaxWidth().padding(20.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Inicio de Sesion",
                                fontSize = 80.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.Default
                            )
                        }
                    }
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    )
                    {
                        OutlinedTextField(
                            value = userName,
                            onValueChange = { userName = it },
                            label = { Text(text = "Ingrese su usuario", fontSize = 12.sp) },
                            colors = TextFieldDefaults.textFieldColors(
                                backgroundColor = Color.White,
                                focusedIndicatorColor = Color.DarkGray,
                                unfocusedIndicatorColor = Color.Gray
                            ),
                            shape = RoundedCornerShape(8.dp)
                        )

                        OutlinedTextField(
                            value = passWord,
                            onValueChange = { passWord = it },
                            label = { Text("Password") },
                            singleLine = true,
                            placeholder = { Text("Password") },
                            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            trailingIcon = {
                                val image = if (passwordVisible)
                                    Icons.Default.Edit
                                else Icons.Default.Edit

                                // Localized description for accessibility services
                                val description = if (passwordVisible) "Hide password" else "Show password"

                                // Toggle button to hide or display password
                                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                    Icon(imageVector = image, description)
                                }
                            },
                            colors = TextFieldDefaults.textFieldColors(
                                backgroundColor = Color.White,
                                focusedIndicatorColor = Color.DarkGray,
                                unfocusedIndicatorColor = Color.Gray
                            )
                        )

                        ViewButton("Ingresar",{
                            var validationRespone = DAOAdministradores.ValidateCredentials(userName,passWord)

                            if (validationRespone == 2){
                                JOptionPane.showMessageDialog(null, "Usuario Incorrecto");
                            }else if (validationRespone == 1){
                                JOptionPane.showMessageDialog(null, "Usuario Correcto, clave incorrecta");
                            }else{
                                navigator.push(homeScreen)
                            }
                        })
                    }
                }
            }
        }
    }
}