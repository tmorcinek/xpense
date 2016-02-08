package com.morcinek.xpense.home.history.period

import com.morcinek.xpense.R
import com.morcinek.xpense.common.utils.isSameDay
import com.morcinek.xpense.common.utils.minusDays
import com.morcinek.xpense.common.utils.resetTime
import com.morcinek.xpense.home.history.period.model.Period
import com.morcinek.xpense.home.history.period.model.PeriodObject
import java.util.*

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class PeriodFilterFactory {

    private val today: Calendar by lazy { Calendar.getInstance() }
    private val yesterday: Calendar by lazy {
        val calendar = Calendar.getInstance()
        calendar.minusDays(1)
        calendar.resetTime()
        calendar
    }

    private val firstDayOfThisWeek: Calendar by lazy {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        calendar.resetTime()
        calendar
    }

    private val firstDayOfPreviousWeek: Calendar by lazy {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        calendar.minusDays(7)
        calendar.resetTime()
        calendar
    }

    fun getPeriodFilter(period: Period): PeriodObject {
        return when (period) {
            Period.TODAY -> PeriodObject(R.string.period_today, { it.date.isSameDay(today) })
            Period.YESTERDAY -> PeriodObject(R.string.period_yesterday, { it.date.isSameDay(yesterday) })
            Period.THIS_WEEK -> PeriodObject(R.string.period_this_week, { it.date >= firstDayOfThisWeek })
            Period.LAST_WEEK -> PeriodObject(R.string.period_last_week, { it.date in firstDayOfPreviousWeek..firstDayOfThisWeek })
        }
    }
}