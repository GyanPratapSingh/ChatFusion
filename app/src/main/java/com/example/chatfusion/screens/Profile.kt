package com.example.chatfusion.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.chatfusion.item_view.ThreadItem
import com.example.chatfusion.model.UserModel
import com.example.chatfusion.navigation.Routes
import com.example.chatfusion.utils.SharedPref
import com.example.chatfusion.viewmodel.AuthViewModel
import com.example.chatfusion.viewmodel.UserViewModel
import com.google.firebase.auth.FirebaseAuth

@SuppressLint("SuspiciousIndentation")
@Composable
fun Profile(navHostController: NavHostController) {

    val context = LocalContext.current

    val authViewModel: AuthViewModel = viewModel()
    val firebaseUser by authViewModel.firebaseUser.observeAsState(null)

    val userViewModel: UserViewModel= viewModel()
    val threads by userViewModel.threads.observeAsState(null)

    val followerList by userViewModel.followerList.observeAsState(null)
    val followingList by userViewModel.followingList.observeAsState(null)

    var currentUserId = ""
    if (FirebaseAuth.getInstance().currentUser!= null)
        currentUserId = FirebaseAuth.getInstance().currentUser!!.uid

     if (currentUserId != "") {
         userViewModel.getfollowers(currentUserId)
         userViewModel.getfollowing(currentUserId)
     }


     val user = UserModel(
        fullName = SharedPref.getFullName(context),
         imageUrl = SharedPref.getImage(context)

     )

    if (firebaseUser != null)
    userViewModel.fetchThreads(firebaseUser!!.uid)




    LaunchedEffect(firebaseUser) {
        if (firebaseUser == null) {
            navHostController.navigate(Routes.Login.routes)
            {
                popUpTo(navHostController.graph.startDestinationId)
                launchSingleTop = true
            }

        }

    }

    LazyColumn {

        item {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(18.dp)

            ) {

                val (
                    text, logo, fullName,
                    role, followers, following,button
                ) = createRefs()



                Text(
                    text = SharedPref.getEmail(context), style = TextStyle(
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 24.sp
                    ), modifier = Modifier.constrainAs(text) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }
                )

                ConstraintLayout(
                    modifier = Modifier.fillMaxSize()
                ) {
                    val (logo) = createRefs()

                    Image(
                        painter = rememberAsyncImagePainter(model = SharedPref.getImage(context)),
                        contentDescription = "close",
                        modifier = Modifier
                            .constrainAs(logo) {
                                top.linkTo(parent.top)
                                end.linkTo(parent.end, margin = 16.dp) // Adjust margin as needed
                            }
                            .size(120.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                }




                Text(
                    text = SharedPref.getFullName(context),
                    style = TextStyle(
                        fontSize = 20.sp
                    ),
                    modifier = Modifier.constrainAs(fullName) {
                        top.linkTo(text.bottom)
                        start.linkTo(parent.start)
                    }
                )

                Text(
                    text = SharedPref.getRole(context),
                    style = TextStyle(
                        fontSize = 20.sp
                    ),
                    modifier = Modifier.constrainAs(role) {
                        top.linkTo(fullName.bottom)
                        start.linkTo(parent.start)
                    }
                )

                Text(
                    text = "${followerList!!.size} followers",
                    style = TextStyle(
                        fontSize = 20.sp
                    ),
                    modifier = Modifier.constrainAs(followers) {
                        top.linkTo(role.bottom)
                        start.linkTo(parent.start)
                    }
                )

                Text(
                    text = "${followingList!!.size} following",
                    style = TextStyle(
                        fontSize = 20.sp
                    ),
                    modifier = Modifier.constrainAs(following) {
                        top.linkTo(followers.bottom)
                        start.linkTo(parent.start)
                    }
                )

                ElevatedButton(onClick = {
                    authViewModel.logout()

                }, modifier = Modifier.constrainAs(button){
                    top.linkTo(following.bottom)
                    start.linkTo(parent.start)

                }
                )
                {
                    Text(text ="Logout")

                }


            }



        }
        items(threads ?: emptyList()){
            pair ->
            ThreadItem(
                thread = pair,
                users = user,
                navHostController = navHostController,
                userId = SharedPref.getFullName(context)
            )
        }
   }
}



