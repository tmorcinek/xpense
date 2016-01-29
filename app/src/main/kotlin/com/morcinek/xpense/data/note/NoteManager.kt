package com.morcinek.xpense.data.note

import com.orm.SugarRecord

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class NoteManager {

    private val notes: MutableList<Note> = SugarRecord.listAll(Note::class.java)

    fun getNotes(): List<String> = notes.sortedByDescending { it.occurrence }.map { it.text }

    fun addNote(text: String) {
        val foundNote = notes.find { text == it.text }
        if (foundNote != null) {
            foundNote.occurrence++
            foundNote.save()
        } else {
            val newNote = Note(text, 1)
            newNote.save()
            notes.add(newNote)
        }
    }
}