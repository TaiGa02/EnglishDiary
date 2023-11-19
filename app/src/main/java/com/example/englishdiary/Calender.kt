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

        //レイアウトないのウィジェットの初期化を行います
        initWidgets()

        //現在の日付を取得し、月のビューをセット
        selectedDate = LocalDate.now(ZoneId.systemDefault())
        setMonthView()
    }

    // ウィジェットの初期化を行うメソッド
    private fun initWidgets() {
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView)
        monthYearText = findViewById(R.id.MonYear)
    }

    // 月のビューを設定するメソッド
    private fun setMonthView() {
        monthYearText.text = monthYearFromDate(selectedDate)
        val daysInMonth = daysInMonthArray(selectedDate)

        val calendarAdapter = CalenderAdapter(daysInMonth, this,selectedDate)
        val layoutManager = GridLayoutManager(applicationContext, 7)
        calendarRecyclerView.layoutManager = layoutManager
        calendarRecyclerView.adapter = calendarAdapter
    }

    // 月の日数を配列にして返すメソッド
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

    // 日付から月と年を取得して返すメソッド
    private fun monthYearFromDate(date: LocalDate): String {
        val formatter = DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH)
        return date.format(formatter)
    }

    // 前の月への移動を行うメソッド
    fun prevMonth(view: View) {
        selectedDate = selectedDate.minusMonths(1)
        setMonthView()
    }

    // 次の月への移動を行うメソッド
    fun nextMonth(view: View) {
        selectedDate = selectedDate.plusMonths(1)
        setMonthView()
    }

    // カレンダーの日付をクリックしたときの処理を行うメソッド
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
