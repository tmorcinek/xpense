package com.morcinek.xpense.splash.data

import android.content.Context
import com.morcinek.xpense.R
import com.morcinek.xpense.data.category.Category
import com.morcinek.xpense.data.note.Note
import com.orm.SugarRecord

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class NoteInitializer(val context: Context) : Initializer {

    override fun initialize() {
        val notes = context.resources.getStringArray(R.array.notes).map { Note(it) }
        SugarRecord.saveInTx(notes)
    }
}