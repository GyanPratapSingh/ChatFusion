package com.example.chatfusion.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.chatfusion.model.BottomNavItem
import com.example.chatfusion.navigation.Routes

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BottomNav(navController: NavHostController)
{
    val navController1 = rememberNavController()

    Scaffold(bottomBar = { MyBottomBar(navController1) })
    {
        innerPadding ->
        NavHost(navController = navController1, startDestination =Routes.Home.routes,
            modifier = Modifier.padding(innerPadding))
        {
            composable(route = Routes.Home.routes)
            {
                Home(navController)
            }

            composable(Routes.Profile.routes)
            {
                Profile(navController)

            }

            composable(Routes.Notification.routes)
            {
                Notification()

            }

            composable(Routes.Search.routes)
            {
                Search(navController)

            }

            composable(Routes.AddThread.routes)
            {
                AddThreads(navController1)

            }

        }


    }

}


@Composable
fun MyBottomBar(navController1: NavHostController)
{
     val backStackEntry = navController1.currentBackStackEntryAsState()


    val list = listOf(

        BottomNavItem
        (
            title = "Home",
            Routes.Home.routes,
            Icons.Rounded.Home
        ),

        BottomNavItem
        (
            title = "Search",
            Routes.Search.routes,
            Icons.Rounded.Search
        ),

        BottomNavItem
        (
            title = "Add Threads",
            Routes.AddThread.routes,
            Icons.Rounded.Add
        ),

        BottomNavItem
        (
            title = "Notification",
            Routes.Notification.routes,
            Icons.Rounded.Notifications
        ),

        BottomNavItem
        (
            title = "Profile",
            Routes.Profile.routes,
            Icons.Rounded.Person
        )
    )

    BottomAppBar {
        list.forEach {
            val selected = it.route == backStackEntry.value?.destination?.route
            
            NavigationBarItem(selected =selected ,
                onClick = {
                  navController1.navigate(it.route)
                  {
                      popUpTo(navController1.graph.findStartDestination().id)
                      {
                          saveState = true

                      }
                      launchSingleTop = true


                  }
                },
                icon = { 
                    Icon(imageVector = it.icon, contentDescription = it.title)
                
                })
        }
    }




}
