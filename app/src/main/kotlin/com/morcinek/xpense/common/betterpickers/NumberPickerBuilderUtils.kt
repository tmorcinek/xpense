package com.morcinek.xpense.common.betterpickers

import com.codetroopers.betterpickers.numberpicker.NumberPickerBuilder
import com.morcinek.xpense.common.types.isInteger

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
fun NumberPickerBuilder.setCurrentNumberAsInteger(number: Double) {
    if (number.isInteger()) {
        setCurrentNumber(number.toInt())
    } else {
        setCurrentNumber(number)
    }
}