package com.example.chatfusion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import androidx.navigation.findNavController
import com.example.chatfusion.navigation.NavGraph
import com.example.chatfusion.ui.theme.ChatFusionTheme

class MainActivity : ComponentActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContent {

            ChatFusionTheme{

                Surface (modifier = Modifier.fillMaxSize(),
                         color = MaterialTheme.colorScheme.background
                )
                {
                    val  navController = rememberNavController()
                    NavGraph(navController =navController )

                }

            }
        }
    }
}