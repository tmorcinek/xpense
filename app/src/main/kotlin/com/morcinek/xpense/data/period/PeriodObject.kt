package com.morcinek.xpense.data.period

import com.morcinek.xpense.data.expense.Expense

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class PeriodObject(val titleResource: Int, val filter: (Expense) -> Boolean) {}