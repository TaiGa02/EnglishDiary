// Utils.kt
package com.example.englishdiary

import android.content.Context
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

object Utils {

    fun getDate(context: Context): String {
        val zoneId = ZoneId.systemDefault()
        val now = ZonedDateTime.now(zoneId)
        val df = DateTimeFormatter.ofPattern("dd MMMM")
        return df.format(now)
    }
}
