package com.morcinek.xpense.data.note

import android.content.Intent
import android.os.Parcel
import android.os.Parcelable
import com.morcinek.xpense.common.utils.createParcel
import com.morcinek.xpense.common.utils.readCalendar
import com.morcinek.xpense.common.utils.readParcelable
import com.morcinek.xpense.common.utils.writeCalendar
import com.morcinek.xpense.data.category.Category
import com.morcinek.xpense.data.expense.Expense

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
enum class ExpenseAction {
    DELETED, UPDATED, CREATED
}