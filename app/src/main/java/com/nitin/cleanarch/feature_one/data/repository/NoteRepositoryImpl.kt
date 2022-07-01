package com.nitin.cleanarch.feature_one.data.repository

import com.nitin.cleanarch.feature_one.data.data_source.NoteDao
import com.nitin.cleanarch.feature_one.domain.model.Note
import com.nitin.cleanarch.feature_one.domain.repository.NoteRepository
import com.nitin.cleanarch.feature_one.utils.DispatcherProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class NoteRepositoryImpl(
    private val noteDao: NoteDao,
    private val dispatcherProvider: DispatcherProvider
) : NoteRepository {

    override fun getNotes(): Flow<List<Note>> {
        return noteDao.getNotes()
    }

    override suspend fun getNoteById(id: Long): Note? {
        return withContext(dispatcherProvider.io) {
            noteDao.getNoteById(id)
        }
    }

    override suspend fun insertNote(note: Note) {
        return withContext(dispatcherProvider.io) {
            noteDao.insertNote(note)
        }
    }

    override suspend fun deleteNote(note: Note) {
        return noteDao.deleteNote(note)
    }
}