package com.example.chatfusion.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.chatfusion.R
import com.example.chatfusion.navigation.Routes
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay

@Composable
fun Splash(navController: NavController)
{

    ConstraintLayout(modifier = Modifier.fillMaxSize())
    {
        val (image) = createRefs()

        Image(painter = painterResource(id = R.drawable.logo) , contentDescription ="Logo",
          modifier =  Modifier.constrainAs(image)
          {
              top.linkTo(parent.top)
              bottom.linkTo(parent.bottom)
              start.linkTo(parent.start)
              end.linkTo(parent.end)

          }.size(200.dp))

    }

    LaunchedEffect(key1 = true) {

        delay(5000)

        if(FirebaseAuth.getInstance().currentUser!=null)
        navController.navigate(Routes.BottomNav.routes)
        {
            popUpTo(navController.graph.startDestinationId)
            launchSingleTop = true

        }


        else
        navController.navigate(Routes.Login.routes)
        {
            popUpTo(navController.graph.startDestinationId)
            launchSingleTop = true

        }




    }

}