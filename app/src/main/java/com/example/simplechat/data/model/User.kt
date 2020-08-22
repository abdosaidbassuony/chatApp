package com.example.simplechat.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val name: String? = "",
    val imageUrl: String? = "",
    val userId: String? = ""
):Parcelable