package com.nitin.cleanarch.feature_one.domain.repository

import com.nitin.cleanarch.feature_one.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    fun getNotes(): Flow<List<Note>>

    suspend fun getNoteById(id: Long): Note?

    suspend fun insertNote(note: Note)

    suspend fun deleteNote(note: Note)
}