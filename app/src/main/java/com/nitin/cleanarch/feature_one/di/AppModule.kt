package com.nitin.cleanarch.feature_one.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.room.Room
import com.nitin.cleanarch.feature_one.data.data_source.NoteDao
import com.nitin.cleanarch.feature_one.data.data_source.NoteDatabase
import com.nitin.cleanarch.feature_one.data.repository.NotePreferenceRepositoryImpl
import com.nitin.cleanarch.feature_one.data.repository.NoteRepositoryImpl
import com.nitin.cleanarch.feature_one.domain.repository.NotePreferenceRepository
import com.nitin.cleanarch.feature_one.domain.repository.NoteRepository
import com.nitin.cleanarch.feature_one.domain.use_cases.AddNote
import com.nitin.cleanarch.feature_one.domain.use_cases.DeleteNote
import com.nitin.cleanarch.feature_one.domain.use_cases.GetNoteOrder
import com.nitin.cleanarch.feature_one.domain.use_cases.GetNotes
import com.nitin.cleanarch.feature_one.domain.use_cases.NoteUseCase
import com.nitin.cleanarch.feature_one.domain.use_cases.SaveNoteOrder
import com.nitin.cleanarch.feature_one.utils.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDb(@ApplicationContext context: Context): NoteDatabase {
        return Room.databaseBuilder(context,
            NoteDatabase::class.java,
            "note_db"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideNoteDao(noteDatabase: NoteDatabase): NoteDao {
        return noteDatabase.noteDao
    }


    @Provides
    @Singleton
    fun provideNoteRepository(noteDao: NoteDao, dispatcherProvider: DispatcherProvider): NoteRepository {
        return NoteRepositoryImpl(noteDao,dispatcherProvider)
    }

    @Provides
    @Singleton
    fun provideNotePrefRepo(dataStore: DataStore<Preferences>): NotePreferenceRepository {
        return NotePreferenceRepositoryImpl(dataStore)
    }


    @Singleton
    @Provides
    fun providePreferencesDataStore(@ApplicationContext appContext: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
//            corruptionHandler = ReplaceFileCorruptionHandler(
//                produceNewData = { emptyPreferences() }
//            ),
//            migrations = listOf(SharedPreferencesMigration(appContext,USER_PREFERENCES)),
            scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
            produceFile = { appContext.preferencesDataStoreFile("USER_PREFERENCES") }
        )
    }
    @Provides
    @Singleton
    fun provideNoteUseCases(
        repository: NoteRepository,
        notePrefRepo: NotePreferenceRepository
    ): NoteUseCase {
        return NoteUseCase(getNotes = GetNotes(repository),
            deleteNote = DeleteNote(repository),
            insertNote = AddNote(repository),
            getNoteOrder = GetNoteOrder(notePrefRepo),
            saveNoteOrder = SaveNoteOrder(notePrefRepo)
        )
    }



}