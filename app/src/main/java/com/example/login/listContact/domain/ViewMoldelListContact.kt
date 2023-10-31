package com.example.login.listContact.domain


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.login.listContact.data.ModelListContact

class ListContactViewModel : ViewModel() {
    private val _contacts = mutableListOf<ModelListContact>()
    val contacts: List<ModelListContact>
        get() = _contacts

    private val _filteredContacts = MutableLiveData<List<ModelListContact>>()
    val filteredContacts: LiveData<List<ModelListContact>>
        get() = _filteredContacts

    private val _searchText = MutableLiveData("")
    val searchText: LiveData<String>
        get() = _searchText

    init {
        _contacts.addAll(contactsData())
        filterContacts()
    }

    fun setSearchText(text: String) {
        _searchText.value = text
        filterContacts()
    }

    private fun filterContacts() {
        val searchTerm = _searchText.value?.trim()?.toLowerCase() ?: ""
        val filteredList = _contacts.filter { it.Name.toLowerCase().contains(searchTerm) }
        _filteredContacts.value = filteredList
    }


    private fun contactsData(): List<ModelListContact> {
        return listOf(
            ModelListContact(1, "Juan Pérez", "9234567890"),
            ModelListContact(2, "María García", "9876543210"),
            ModelListContact(3, "Pedro Sánchez", "9678901234"),
            ModelListContact(4, "Paco Yunke", "95156854534"),
            ModelListContact(5, "Ricardo Palma", "925641684"),
            ModelListContact(7, "Ricardo Palma", "925641684"),
            ModelListContact(8, "Ricardo Palma", "925641684"),
            ModelListContact(9, "Ricardo Palma", "925641684"),
            ModelListContact(10, "Ricardo Palma", "925641684"),
            ModelListContact(11, "Ricardo Palma", "925641684"),
            ModelListContact(12, "Tina Tuna", "915228589")
        )
    }

}
