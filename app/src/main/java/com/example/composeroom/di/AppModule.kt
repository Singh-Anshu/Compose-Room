package com.example.composeroom.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.composeroom.room.ContactDao
import com.example.composeroom.room.ContactDatabase
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    fun provideDatabase(@ApplicationContext context: Context): ContactDatabase = Room
        .databaseBuilder(context, ContactDatabase::class.java, "contact_database")
        .fallbackToDestructiveMigration()
        .build()

    fun provideContactDao(contactDatabase: ContactDatabase): ContactDao = contactDatabase.contactDao()

}