package com.example.submissionandroidluthfi

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Food(
    val name: String,
    val photo: String,
    val description: String,
    val city: String,
    val ingredients: String,
) : Parcelable
