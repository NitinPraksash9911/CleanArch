package com.nitin.cleanarch.feature_one.domain.use_cases

import com.nitin.cleanarch.feature_one.domain.repository.NotePreferenceRepository
import com.nitin.cleanarch.feature_one.domain.util.NoteOrder

class SaveNoteOrder(private val notePreferenceRepository: NotePreferenceRepository) {

    suspend operator fun invoke(order: NoteOrder) {
        notePreferenceRepository.saveNoteOrder(order.ordinal)
    }
}