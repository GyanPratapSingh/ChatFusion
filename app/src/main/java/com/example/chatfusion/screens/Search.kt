package com.example.chatfusion.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.chatfusion.item_view.ThreadItem
import com.example.chatfusion.item_view.UserItem
import com.example.chatfusion.viewmodel.HomeViewModel
import com.example.chatfusion.viewmodel.SearchViewModel
import com.google.firebase.auth.FirebaseAuth

@Composable
fun Search(navHostController: NavHostController)
{

    val searchViewModel: SearchViewModel = viewModel()
    val threadAndUsers by searchViewModel.userList.observeAsState(null)

    var search by remember {
        mutableStateOf("")
    }

Column {

    Text(text = "Search", style = TextStyle(
        fontWeight = FontWeight.ExtraBold,
        fontFamily = FontFamily.Serif,
        color = Color.DarkGray,
        fontSize = 24.sp

    ), modifier = Modifier.padding(top = 16.dp, start = 16.dp)
    )

    OutlinedTextField(value = search, onValueChange =  {  search = it }, label = {
        Text(text = "Search",style = TextStyle(
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif,
            color = Color.DarkGray,
            fontSize = 15.sp
        ))
    },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text
        ),
        singleLine = true,
        modifier = Modifier.fillMaxWidth(),
        leadingIcon = {

        }
    )



      LazyColumn {
        items(threadAndUsers ?: emptyList())
        { pairs ->
            UserItem(
                pairs,
                navHostController
            )
        }
      }
  }
}
