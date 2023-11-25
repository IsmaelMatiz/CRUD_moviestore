package Views


import Header
import MiniHeader
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen


class ClientV (): Screen {

    @Composable
    override fun Content() {
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
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        MiniHeader("Clientes")

                    }

                }

            }

        }

    }

}