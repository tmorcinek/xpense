package com.morcinek.xpense.data.project

import android.os.Parcel
import android.os.Parcelable
import com.morcinek.xpense.common.utils.createParcel

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
data class Project(var name: String, var location: String, var currency: String) : Parcelable {

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(name)
        dest.writeString(location)
        dest.writeString(currency)
    }

    override fun describeContents() = 0

    companion object {
        val CREATOR = createParcel { Project(it.readString(), it.readString(), it.readString()) }
    }
}