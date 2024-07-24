package com.athena.facebooklike

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import com.athena.facebooklike.data.Shortcut
import com.athena.facebooklike.data.getRandomItems
import com.athena.facebooklike.navigation.Destination
import com.athena.facebooklike.ui.theme.FacebookLikeTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FacebookLikeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    FacebookLikeScaffold(navController = navController)
                }
            }
        }
    }
}

@Composable
fun FacebookLikeScaffold(navController: NavHostController) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val onDrawerIconClick: () -> Unit = {
        scope.launch {
            drawerState.apply {
                if (isClosed) open() else close()
            }
        }
    }
    val ctx = LocalContext.current
    val randomItems = remember { mutableStateOf(getRandomItems(10)) }
    val shortcut = remember { mutableStateOf(Shortcut.getShortcuts()) }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = { NavigationDrawer(randomItems.value, shortcut.value) },
    ) {
        Scaffold(
            bottomBar = { BottomNav(navController, onDrawerIconClick) }
        ) { contentPadding ->
            val stdModifier =
                Modifier
                    .padding(bottom = contentPadding.calculateBottomPadding())
                    .background(
                        Color(0xFFcccccc)
                    )
            NavHost(
                navController = navController,
                startDestination = Destination.Home.route
            ) {
                composable(Destination.Home.route) {
                    HomeScreen(
                        navController = navController,
                        modifier = stdModifier
                    )
                }
                composable(Destination.Notifications.route) {
                    NotificationsScreen(modifier = stdModifier)
                }
                composable(
                    Destination.Detail.route,
                    deepLinks = listOf(navDeepLink {
                        uriPattern = "https://www.facebooklike.com/{itemId}"
                    })
                ) {
                    val itemId = it.arguments?.getString("itemid")
                    if (itemId != null) {
                        ItemDetailsScreen(itemId = itemId.toInt(), modifier = stdModifier)
                    } else {
                        Toast.makeText(ctx, "Id is required", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FacebookLikeTheme {
        FacebookLikeScaffold(rememberNavController())
    }
}