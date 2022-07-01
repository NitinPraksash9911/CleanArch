package com.nitin.cleanarch.feature_one.data.repository

import com.nitin.cleanarch.feature_one.domain.repository.NotePreferenceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NotePrefFakeRepository : NotePreferenceRepository {

    private var order: Int = 0

    override suspend fun saveNoteOrder(int: Int) {
        order = int
    }

    override fun getNoteOrder(): Flow<Int> {
        return flow { emit(order) }
    }


}