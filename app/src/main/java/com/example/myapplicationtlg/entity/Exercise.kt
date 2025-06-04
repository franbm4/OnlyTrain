package com.example.myapplicationtlg.entity

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Exercise(
    val id: Int?,
    val name: String,
    val description: String
): Parcelable
