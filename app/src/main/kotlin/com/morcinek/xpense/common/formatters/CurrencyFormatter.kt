package com.morcinek.xpense.common.formatters

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class CurrencyFormatter {

    fun format(value: Double) = java.lang.String.format("$ %.2f", value)
}