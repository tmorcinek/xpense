package com.morcinek.xpense.common.utils.betterpickers

import com.codetroopers.betterpickers.numberpicker.NumberPickerBuilder
import com.morcinek.xpense.common.utils.isInteger

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