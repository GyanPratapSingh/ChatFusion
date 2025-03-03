package com.example.chatfusion.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.chatfusion.navigation.Routes
import com.example.chatfusion.viewmodel.AuthViewModel

@Composable
fun Login(navController: NavHostController)
{
    val authViewModel:AuthViewModel = viewModel()
    val firebaseUser by authViewModel.firebaseUser.observeAsState()
    val error by authViewModel.error.observeAsState()



    LaunchedEffect(firebaseUser) {
        if(firebaseUser!=null){
            navController.navigate(Routes.BottomNav.routes)
            {
                popUpTo(navController.graph.startDestinationId)
                launchSingleTop = true
            }

        }

    }
    LaunchedEffect(error) {
        if(error!=null){
            navController.navigate(Routes.BottomNav.routes)
            {
                popUpTo(navController.graph.startDestinationId)
                launchSingleTop = true
            }

        }

    }
    val context = LocalContext.current
   error?.let {
       Toast.makeText(context, "Invalid Details.", Toast.LENGTH_SHORT).show()
   }




    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }




  Column (modifier = Modifier
      .fillMaxSize()
      .padding(24.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center){

      Text(text = "Login Here", style = TextStyle(
          fontWeight = FontWeight.ExtraBold,
          fontFamily = FontFamily.Serif,
          color = Color.DarkGray,
          fontSize = 24.sp
      ))

       Box(modifier = Modifier.height(50.dp))

      OutlinedTextField(value = email, onValueChange =  {  email = it }, label = {
          Text(text = "Email",style = TextStyle(
                  fontWeight = FontWeight.Bold,
              fontFamily = FontFamily.Serif,
              color = Color.DarkGray,
              fontSize = 15.sp
          ))
      },
          keyboardOptions = KeyboardOptions(
              keyboardType = KeyboardType.Email
          ),
          singleLine = true,
          modifier = Modifier.fillMaxWidth()
      )

      Box(modifier = Modifier.height(25.dp))

      OutlinedTextField(value = password, onValueChange =  {  password = it }, label = {
          Text(text = "Password",style = TextStyle(
              fontWeight = FontWeight.Bold,
              fontFamily = FontFamily.Serif,
              color = Color.DarkGray,
              fontSize = 15.sp
          ))
      },
          keyboardOptions = KeyboardOptions(
              keyboardType = KeyboardType.Password
          ),
          singleLine = true,
          modifier = Modifier.fillMaxWidth()
      )

      Box(modifier = Modifier.height(32.dp))

      ElevatedButton(onClick = {
          if(email.isEmpty()||password.isEmpty())
          {
              Toast.makeText(context, "please provide all fields", Toast.LENGTH_SHORT).show()
          }
          else
          {
            authViewModel.login(email, password, context)
          }


       })


      {
          Text(text = "Login", style = TextStyle(
              fontWeight = FontWeight.Bold,
              fontFamily = FontFamily.SansSerif,
              fontSize = 20.sp
          ), modifier = Modifier.padding(vertical = 2.dp)
          )

      }
      Box(modifier = Modifier.height(80.dp))
      TextButton(onClick = {
          navController.navigate(Routes.Register.routes)
          {
              popUpTo(navController.graph.startDestinationId)
              launchSingleTop = true

          }

      })
      {
          Text(text = "Don't Have Account? Register Yourself", style = TextStyle(
              fontWeight = FontWeight.ExtraBold,
              fontFamily = FontFamily.SansSerif,
              fontSize = 16.sp
          ), modifier = Modifier.padding(vertical = 8.dp)
          )

      }





  }




}




@Preview(showBackground = true)
@Composable
fun LoginView()
{
   // Login()
}