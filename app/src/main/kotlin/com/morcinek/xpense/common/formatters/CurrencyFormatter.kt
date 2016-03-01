package com.morcinek.xpense.common.formatters

import com.morcinek.xpense.BuildConfig
import com.morcinek.xpense.common.utils.isInteger
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class CurrencyFormatter {

    fun format(value: Double, currencySymbol: String) = "${DecimalFormat(BuildConfig.CURRENCY_FORMAT).format(value).removeSuffix(".00")}${currencySymbol}"
}