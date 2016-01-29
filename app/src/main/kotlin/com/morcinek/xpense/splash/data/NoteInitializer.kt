package com.morcinek.xpense.splash.data

import com.morcinek.xpense.data.category.Category
import com.morcinek.xpense.data.category.CategoryManager
import com.morcinek.xpense.data.note.Note
import com.orm.SugarRecord

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class NoteInitializer() : Initializer {

    override fun initialize() {
        var notes = listOf(
                "Dinner", "Breakfast", "Lunch", "Rent", "Fuel", "Phone", "Cigarettes",
                "Taxi", "Bus ticket", "TV", "Book", "Coffee"
        )
        notes.forEach {
            SugarRecord.saveInTx(Note(it, 1))
        }
    }
}