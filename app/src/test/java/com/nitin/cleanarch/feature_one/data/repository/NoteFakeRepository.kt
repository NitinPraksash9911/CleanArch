package com.nitin.cleanarch.feature_one.data.repository

import com.nitin.cleanarch.feature_one.domain.model.Note
import com.nitin.cleanarch.feature_one.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NoteFakeRepository : NoteRepository {
    private val notes = mutableListOf<Note>()

    override fun getNotes(): Flow<List<Note>> {
        return flow {
            emit(notes)
        }
    }

    override suspend fun getNoteById(id: Long): Note? {
        return notes.find { it.id == id }
    }

    override suspend fun insertNote(note: Note) {
        notes.add(note)
    }

    override suspend fun deleteNote(note: Note) {
        notes.remove(note)
    }


}