package com.morcinek.xpense.home.history.period

import com.morcinek.xpense.R
import com.morcinek.xpense.common.utils.isSameDay
import com.morcinek.xpense.common.utils.minusDays
import com.morcinek.xpense.home.history.period.model.Period
import com.morcinek.xpense.home.history.period.model.PeriodObject
import java.util.*

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class PeriodFilterFactory {

    private val currentDay: Calendar by lazy { Calendar.getInstance() }
    private val yesterday: Calendar by lazy {
        val calendar = Calendar.getInstance()
        calendar.minusDays(1)
        calendar
    }

    fun getPeriodFilter(period: Period): PeriodObject {
        return when (period) {
            Period.TODAY -> PeriodObject(R.string.period_today, { it.date.isSameDay(currentDay) })
            Period.YESTERDAY -> PeriodObject(R.string.period_yesterday, { it.date.isSameDay(yesterday) })
        }
    }
}