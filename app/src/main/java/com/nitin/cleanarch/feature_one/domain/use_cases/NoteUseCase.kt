package com.nitin.cleanarch.feature_one.domain.use_cases

data class NoteUseCase(
    val getNotes: GetNotes,
    val deleteNote: DeleteNote,
    val insertNote: AddNote,
    val saveNoteOrder: SaveNoteOrder,
    val getNoteOrder: GetNoteOrder
) {
}