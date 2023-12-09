package Views

import Header
import MiniHeader
import ViewTextField
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen

class ManagerV () : Screen {

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
                        MiniHeader("Manager")
                    }
                    //campos
                    Row(
                        modifier = Modifier.fillMaxWidth().weight(0.2f),
                        horizontalArrangement = Arrangement.SpaceBetween
                    )
                    <EditText
                    android:id="@+id/etID"
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:layout_marginTop="16dp"
                    android:hint="ID"/>

                    <EditText
                    android:id="@+id/etNombres"
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:layout_below="@id/etUsername"
                    android:layout_marginTop="16dp"
                    android:hint="Nombres"/>

                    <EditText
                    android:id="@+id/etCorreo"
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:layout_below="@id/etUsername"
                    android:layout_marginTop="16dp"
                    android:hint="Correo"/>
                    }
                }
            }
        }
    }

}
