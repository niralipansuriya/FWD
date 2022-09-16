package com.swipefwd.ui.auth

sealed class LogInNavigationModel {
    object Profile : LogInNavigationModel()
    object Preferences : LogInNavigationModel()
    object Dashboard : LogInNavigationModel()
    object Agreement : LogInNavigationModel()
}
