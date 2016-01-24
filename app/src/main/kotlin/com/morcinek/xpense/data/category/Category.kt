package com.morcinek.xpense.data.category

import android.os.Parcel
import android.os.Parcelable
import com.morcinek.xpense.common.utils.createParcel

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */

data class Category(val name: String, val color: Int? = null) : Comparable<Category>, Parcelable {

    override fun compareTo(other: Category) = name.compareTo(other.name)

    override fun toString() = name

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(name)
        dest.writeValue(color)
    }

    override fun describeContents() = 0

    companion object {
        val CREATOR = createParcel { Category(it.readString(), it.readValue(null) as Int?) }
    }
}