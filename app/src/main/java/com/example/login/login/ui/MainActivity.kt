package com.example.login.login.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.login.LoadUserActivity
import com.example.login.R
import com.example.login.UsuarioActivity
import com.example.login.login.domain.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint


class MainActivity : ComponentActivity() {

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Login(viewModel)
        }
    }

    @Composable
    fun Login(viewModel: LoginViewModel) {
        val user = viewModel.user.value
        val password = viewModel.password.value
        val showError = viewModel.showError.value
        val showLogin = viewModel.showLogin.value
        val context = LocalContext.current

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(30.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            ImangeUser(borderWidth = 10.dp)
            Spacer()
            EditTextUser(user = user, onTextChanged = viewModel::onUserTextChanged)
            Spacer()
            EditTextPassword(password = password, onTextChanged = viewModel::onPasswordTextChanged)
            Spacer()
            if (showError) {
                Text(
                    text = "Usuario o contraseña incorrecta",
                    color = Color.Red,
                    style = TextStyle(fontSize = 20.sp)
                )
            }
            if (showLogin) { val intent = Intent(context, LoadUserActivity::class.java)
                context.startActivity(intent)}
            Spacer()
            ButtonLogin( onLoginClick = viewModel::onLoginClick)

        }
    }

    @Composable
    fun EditTextUser(user: String, onTextChanged: (String) -> Unit) {
        TextField(
            value = user,
            onValueChange = onTextChanged,
            modifier = Modifier.fillMaxWidth().background(Color.White),
            placeholder = { Text(text = "Usuario") },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle.Default.copy(fontSize = 20.sp)
        )
    }

    @Composable
    fun EditTextPassword(password: String, onTextChanged: (String) -> Unit) {
        TextField(
            value = password,
            onValueChange = onTextChanged,
            modifier = Modifier.fillMaxWidth().background(Color.White),
            placeholder = { Text(text = "Contraseña") },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle.Default.copy(fontSize = 20.sp),
            visualTransformation = PasswordVisualTransformation()
        )
    }

    @Composable
    fun ButtonLogin(onLoginClick: () -> Unit) {
        Button(onClick = onLoginClick) {
            Text(text = "Iniciar Sesión", style = TextStyle(fontSize = 20.sp))
        }
    }

    @Composable
    fun Spacer (){
        Spacer(modifier = Modifier.height(15.dp))
    }

    @Composable
    fun ImangeUser(borderWidth: Dp){
        Card(
            modifier = Modifier.size(250.dp),
            shape = CircleShape,
        ) {
            Image(
                painter = rememberImagePainter(
                    data = "https://rickandmortyapi.com/api/character/avatar/19.jpeg"
                ),
               contentDescription = "user"
           )
            }

        }
}
