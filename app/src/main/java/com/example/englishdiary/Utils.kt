package com.example.englishdiary

import android.content.Context
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

object Utils {

    fun getDate(): String {
        val now = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"))
        val df = DateTimeFormatter.ofPattern("dd MMMM")
        val fdate = df.format(now)
        return fdate
    }
}