package com.example.mynoteapp

import kotlinx.coroutines.flow.Flow

class NoteRepository(private val noteDao: NoteDao) {

    val allNotes: Flow<List<Note>> = noteDao.getAllNotes()

    suspend fun upsert(note: Note){
        noteDao.upsertNote(note)
    }

    suspend fun delete(note: Note){
        noteDao.deleteNote(note)
    }
}