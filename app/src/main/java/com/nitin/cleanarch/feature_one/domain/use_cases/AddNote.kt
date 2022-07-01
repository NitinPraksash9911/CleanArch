package com.nitin.cleanarch.feature_one.domain.use_cases

import com.nitin.cleanarch.feature_one.domain.model.Note
import com.nitin.cleanarch.feature_one.domain.repository.NoteRepository

class AddNote(private val noteRepository: NoteRepository) {

    @Throws(Note.InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {
        if (note.title.isBlank()) throw Note.InvalidNoteException("Invalid title exception")
        if (note.content.isBlank()) throw Note.InvalidNoteException("Invalid content exception")
        noteRepository.insertNote(note)
    }
}