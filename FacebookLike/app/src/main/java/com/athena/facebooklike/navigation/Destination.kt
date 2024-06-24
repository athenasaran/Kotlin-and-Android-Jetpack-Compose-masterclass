package com.athena.facebooklike.navigation

sealed class Destination(val route: String) {
    object Home: Destination("home")
    object Notifications: Destination("notifications")
    object NavigationDrawer: Destination("navigation_drawer")
    object Detail: Destination("detail/{itemid}") {
        fun createRoute(itemId: Int) = "detail/$itemId"
    }
}