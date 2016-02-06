package com.morcinek.xpense.common.utils

import org.joda.time.LocalDate

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */

fun LocalDate.isSameMonth(otherDate: LocalDate) = year == otherDate.year && monthOfYear == otherDate.monthOfYear

fun LocalDate.monthName() = toString("MMMM")

