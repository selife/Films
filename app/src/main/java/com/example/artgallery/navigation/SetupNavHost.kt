package com.example.artgallery.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.artgallery.MainViewModel
import com.example.artgallery.screens.DetailsScreen
import com.example.artgallery.screens.MainScreen
import com.example.artgallery.utils.Constants

sealed class Screens(val route : String) {
    object Main : Screens(route = Constants.Screens.MAIN_SCREEN)
    object Details : Screens(route = Constants.Screens.DETAILS_SCREEN)
}


@Composable
fun SetupNavHost(navController: NavHostController, viewModel: MainViewModel) {
    NavHost(
        navController = navController,
        startDestination = Screens.Main.route
    ) {
        composable(route = Screens.Main.route ) {
            MainScreen(navController = navController, viewModel = viewModel)
        }
        composable(route = Screens.Details.route + "/{Id}") { backStackEntry ->
            DetailsScreen(viewModel = viewModel, itemId = backStackEntry.arguments?.getString("Id")?: "1")
        }
    }

}