package com.athena.facebooklike

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.athena.facebooklike.navigation.Destination

@Composable
fun BottomNav(navController: NavHostController, onDrawerIconClick: () -> Unit) {
    NavigationBar {
        val navBackStackEntry = navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry.value?.destination

        NavigationBarItem(
            selected = currentDestination?.route == Destination.Home.route,
            onClick = { navController.navigate(Destination.Home.route) },
            icon = { Icon(Icons.Default.Home, contentDescription = null) },
            label = { Text(Destination.Home.route) }
        )

        NavigationBarItem(
            selected = currentDestination?.route == Destination.Notifications.route,
            onClick = { navController.navigate(Destination.Notifications.route) },
            icon = { Icon(Icons.Default.Notifications, contentDescription = null) },
            label = { Text(Destination.Notifications.route) }
        )

        NavigationBarItem(
            selected = false,
            onClick = onDrawerIconClick,
            icon = { Icon(Icons.Default.Menu, contentDescription = null) },
            label = { Text("Menu") }
        )
    }
}