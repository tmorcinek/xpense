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

    //    override fun writeToParcel(dest: Parcel, flags: Int) {
    //        dest.writeDouble(value)
    //        dest.writeParcelable(category, 0)
    //        dest.writeString(note)
    //        dest.writeCalendar(date)
    //        dest.writeValue(id)
    //    }
    //
    //    override fun describeContents() = 0
    //
    //    companion object {
    //        val CREATOR = createParcel {
    //            Expense(null, it.readDouble(), it.readParcelable(Category.CREATOR), it.readString(), it.readCalendar(), it.readValue(null) as Long?)
    //        }
    //
    //        fun getExpense(intent: Intent): Expense {
    //            return intent.extras.getParcelableExtra()
    //        }
    //    }

}