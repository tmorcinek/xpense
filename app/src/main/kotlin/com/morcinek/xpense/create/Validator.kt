package com.morcinek.xpense.create

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
interface Validator<T> {

    fun isValid(item: T): Boolean
}