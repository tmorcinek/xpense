package com.morcinek.xpense.data.period

import com.morcinek.xpense.R
import com.morcinek.xpense.common.utils.*
import java.util.*

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class PeriodObjectFactory {

    fun getPeriodFilter(period: Period): PeriodObject {
        return when (period) {
            Period.TODAY -> PeriodObject(period, R.string.period_today, { it.date.isSameDay(today) })
            Period.YESTERDAY -> PeriodObject(period, R.string.period_yesterday, { it.date.isSameDay(yesterday) })
            Period.THIS_WEEK -> PeriodObject(period, R.string.period_this_week, { it.date.isSameWeek(today) })
            Period.LAST_WEEK -> PeriodObject(period, R.string.period_last_week, { it.date.isSameWeek(sevenDaysAgo) })
            Period.LAST_7_DAYS -> PeriodObject(period, R.string.period_last_7_days, { it.date in sevenDaysAgo..tomorrow })
            Period.LAST_30_DAYS -> PeriodObject(period, R.string.period_last_30_days, { it.date in last30Days })
            Period.LAST_MONTH -> PeriodObject(period, R.string.period_last_month, { it.date.isSameMonth(dayOfPreviousMonth) })
            Period.THIS_MONTH -> PeriodObject(period, R.string.period_this_month, { it.date.isSameMonth(today) })
            Period.ALL -> PeriodObject(period, R.string.period_all, { true })
        }
    }

    val last30Days by lazy {
        thirtyDaysAgo..tomorrow
    }

    val last2Weeks by lazy {
        weeksBefore(2)..tomorrow
    }

    val last5Weeks by lazy {
        weeksBefore(5)..tomorrow
    }

    val today: Calendar by lazy {
        val calendar = Calendar.getInstance()
        calendar.resetTime()
        calendar
    }

    private val yesterday: Calendar by lazy {
        val calendar = Calendar.getInstance()
        calendar.minusDays(1)
        calendar
    }

    private val tomorrow: Calendar by lazy {
        val calendar = Calendar.getInstance()
        calendar.plusDays(1)
        calendar.resetTime()
        calendar
    }

    private val thirtyDaysAgo: Calendar by lazy {
        val calendar = Calendar.getInstance()
        calendar.minusDays(30)
        calendar.resetTime()
        calendar
    }

    private val sevenDaysAgo: Calendar by lazy {
        val calendar = Calendar.getInstance()
        calendar.minusDays(7)
        calendar.resetTime()
        calendar
    }

    private val dayOfPreviousMonth: Calendar by lazy {
        val calendar = Calendar.getInstance()
        calendar.minusMonth(1)
        calendar.resetTime()
        calendar
    }

    private fun weeksBefore(weeks: Int) : Calendar  {
        val calendar = Calendar.getInstance()
        calendar.resetTime()
        calendar.minusWeeks(weeks)
        return calendar
    }
}