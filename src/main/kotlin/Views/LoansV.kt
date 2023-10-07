package Views

import Header
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import navController.SharedScreen

class LoansV (): Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val homeScreen = rememberScreen(SharedScreen.HomeV)

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
                    Column(
<<<<<<< HEAD
                        modifier = Modifier.fillMaxSize()
                    ) {
                            Button(onClick = {
=======
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "loans SIUUU")
                        Button(onClick = {
>>>>>>> ft_logic_loans
                            navigator.push(homeScreen)
                        }){
                            Text("Volver")
                        }
<<<<<<< HEAD
                        //aqui wey empieza aqui

=======
>>>>>>> ft_logic_loans
                    }
                }
            }
        }
    }
}