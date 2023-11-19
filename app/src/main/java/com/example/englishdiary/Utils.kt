// Utils.kt
package com.example.englishdiary

import android.content.Context
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

object Utils {

    fun getDate(context: Context): String {
        val zoneId = ZoneId.systemDefault()
        val now = ZonedDateTime.now(zoneId)
        val df = DateTimeFormatter.ofPattern("dd MMMM", Locale.ENGLISH)
        return df.format(now)
    }

    fun getDateWithFormat(format: String, context: Context): String {
        val zoneId = ZoneId.systemDefault()
        val now = ZonedDateTime.now(zoneId)
        val df = DateTimeFormatter.ofPattern(format, Locale.ENGLISH)
        return df.format(now)
    }
}
