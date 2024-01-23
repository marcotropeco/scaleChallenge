package com.example.scale1.extensions

import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

fun Long.convertMillisToLocalDate(): LocalDate {
    val instant = Instant.ofEpochMilli(this)
    return instant.atZone(ZoneId.of("UTC")).toLocalDate()
}