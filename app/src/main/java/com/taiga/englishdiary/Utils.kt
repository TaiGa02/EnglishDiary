// Utils.kt
package com.taiga.englishdiary

import android.content.Context
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

object Utils {

    // 現在の日付を "dd MMMM" のフォーマットで取得する
    fun getDate(context: Context): String {
        val zoneId = ZoneId.systemDefault()
        val now = ZonedDateTime.now(zoneId)
        val df = DateTimeFormatter.ofPattern("dd MMMM", Locale.ENGLISH)
        return df.format(now)
    }

    // 指定されたフォーマットで現在の日付を取得する
    fun getDateWithFormat(format: String, context: Context): String {
        val zoneId = ZoneId.systemDefault()
        val now = ZonedDateTime.now(zoneId)
        val df = DateTimeFormatter.ofPattern(format, Locale.ENGLISH)
        return df.format(now)
    }
}
