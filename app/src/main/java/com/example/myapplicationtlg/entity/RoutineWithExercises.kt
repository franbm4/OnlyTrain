package com.example.myapplicationtlg.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class RoutineWithExercises(
    val id: Int?,
    val name: String,
    val description: String,
    val exercises: List<Exercise>
): Parcelable