package com.morcinek.xpense.common.utils

import com.morcinek.xpense.BuildConfig
import java.text.SimpleDateFormat
import java.util.*

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */

fun Calendar.toString(format: String) = SimpleDateFormat(format).format(time)

fun Calendar.toShortDate() = toString(BuildConfig.SHORT_DATE_FORMAT)

fun Calendar.monthName() = getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())

val Calendar.month : Int
        get() = get(Calendar.MONTH)

val Calendar.year : Int
        get() = get(Calendar.YEAR)

val Calendar.dayOfYear : Int
        get() = get(Calendar.DAY_OF_YEAR)

fun Calendar.isSameMonth(otherDate : Calendar) = year == otherDate.year && month == otherDate.month

fun Calendar.isSameDay(otherDate : Calendar) = year == otherDate.year && dayOfYear == otherDate.dayOfYear