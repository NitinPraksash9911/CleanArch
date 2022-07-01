package com.nitin.cleanarch.feature_one.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nitin.cleanarch.feature_one.domain.model.Note

@Database(
    entities = [Note::class],
    version = 1
)
abstract class NoteDatabase : RoomDatabase() {
    abstract val noteDao: NoteDao
}