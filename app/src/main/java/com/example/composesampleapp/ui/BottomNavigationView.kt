package com.example.composesampleapp.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.composesampleapp.BookScreen
import com.example.composesampleapp.NewsScreen
import com.example.composesampleapp.R

@Composable
fun NavigationHost(
    navController: NavHostController,
    scaffoldState: ScaffoldState
) {
    NavHost(
        navController = navController,
        startDestination = BottomNavigationItems.Books.route
    ) {
        composable(BottomNavigationItems.Books.route) {
            BookScreen(scaffoldState = scaffoldState)
        }
        composable(BottomNavigationItems.News.route) {
            NewsScreen()
        }
    }
}

@Composable
fun BottomNavigationBar(
    navController: NavController,
    items: List<BottomNavigationItems> = navItems,
) {
    BottomNavigation(backgroundColor = Color.White) {

        val navBackStackEntry = navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry.value?.destination

        items.forEach { navigationItem ->
            BottomNavigationItem(
                label = { Text(navigationItem.name) },
                alwaysShowLabel = true,
                selectedContentColor = Color.Red,
                unselectedContentColor = Color.Gray,
                selected = currentRoute?.hierarchy?.any {
                    navigationItem.route == it.route
                } == true,
                icon = {
                    Icon(
                        painterResource(id = navigationItem.icon),
                        contentDescription = navigationItem.name,
                    )
                },
                onClick = {
                    navController.navigate(navigationItem.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Composable
fun BottomNavigationView() {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) },
        scaffoldState = scaffoldState,
        snackbarHost = { scaffoldState.snackbarHostState }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            NavigationHost(navController, scaffoldState)
            SnackBar(
                snackbarHostState = scaffoldState.snackbarHostState,
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }
    }
}

@Composable
fun SnackBar(
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
) {
    SnackbarHost(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(Alignment.Bottom),
        hostState = snackbarHostState,
        snackbar = { snackbarData: SnackbarData ->
            Card(
                backgroundColor = colorResource(id = R.color.purple_200),
                shape = RoundedCornerShape(dimensionResource(id = R.dimen.dimen_small)),
                border = BorderStroke(1.dp, Color.Black),
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.dimen_large))
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .padding(dimensionResource(id = R.dimen.dimen_small))
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Icon(imageVector = Icons.Default.Notifications, contentDescription = "")
                    Text(text = snackbarData.message)
                    snackbarData.actionLabel?.let {
                        Text(
                            text = it,
                            Modifier.clickable {
                                snackbarData.dismiss()
                            },
                            textAlign = TextAlign.End,
                            color = Color.Red,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

        }
    )
}
