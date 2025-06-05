package com.example.myapplicationtlg.entity.relation

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class RoutineExercise(
    val id: Int?,
    val id_routine: Int,
    val id_exercise: Int
): Parcelable