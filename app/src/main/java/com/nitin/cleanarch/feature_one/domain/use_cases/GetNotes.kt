package com.nitin.cleanarch.feature_one.domain.use_cases

import com.nitin.cleanarch.feature_one.domain.model.Note
import com.nitin.cleanarch.feature_one.domain.repository.NoteRepository
import com.nitin.cleanarch.feature_one.domain.util.NoteOrder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetNotes(
    private val noteRepository: NoteRepository
) {

    operator fun invoke(
        noteOrder: NoteOrder = NoteOrder.Date
    ): Flow<List<Note>> {
        return noteRepository.getNotes().map { notes ->
            when (noteOrder) {
                NoteOrder.Date -> {
                    notes.sortedByDescending { it.timeStamp }
                }
                NoteOrder.Title -> {
                    notes.sortedBy { it.title }
                }
                NoteOrder.Color -> {
                    notes.sortedBy { it.color }
                }
            }

        }
    }

}
