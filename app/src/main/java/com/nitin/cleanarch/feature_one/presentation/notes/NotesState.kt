package com.nitin.cleanarch.feature_one.presentation.notes

import com.nitin.cleanarch.feature_one.domain.model.Note
import com.nitin.cleanarch.feature_one.domain.util.NoteOrder
import com.nitin.cleanarch.feature_one.domain.util.OrderType


//change state with my own UI state
data class NotesState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date
) {
}