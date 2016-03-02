package com.morcinek.xpense.data.category

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class ColorManager {

    val colors: List<Int>

    init {
        colors = listOf(
                0xFFff0000, 0xFFffc0cb, 0xFF008080, 0xFF0000ff, 0xFF800000,
                0xFF40e0d0, 0xFFcccccc, 0xFFffd700, 0xFF00ffff, 0xFFff7373,
                0xFFc0c0c0, 0xFF333333, 0xFF00ff00, 0xFFb0e0e6, 0xFF468499,
                0xFFd3ffce, 0xFF660066, 0xFFffff00, 0xFF003366, 0xFF800080,
                0xFFffa500, 0xFF666666, 0xFFf6546a, 0xFF088da5, 0xFF20b2aa,
                0xFF7fffd4, 0xFF66cdaa, 0xFFccff00, 0xFF00ced1, 0xFF990000,
                0xFF008000, 0xFFdaa520, 0xFFff4040, 0xFF191970, 0xFF31698a,
                0xFF8b0000, 0xFF0e2f44, 0xFF8a2be2, 0xFF0099cc, 0xFF000080,
                0xFF794044, 0xFF3399ff
        ).map { it.toInt() }
    }
}