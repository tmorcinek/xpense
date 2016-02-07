package com.morcinek.xpense.common.utils

import com.morcinek.xpense.BuildConfig
import org.joda.time.LocalDate

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */

fun LocalDate.isSameMonth(otherDate: LocalDate) = year == otherDate.year && monthOfYear == otherDate.monthOfYear

fun LocalDate.monthName() = toString(BuildConfig.MONTH_FORMAT)

fun LocalDate.toShortDate() = toString(BuildConfig.SHORT_DATE_FORMAT)
