package com.morcinek.xpense.common.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import org.jetbrains.anko.internals.AnkoInternals
import org.jetbrains.anko.startActivity

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
inline fun <reified T : Parcelable> Bundle.getParcelable(): T? = getParcelable(T::class.java.name)

inline fun <reified T : Parcelable> Bundle.putParcelable(value: T?) = putParcelable(T::class.java.name, value)

inline fun <reified T : Parcelable> Intent.getParcelableExtra() = getParcelableExtra<T>(T::class.java.name)

inline fun <reified T : Activity> Context.startActivity(any: Any) {
    AnkoInternals.internalStartActivity(this, T::class.java, arrayOf(Pair(any.javaClass.name, any)))
}
