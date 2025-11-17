package com.example.medinotify.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.medinotify.ui.screens.auth.login.LoginRoute
import com.example.medinotify.ui.screens.auth.password.ForgotPasswordRoute
import com.example.medinotify.ui.screens.auth.password.ResetPasswordRoute
import com.example.medinotify.ui.screens.auth.password.ResetPasswordSuccessScreen
import com.example.medinotify.ui.screens.auth.password.VerifyCodeRoute
import com.example.medinotify.ui.screens.auth.register.RegisterRoute
import com.example.medinotify.ui.screens.auth.splash.SplashScreen

@Composable
fun MedinotifyApp(modifier: Modifier = Modifier, navController: NavHostController = rememberNavController()) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = NavDestination.startDestination
    ) {
        composable(NavDestination.Splash.route) {
            SplashScreen(
                onLogin = {
                    navController.navigate(NavDestination.Login.route) {
                        popUpTo(NavDestination.Splash.route) { inclusive = true }
                    }
                },
                onRegister = { navController.navigate(NavDestination.Register.route) }
            )
        }

        composable(NavDestination.Login.route) {
            LoginRoute(
                onRegister = { navController.navigate(NavDestination.Register.route) },
                onContinue = { /* TODO: Hook up real home flow */ },
                onBack = {
                    navController.navigate(NavDestination.Splash.route) {
                        popUpTo(NavDestination.Login.route) { inclusive = true }
                    }
                },
                onForgotPassword = {
                    navController.navigate(NavDestination.ForgotPassword.route)
                }
            )
        }

        composable(NavDestination.ForgotPassword.route) {
            ForgotPasswordRoute(
                onBack = { navController.popBackStack() },
                onSendCode = {
                    navController.navigate(NavDestination.VerifyCode.route)
                }
            )
        }

        composable(NavDestination.VerifyCode.route) {
            VerifyCodeRoute(
                onBack = { navController.popBackStack() },
                onConfirm = { navController.navigate(NavDestination.ResetPassword.route) }
            )
        }

        composable(NavDestination.ResetPassword.route) {
            ResetPasswordRoute(
                onBack = { navController.popBackStack() },
                onReset = {
                    navController.navigate(NavDestination.ResetPasswordSuccess.route) {
                        popUpTo(NavDestination.ResetPassword.route) { inclusive = true }
                    }
                }
            )
        }

        composable(NavDestination.ResetPasswordSuccess.route) {
            ResetPasswordSuccessScreen(
                onBackToLogin = {
                    navController.navigate(NavDestination.Login.route) {
                        popUpTo(NavDestination.Splash.route) { inclusive = true }
                    }
                }
            )
        }

        composable(NavDestination.Register.route) {
            RegisterRoute(
                onBack = { navController.popBackStack() },
                onRegisterSuccess = {
                    navController.popBackStack(NavDestination.Login.route, inclusive = false)
                },
                onLogin = { navController.popBackStack() }
            )
        }
    }
}

