package com.example.composeroom.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.composeroom.repository.ContactRepository
import com.example.composeroom.room.Contact
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactViewModel @Inject constructor(private val contactRepository: ContactRepository) : ViewModel() {

    private val _contacts = MutableStateFlow<List<Contact>>(emptyList())
    val contact: StateFlow<List<Contact>> = _contacts.asStateFlow()

    private val allLastNameContact = contactRepository.getContactsByFirstName()
    private val byPhoneNoContact = contactRepository.getContactsByFirstName()

    init {
        viewModelScope.launch {
            contactRepository.getContactsByFirstName().collect { firstNameContact ->
                _contacts.value = firstNameContact
            }
        }
    }


    val lastNameContacts: LiveData<List<Contact>> = allLastNameContact.asLiveData()

    val phoneNoContacts: LiveData<List<Contact>> = byPhoneNoContact.asLiveData()

    fun upsertContact(contact: Contact) = viewModelScope.launch {
        contactRepository.upsertContact(contact)
    }

    fun deleteContact(contact: Contact) = viewModelScope.launch {
        contactRepository.deleteContact(contact)
    }
}