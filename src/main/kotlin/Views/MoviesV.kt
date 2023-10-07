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
import menuButton
import navController.SharedScreen

class MoviesV(): Screen {

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
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "MOVIEESSS SIUUU")
                        Button(onClick = {
                            navigator.push(homeScreen)
                        }){
                            Text("Volver")
                        }
                    }
                }
            }
        }
    }
}