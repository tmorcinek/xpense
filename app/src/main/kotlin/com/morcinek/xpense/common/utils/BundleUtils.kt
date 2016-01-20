package com.morcinek.xpense.common.utils

import android.os.Bundle
import android.os.Parcelable

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
inline fun <reified T : Parcelable> Bundle.getParcelable(): T? = getParcelable(T::class.java.name)

inline fun <reified T : Parcelable> Bundle.putParcelable(value: T?) = putParcelable(T::class.java.name, value)

