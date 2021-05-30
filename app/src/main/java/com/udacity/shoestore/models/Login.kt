package com.udacity.shoestore.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Login (
    var username: String = "", var password: String = "") : Parcelable