package com.nitin.cleanarch.feature_one.domain.model

import android.graphics.Color
import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
@Keep
data class Note(
    @PrimaryKey
    val id: Long? = null,
    val title: String,
    val content: String,
    val timeStamp: Long,
    val color: Int = noteColors.random()
) : Parcelable {


    class InvalidNoteException(message: String) : Exception(message)
}

    val noteColors = listOf(
        Color.parseColor("#f4b7de"),
        Color.parseColor("#c6fbc8"),
        Color.parseColor("#edbbf8"),
        Color.parseColor("#ffc9cf"),
        Color.parseColor("#ffe2b3"),
        Color.parseColor("#e2fac1"),
        Color.parseColor("#e9fac1"),
        Color.parseColor("#d6c2fe"),
        Color.parseColor("#fbf5be"),
        Color.parseColor("#b0e4f8"),
    )
