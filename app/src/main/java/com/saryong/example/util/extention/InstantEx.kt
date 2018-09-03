package com.saryong.example.util.extention

import org.threeten.bp.Instant
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime

fun Instant.atSGT(): ZonedDateTime =
  atZone(ZoneId.of("SGT", ZoneId.SHORT_IDS))

fun Instant.atUTC(): ZonedDateTime =
  atZone(ZoneId.of("UTC", ZoneId.SHORT_IDS))

fun Instant.atCurrentZone(): ZonedDateTime =
  ZoneId.systemDefault()?.let { defaultZone ->
    atZone(defaultZone)
  } ?: atUTC()