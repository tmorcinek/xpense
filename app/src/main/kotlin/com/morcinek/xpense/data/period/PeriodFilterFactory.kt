package com.morcinek.xpense.data.period

import com.morcinek.xpense.R
import com.morcinek.xpense.common.utils.*
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
    private val tomorrow: Calendar by lazy {
        val calendar = Calendar.getInstance()
        calendar.plusDays(1)
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
        val calendar = firstDayOfThisWeek.clone() as Calendar
        calendar.minusDays(7)
        calendar.resetTime()
        calendar
    }

    private val thirtyDaysAgo: Calendar by lazy {
        val calendar = Calendar.getInstance()
        calendar.minusDays(30)
        calendar.resetTime()
        calendar
    }

    private val firstDayOfThisMonth: Calendar by lazy {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.resetTime()
        calendar
    }

    private val firstDayOfPreviousMonth: Calendar by lazy {
        val calendar = firstDayOfThisMonth.clone() as Calendar
        calendar.minusMonth(1)
        calendar.resetTime()
        calendar
    }

    fun getPeriodFilter(period: Period): PeriodObject {
        return when (period) {
            Period.TODAY -> PeriodObject(R.string.period_today, { it.date.isSameDay(today) })
            Period.YESTERDAY -> PeriodObject(R.string.period_yesterday, { it.date.isSameDay(yesterday) })
            Period.THIS_WEEK -> PeriodObject(R.string.period_this_week, { it.date in firstDayOfThisWeek..tomorrow })
            Period.LAST_WEEK -> PeriodObject(R.string.period_last_week, { it.date in firstDayOfPreviousWeek..firstDayOfThisWeek })
            Period.LAST_30_DAYS -> PeriodObject(R.string.period_last_30_days, { it.date in thirtyDaysAgo..tomorrow })
            Period.LAST_MONTH -> PeriodObject(R.string.period_last_month, { it.date in firstDayOfPreviousMonth..firstDayOfThisMonth })
        }
    }
}