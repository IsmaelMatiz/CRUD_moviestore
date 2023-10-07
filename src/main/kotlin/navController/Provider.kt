package navController

import cafe.adriel.voyager.core.registry.ScreenProvider

sealed class SharedScreen : ScreenProvider {
    object HomeV : SharedScreen()
    object MoviesV : SharedScreen()
    object LoansV : SharedScreen()
}