package com.example.mynoteapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NoteViewModel(
    private val repository: NoteRepository
): ViewModel() {

    //internal mutable StateFlow
    private val _notes = MutableStateFlow<List<Note>>(emptyList())

    //external immutable StateFlow
    val notes: StateFlow<List<Note>> = _notes.asStateFlow()

    init {
        //Collect flow from repository into StateFlow
        viewModelScope.launch{
            repository.allNotes.collect { noteList ->
                _notes.value = noteList
            }
        }
    }

    fun addOrUpdateNote(note: Note){
        viewModelScope.launch{
            repository.upsert(note)
        }
    }

    fun deleteNote(note: Note){
        viewModelScope.launch{
            repository.delete(note)
        }
    }


}