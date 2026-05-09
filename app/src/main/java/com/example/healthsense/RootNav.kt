package com.example.healthsense

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.healthsense.auth.SignupScreen
import com.example.healthsense.getstart.GetStartScreen
import com.example.healthsense.userDetails.UserDetailsScreen


@Composable
fun RootNav(innerPadding: PaddingValues) {
    val rootNavController = rememberNavController()
    NavHost(
        navController = rootNavController,
        startDestination = Screens.GetStartedScreen.route // ✅ match the composable route
    ) {
        composable(Screens.GetStartedScreen.route) {
            GetStartScreen(
                rootNavController = rootNavController,
                onFinished = {
                    rootNavController.navigate(Screens.UserDetails.route) // ✅ use the same route constant
                }
            )
        }

        composable(Screens.Signup.route) {
            SignupScreen(rootNavController = rootNavController)
        }

        composable(Screens.UserDetails.route){
            UserDetailsScreen(
                onFinished = {
                    rootNavController.navigate(Screens.Signup.route) // ✅ use the same route constant
                },
                rootNavController = rootNavController)
        }
    }


    }
