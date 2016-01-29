package com.morcinek.xpense.data.note

import com.orm.SugarRecord

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class Note(val text: String = "", var occurrence: Int = 0) : SugarRecord(), Comparable<Note> {

    override fun compareTo(other: Note) = occurrence.compareTo(other.occurrence)
}