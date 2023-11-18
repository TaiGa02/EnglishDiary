package com.example.englishdiary

import CalenderAdapter
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.realm.Realm
import io.realm.kotlin.where
import java.time.LocalDate
import java.time.YearMonth
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.ArrayList
import java.util.Locale

class Calender : AppCompatActivity(), CalenderAdapter.OnItemListener {
    private lateinit var monthYearText: TextView
    private lateinit var calendarRecyclerView: RecyclerView
    private lateinit var selectedDate: LocalDate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calender)
        initWidgets()
        selectedDate = LocalDate.now(ZoneId.systemDefault())
        setMonthView()
    }

    private fun initWidgets() {
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView)
        monthYearText = findViewById(R.id.MonYear)
    }

    private fun setMonthView() {
        monthYearText.text = monthYearFromDate(selectedDate)
        val daysInMonth = daysInMonthArray(selectedDate)

        val calendarAdapter = CalenderAdapter(daysInMonth, this,selectedDate)
        val layoutManager = GridLayoutManager(applicationContext, 7)
        calendarRecyclerView.layoutManager = layoutManager
        calendarRecyclerView.adapter = calendarAdapter
    }

    private fun daysInMonthArray(date: LocalDate): ArrayList<String> {
        val daysInMonthArray = ArrayList<String>()
        val yearMonth = YearMonth.from(date)
        val daysInMonth = yearMonth.lengthOfMonth()
        val firstOfMonth = selectedDate.withDayOfMonth(1)
        val dayOfWeek = firstOfMonth.dayOfWeek.value

        for (i in 1..42) {
            if (i <= dayOfWeek || i > daysInMonth + dayOfWeek) {
                daysInMonthArray.add("")
            } else {
                daysInMonthArray.add((i - dayOfWeek).toString())
            }
        }
        return daysInMonthArray
    }

    private fun monthYearFromDate(date: LocalDate): String {
        val formatter = DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH)
        return date.format(formatter)
    }

    fun prevMonth(view: View) {
        selectedDate = selectedDate.minusMonths(1)
        setMonthView()
    }

    fun nextMonth(view: View) {
        selectedDate = selectedDate.plusMonths(1)
        setMonthView()
    }

    override fun onItemClick(position: Int, dayText: String) {
        if (dayText != "") {
            val selectedDate = "${selectedDate.year}-${String.format("%02d", selectedDate.monthValue)}-$dayText"
            val intent = Intent(this@Calender, ViewDiary::class.java)
            intent.putExtra("selectedDate", selectedDate)

            val realm = Realm.getDefaultInstance()
            val diaryData = realm.where<MyModel>().equalTo("date", selectedDate).findFirst()
            realm.close()

            if (diaryData != null) {
                startActivity(intent)
            } else {
                Toast.makeText(this, "No diary entry found for this date", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
