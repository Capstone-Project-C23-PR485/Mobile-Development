package com.android.skinchekai.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class WawasanResponse(
    val title: String,
    val description: String
): Parcelable
