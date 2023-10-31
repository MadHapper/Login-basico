package com.example.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.login.ui.theme.LoginTheme

class UsuarioActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginTheme {
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    Column(
                    ) {
                        MenuUser()
                    }
                }
            }
        }
    }
}

@Composable
fun MenuUser(){
    var SelectedMenuUsuario by remember { mutableStateOf("")}
    var ExpandedMenuUsuario by remember { mutableStateOf(false)}
    var ListMenuUsuario = listOf("Mi perfil", "Mi cartera" , "Cambiar contraseña" , "Cerrar secion")

    Column(
        Modifier.padding(10.dp)) {
        Row() {

            /// 1
            OutlinedTextField(value = SelectedMenuUsuario,
                onValueChange = {SelectedMenuUsuario=it},
                enabled = false,
                readOnly = true,
                modifier = Modifier
                    .clickable{ExpandedMenuUsuario =true}
            )
            DropdownMenu(
                expanded = ExpandedMenuUsuario,
                onDismissRequest = { ExpandedMenuUsuario = false },
            ) {
                ListMenuUsuario.forEach{ Item -> DropdownMenuItem(
                    onClick = {
                        ExpandedMenuUsuario = false
                        SelectedMenuUsuario = Item
                    }) {
                    Text(text = Item)

                } }
            }

            ///2
            OutlinedTextField(value = SelectedMenuUsuario,
                onValueChange = {SelectedMenuUsuario=it},
                enabled = false,
                readOnly = true,
                modifier = Modifier
                    .clickable{ExpandedMenuUsuario =true}
            )
            DropdownMenu(
                expanded = ExpandedMenuUsuario,
                onDismissRequest = { ExpandedMenuUsuario = false },
            ) {
                ListMenuUsuario.forEach{ Item -> DropdownMenuItem(
                    onClick = {
                        ExpandedMenuUsuario = false
                        SelectedMenuUsuario = Item
                    }) {
                    Text(text = Item)

                } }
            }


            
        }
        
    }
}

