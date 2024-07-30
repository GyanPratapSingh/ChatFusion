package com.example.chatfusion.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.chatfusion.screens.AddThreads
import com.example.chatfusion.screens.BottomNav
import com.example.chatfusion.screens.Home
import com.example.chatfusion.screens.Login
import com.example.chatfusion.screens.Notification
import com.example.chatfusion.screens.Profile
import com.example.chatfusion.screens.Register
import com.example.chatfusion.screens.Search
import com.example.chatfusion.screens.Splash

@Composable
fun NavGraph(navController: NavHostController)
{
  NavHost(navController = navController,
          startDestination =Routes.Splash.routes)
  {

      composable(Routes.Splash.routes)
      {
          Splash(navController)

      }

      composable(Routes.Home.routes)
      {
          Home()

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
          Search()

      }

      composable(Routes.AddThreads.routes)
      {
          AddThreads()

      }

      composable(Routes.BottomNav.routes)
      {
          BottomNav(navController)

      }

      composable(Routes.Login.routes)
      {
          Login(navController)

      }

      composable(Routes.Register.routes)
      {
          Register(navController)

      }


      
  }
}