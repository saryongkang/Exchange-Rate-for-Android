package com.saryong.example.util.extention

import org.threeten.bp.*

fun LocalDateTime.atSGT(): ZonedDateTime =
  atZone(ZoneId.of("SGT", ZoneId.SHORT_IDS))

fun LocalDateTime.atUTC(): ZonedDateTime =
  atZone(ZoneId.of("UTC", ZoneId.SHORT_IDS))

fun LocalDateTime.atCurrentZone(): ZonedDateTime =
  ZoneId.systemDefault()?.let { defaultZone ->
    atZone(defaultZone)
  } ?: atUTC()