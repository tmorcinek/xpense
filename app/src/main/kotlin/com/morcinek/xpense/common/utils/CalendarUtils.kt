package com.morcinek.xpense.common.utils

import com.morcinek.xpense.BuildConfig
import java.text.SimpleDateFormat
import java.util.*

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */

fun Calendar.toString(format: String) = SimpleDateFormat(format).format(time)

fun Calendar.toShortDate() = toString(BuildConfig.SHORT_DATE_FORMAT)

fun Calendar.toDateFormat() = toString(BuildConfig.DATE_FORMAT)

fun Calendar.monthName() = getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())

val Calendar.month: Int
    get() = get(Calendar.MONTH)

val Calendar.year: Int
    get() = get(Calendar.YEAR)

val Calendar.dayOfYear: Int
    get() = get(Calendar.DAY_OF_YEAR)

val Calendar.weekOfYear: Int
    get() = get(Calendar.WEEK_OF_YEAR)

fun Calendar.isSameMonth(otherDate: Calendar) = year == otherDate.year && month == otherDate.month

fun Calendar.isSameDay(otherDate: Calendar) = year == otherDate.year && dayOfYear == otherDate.dayOfYear

fun Calendar.isSameWeek(otherDate: Calendar) = year == otherDate.year && weekOfYear == otherDate.weekOfYear

fun Calendar.plusDays(number: Int) = add(Calendar.DAY_OF_MONTH, number)

fun Calendar.minusDays(number: Int) = add(Calendar.DAY_OF_MONTH, -number)

fun Calendar.minusWeeks(number: Int) = add(Calendar.WEEK_OF_YEAR, -number)

fun Calendar.minusMonth(number: Int) = add(Calendar.MONTH, -number)

fun Calendar.resetTime() {
    set(Calendar.HOUR_OF_DAY, 0);            // set hour to midnight
    set(Calendar.MINUTE, 0);                 // set minute in hour
    set(Calendar.SECOND, 0);                 // set second in minute
    set(Calendar.MILLISECOND, 0);
}
