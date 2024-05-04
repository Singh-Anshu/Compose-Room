package com.example.composeroom.repository

import com.example.composeroom.room.Contact
import com.example.composeroom.room.ContactDao
import kotlinx.coroutines.flow.Flow

class ContactRepository(private val contactDao: ContactDao) {

    fun getContactsByFirstName(): Flow<List<Contact>> = contactDao.getContactsOrderedByFirstName()

    fun getContactsByLastName(): Flow<List<Contact>> = contactDao.getContactsOrderedByFirstName()

    fun getContactsByPhoneNo(): Flow<List<Contact>> = contactDao.getContactsOrderedByFirstName()

    suspend fun upsertContact(contact: Contact) = contactDao.upsertContact(contact)

    suspend fun deleteContact(contact: Contact) = contactDao.deleteContact(contact)


}