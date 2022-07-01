package com.nitin.cleanarch.feature_one.domain.use_cases

import com.nitin.cleanarch.feature_one.domain.repository.NotePreferenceRepository
import kotlinx.coroutines.flow.Flow

class GetNoteOrder(private val notePreferenceRepository: NotePreferenceRepository) {

    operator fun invoke():Flow<Int>{
        return notePreferenceRepository.getNoteOrder()
    }
}