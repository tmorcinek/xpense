package com.morcinek.xpense.data.note

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class NoteManager {

    private val notes: MutableMap<String, Int> = hashMapOf()

    init {
        notes.putAll(listOf("Dinner", "Breakfast", "Lunch").map { it to 1 })
        notes.put("Black", 3)
        notes.put("Grain", 5)
        notes.put("Health", 2)
    }

    fun getNotes(): List<String> = notes.entries.sortedByDescending { it.value }.map { it.key }

    fun addNote(note: String) {
        if (!notes.containsKey(note)) {
            notes.put(note, 1)
        } else {
            notes.put(note, notes.get(note)!! + 1)
        }
    }
}