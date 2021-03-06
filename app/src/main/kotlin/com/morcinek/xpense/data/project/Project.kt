package com.morcinek.xpense.data.project

import android.os.Parcel
import android.os.Parcelable
import com.morcinek.xpense.common.utils.createParcel
import com.orm.SugarRecord

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class Project(var name: String = "", var location: String = "", var currency: String = "", id: Long? = null) : SugarRecord(), Parcelable {

    init {
        setId(id)
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(name)
        dest.writeString(location)
        dest.writeString(currency)
        dest.writeValue(id)
    }

    override fun describeContents() = 0

    companion object {
        @JvmField final val CREATOR = createParcel {
            Project(it.readString(), it.readString(), it.readString(), it.readValue(null) as Long?)
        }
    }
}