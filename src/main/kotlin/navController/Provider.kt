package navController

import cafe.adriel.voyager.core.registry.ScreenProvider

sealed class SharedScreen : ScreenProvider {
    object LoginV : SharedScreen()
    object HomeV : SharedScreen()
    object MoviesV : SharedScreen()
    object LoansV : SharedScreen()
}