package com.nitin.cleanarch.feature_one.presentation.notes

import com.nitin.cleanarch.feature_one.domain.model.Note
import com.nitin.cleanarch.feature_one.domain.util.NoteOrder

sealed class NoteEvent {
    data class Order(val noteOrder: NoteOrder) : NoteEvent()
    data class DeleteNote(val note: Note) : NoteEvent()
    data class AddNote(val note: Note) : NoteEvent()
}