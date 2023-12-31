import Views.ClientV
import Views.HomeV
import Views.LoansV
import Views.ManagerV
import Views.LoginV
import Views.MoviesV
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import cafe.adriel.voyager.core.registry.ScreenRegistry
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import navController.SharedScreen


@OptIn(ExperimentalAnimationApi::class)
fun main() = application {
    val windowState = rememberWindowState(width = 1100.dp, height = 700.dp, position = WindowPosition(Alignment.Center))
    Window(
        onCloseRequest = ::exitApplication,
        state = windowState,
        title = storeName,
        icon = painterResource(logoPaht)
    ) {
        ScreenRegistry {
            register<SharedScreen.HomeV> {
                HomeV()
            }
            register<SharedScreen.MoviesV> {
                MoviesV()
            }
            register<SharedScreen.LoansV> {
                LoansV()
            }
            register<SharedScreen.ManagerV> {
                ManagerV()
            }
            register<SharedScreen.LoginV> {
                LoginV()
            }

        }
        Navigator(LoginV()) { navigator ->
            SlideTransition(navigator)
        }
    }
}
