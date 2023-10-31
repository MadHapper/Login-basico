package com.example.login

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.login.listContact.ui.ListContactActivity
import com.example.login.ui.theme.LoginTheme

class LoadUserActivity : ComponentActivity() {
    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    var loading by remember { mutableStateOf(true) }

                    Column(
                        Modifier
                            .padding(20.dp)
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if (loading) {
                            ProgressBar()
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(text = "Obteniendo datos")
                        }
                    }

                    if (loading) {
                        Handler(Looper.getMainLooper()).postDelayed({
                            loading = false
                            startActivity(Intent(this, ListContactActivity::class.java))
                        }, 3000)

                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
    }
}

@Composable
fun ProgressBar(){
    CircularProgressIndicator(
        modifier = Modifier.size(70.dp))
}
