package com.example.login.listContact.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import coil.compose.rememberImagePainter
import com.example.login.R
import com.example.login.listContact.data.ModelListContact
import com.example.login.listContact.domain.ListContactViewModel
import com.example.login.ui.theme.LoginTheme

class ListContactActivity : ComponentActivity() {
    private val viewModel: ListContactViewModel by viewModels()
    //private var searchText by mutableStateOf("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginTheme {
                Surface {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                  var searchText = remember { mutableStateOf("")}
                        TitelContact()
                        SearchContact(
                            searchText = searchText.value,
                            onSearchTextChanged = { textSearch -> searchText.value = textSearch }
                        )
                        val filteredContacts = getFilteredContacts(viewModel.contacts, searchText.value)
                        ListContact1(contacts = filteredContacts)

                    }
                }
            }
        }
    }
}

private fun getFilteredContacts(contacts: List<ModelListContact>, query: String): List<ModelListContact> {
    return if (query.isBlank()) {
        contacts // If the search query is empty, return the original list
    } else {
        contacts.filter { contact ->
            contact.Name.contains(query, ignoreCase = true)
        }
    }
}

@Composable
fun TitelContact() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Red)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Lista de contactos",
            color = Color.Black
        )
    }
}

@Composable
fun SearchContact(searchText: String, onSearchTextChanged: (String) -> Unit) {
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = searchText,
        onValueChange = { onSearchTextChanged(it) },
        label = { Text(text = "Buscar") }
    )
}

@Composable
fun ListContact(contacts: List<ModelListContact>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        for (contact in contacts) {
            ContactItem(contact)
        }
    }
}

@Composable
fun ListContact1(contacts: List<ModelListContact>) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(contacts){ ContactItem(contact = it)}
    }
}

@Composable
fun ContactItem(contact: ModelListContact) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row() {
            Column(modifier = Modifier.padding(16.dp)) {

                Text(text = "Nombre: ${contact.Name}")
                Text(text = "Tipo: ${contact.Phone}")
            }

        }
    }
}