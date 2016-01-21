package com.morcinek.xpense.common.formatters

import com.morcinek.xpense.BuildConfig
import java.text.SimpleDateFormat

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class ShortDateFormatter : SimpleDateFormat(BuildConfig.SHORT_DATE_FORMAT)