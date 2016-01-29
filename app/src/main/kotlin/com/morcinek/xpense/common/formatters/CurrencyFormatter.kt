package com.morcinek.xpense.common.formatters

import com.morcinek.xpense.BuildConfig

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class CurrencyFormatter {

    fun format(value: Double, currencySymbol: String) = BuildConfig.CURRENCY_FORMAT.format(value, currencySymbol)
}