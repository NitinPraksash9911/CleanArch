package com.nitin.cleanarch.feature_one.domain.use_cases

import com.nitin.cleanarch.feature_one.domain.model.Note
import com.nitin.cleanarch.feature_one.domain.repository.NoteRepository

class DeleteNote(private val noteRepository: NoteRepository) {

    suspend operator fun invoke(note: Note) {
        noteRepository.deleteNote(note)
    }
}