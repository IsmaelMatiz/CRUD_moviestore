import Views.HomeV
import Views.LoansV
import Views.MoviesV
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import cafe.adriel.voyager.core.registry.ScreenRegistry
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
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
        ScreenRegistry{
            register<SharedScreen.HomeV> {
                HomeV()
            }
            register<SharedScreen.MoviesV> {
                MoviesV()
            }
            register<SharedScreen.LoansV> {
                LoansV()
            }
        }
        Navigator(HomeV()) { navigator ->
            SlideTransition(navigator)
        }
    }
}
