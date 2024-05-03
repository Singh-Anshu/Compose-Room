package com.example.composeroom.di

import android.content.Context
import androidx.room.Room
import com.example.composeroom.repository.ContactRepository
import com.example.composeroom.room.ContactDao
import com.example.composeroom.room.ContactDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): ContactDatabase = Room
        .databaseBuilder(context, ContactDatabase::class.java, "contact_database")
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    fun provideContactDao(contactDatabase: ContactDatabase): ContactDao = contactDatabase.contactDao()

    @Provides
    fun provideItemRepository(contactDao: ContactDao): ContactRepository =
        ContactRepository(contactDao)

}