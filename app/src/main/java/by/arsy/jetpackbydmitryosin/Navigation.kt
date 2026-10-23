package by.arsy.jetpackbydmitryosin

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import by.arsy.jetpackbydmitryosin.MainActivity.Companion.HOME_DESTINATION
import by.arsy.jetpackbydmitryosin.MainActivity.Companion.PROFILE_DESTINATION
import by.arsy.jetpackbydmitryosin.MainActivity.Companion.PROFILE_ID

@Composable
fun Navigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = HOME_DESTINATION
    ) {
        composable(route = HOME_DESTINATION) {
            AppWrapper(modifier = modifier) {
                Buttons(
                    onClick = { navController.navigate(route = "$PROFILE_DESTINATION/$it") }
                )
            }
        }

        composable(
            route = "$PROFILE_DESTINATION/{$PROFILE_ID}",
            arguments = listOf(navArgument(name = PROFILE_ID) { type = NavType.IntType })
        ) { backStackEntry ->
            backStackEntry.arguments?.getInt(/* key = */ PROFILE_ID)?.let {
                AppWrapper(modifier = modifier) {
                    Profile(id = it)
                }
            }
        }
    }

}