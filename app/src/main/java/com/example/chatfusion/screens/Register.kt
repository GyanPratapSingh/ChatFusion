package com.example.chatfusion.screens

import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.chatfusion.R
import com.example.chatfusion.navigation.Routes
import com.example.chatfusion.viewmodel.AuthViewModel

@Composable
fun Register(navHostController: NavHostController)
{

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var fullName by remember {
        mutableStateOf("")
    }

    var role by remember {
        mutableStateOf("")
    }

    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }
   
    val authViewModel : AuthViewModel = viewModel()
    val firebaseUser by authViewModel.firebaseUser.observeAsState(null)
    

    val permissionToRequest = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        android.Manifest.permission.READ_EXTERNAL_STORAGE
    }
    else
    {
        android.Manifest.permission.READ_EXTERNAL_STORAGE

    }

    val context = LocalContext.current



    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent())
    {
        uri: Uri? ->
        imageUri = uri
        
    }

    val permissionLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission())
    {
        isGranted : Boolean ->
        if (isGranted)
        {

        }
        else
        {

        }
    }
    LaunchedEffect(firebaseUser) {
        if(firebaseUser!=null){
            navHostController.navigate(Routes.BottomNav.routes)
            {
                popUpTo(navHostController.graph.startDestinationId)
                launchSingleTop = true
            }

        }
        
    }






    Column (modifier = Modifier
        .fillMaxSize()
        .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){

        Text(text = "Register Yourself", style = TextStyle(
            fontWeight = FontWeight.ExtraBold,
            fontFamily = FontFamily.Serif,
            color = Color.DarkGray,
            fontSize = 24.sp
        )
        )
        Box(modifier = Modifier.height(25.dp))

        Image(
            painter = if(imageUri == null) painterResource(id = R.drawable.person)
            else rememberAsyncImagePainter(model =imageUri), contentDescription ="person",
            modifier = Modifier
                .size(96.dp)
                .clip(CircleShape)
                .background(Color.LightGray)
                .clickable {
                    val isGranted = ContextCompat.checkSelfPermission(
                        context, permissionToRequest
                    ) == PackageManager.PERMISSION_GRANTED

                    if (isGranted) {
                        launcher.launch("image/*")

                    } else {
                        permissionLauncher.launch(permissionToRequest)

                    }


                }, contentScale = ContentScale.Crop )




        Box(modifier = Modifier.height(25.dp))

        OutlinedTextField(value = fullName, onValueChange =  {  fullName = it }, label = {
            Text(text = "fullName",style = TextStyle(
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

        Box(modifier = Modifier.height(5.dp))

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

        Box(modifier = Modifier.height(5.dp))

        OutlinedTextField(value = role, onValueChange =  {  role = it }, label = {
            Text(text = "username",style = TextStyle(
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



        Box(modifier = Modifier.height(5.dp))

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
            if(fullName.isEmpty()||email.isEmpty()||password.isEmpty()||role.isEmpty()||imageUri==null)
            {
                Toast.makeText(context, "Please Fill All Details", Toast.LENGTH_SHORT).show()
            }
            else
            {
                authViewModel.register(email, password, fullName, role, imageUri!!, context)

            }

        })
        {
            Text(text = "Register", style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif,
                fontSize = 20.sp
            ), modifier = Modifier.padding(vertical = 2.dp)
            )

        }
        Box(modifier = Modifier.height(80.dp))

        TextButton(onClick = {
            navHostController.navigate(Routes.Login.routes)
            {
                popUpTo(navHostController.graph.startDestinationId)
                launchSingleTop = true

            }


        })
        {
            Text(text = " You Have Account? Login Yourself", style = TextStyle(
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
fun RegisterView()
{
   // Register()
}