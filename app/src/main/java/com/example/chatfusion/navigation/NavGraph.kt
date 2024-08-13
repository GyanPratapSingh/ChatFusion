package com.example.chatfusion.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.chatfusion.screens.AddThreads
import com.example.chatfusion.screens.BottomNav
import com.example.chatfusion.screens.Home
import com.example.chatfusion.screens.Login
import com.example.chatfusion.screens.Notification
import com.example.chatfusion.screens.OtherUsers
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
          AddThreads(navController)

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

      composable(Routes.OtherUsers.routes)
      {
       val data = it.arguments!!.getString("data")
          OtherUsers(navController, data!!)

     }

  }
}