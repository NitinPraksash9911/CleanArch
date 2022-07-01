package com.nitin.cleanarch.feature_one.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import com.nitin.cleanarch.feature_one.domain.repository.NotePreferenceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NotePreferenceRepositoryImpl(
    private val dataStore: DataStore<Preferences>,
) :
    NotePreferenceRepository {
    private val NOTE_ORDER = intPreferencesKey("note_order")

    override suspend fun saveNoteOrder(int: Int) {
        dataStore.edit { pref ->
            pref[NOTE_ORDER] = int
        }
    }

    override fun getNoteOrder(): Flow<Int> {
        return dataStore.data.map { pref ->
            pref[NOTE_ORDER] ?: 0
        }
    }
}