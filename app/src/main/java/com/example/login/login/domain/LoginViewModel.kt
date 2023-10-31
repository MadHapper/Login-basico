package com.example.login.login.domain

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import com.example.login.UsuarioActivity
import com.example.login.listContact.ui.ListContactActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


class LoginViewModel : ViewModel() {


    private val _user = mutableStateOf("")
    val user: State<String> get() = _user

    private val _password = mutableStateOf("")
    val password: State<String> get() = _password

    private val _showError = mutableStateOf(false)
    val showError: State<Boolean> get() = _showError

    private val _showLogin = mutableStateOf(false)
    val showLogin: State<Boolean> get() = _showLogin


    fun onUserTextChanged(newUser: String) {
        _user.value = newUser
    }

    fun onPasswordTextChanged(newPassword: String) {
        _password.value = newPassword
    }

    fun onLoginClick() {
        val isValidCredentials = user.value == "asd" && password.value == "asd"
        _showError.value = !isValidCredentials
        _showLogin.value = isValidCredentials
    }
}