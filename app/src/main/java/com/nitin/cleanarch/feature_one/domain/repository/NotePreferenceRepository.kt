package com.nitin.cleanarch.feature_one.domain.repository

import kotlinx.coroutines.flow.Flow

interface NotePreferenceRepository {

    suspend fun saveNoteOrder(int: Int)
    fun getNoteOrder(): Flow<Int>
}