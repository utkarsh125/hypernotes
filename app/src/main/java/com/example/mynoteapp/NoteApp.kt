package com.example.mynoteapp

import android.app.Application
import androidx.room.Room

class NoteApp: Application() {

    private lateinit var database: AppDatabase
    private lateinit var repository: NoteRepository

    override fun onCreate(){
        super.onCreate()
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "notes.db"
        ).build()

        repository = NoteRepository(database.noteDao())
    }

    fun getRepository(): NoteRepository = repository
}