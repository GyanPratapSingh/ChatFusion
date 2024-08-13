package com.example.chatfusion.navigation

 sealed class Routes(val routes: String)
 {
     data object Home : Routes("home")
     data object Notification : Routes("notification")
     data object Profile : Routes("profile")
     data object  Search : Routes("search")
     data object Splash : Routes("splash")
     data object AddThread : Routes("add_threads")
     data object BottomNav : Routes("bottom_nav")
     data object Login : Routes("login")
     data object Register : Routes("register")
     data object OtherUsers : Routes("other_users/{data}")



 }
