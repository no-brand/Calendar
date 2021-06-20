package com.nobrand.calendar.ui.calendar

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nobrand.calendar.data.Day
import java.util.*


class CalendarViewModel : ViewModel() {

    private val T = "OMG"

    val days: MutableLiveData<List<Day>> by lazy {
        MutableLiveData<List<Day>>()
    }

    fun updateCalendar() {
        var days: ArrayList<Day> = arrayListOf()

        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        /*
        * 달력 이번달 몇일까지?
        * */
        val currentMonthMax = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

        /*
        * 달력 지난달 흐릿하게 보여줄 몇 개의 날짜
        *   calendar.get(Calendar.DAY_OF_WEEK) : Calendar(1일) 이 한주의 몇 번째?
        *
        *   '21 JUN
        *    S M T W T F S
        *    - - 1 2 3 4 5
        *       (3)
        * */
        val previousMonthTail = calendar.get(Calendar.DAY_OF_WEEK) - 1

        days = addPreviousMonth(days, calendar.clone() as Calendar, previousMonthTail)
        days = addCurrentMonth(days, calendar.clone() as Calendar, currentMonthMax)

        calendar.set(Calendar.DAY_OF_MONTH, currentMonthMax)
        val nextMonthHead = 7 - calendar.get(Calendar.DAY_OF_WEEK)
        days = addNextMonth(days, calendar.clone() as Calendar, nextMonthHead)

        this.days.postValue(days)
    }

    private fun addPreviousMonth(days: ArrayList<Day>, calendar: Calendar, num: Int): ArrayList<Day> {
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1)
        val y = calendar.get(Calendar.YEAR)
        val m = calendar.get(Calendar.MONTH)
        val maxD = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        for (idx in 1..num) {
            val d = maxD - num + idx
            days.add(Day(y, m, d))
        }
        return days
    }

    private fun addCurrentMonth(days: ArrayList<Day>, calendar: Calendar, num: Int): ArrayList<Day> {
        val y = calendar.get(Calendar.YEAR)
        val m = calendar.get(Calendar.MONTH)
        for (idx in 1..num) {
            days.add(Day(y, m, idx))
        }
        return days
    }

    private fun addNextMonth(days: ArrayList<Day>, calendar: Calendar, num: Int): ArrayList<Day> {
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1)
        val y = calendar.get(Calendar.YEAR)
        val m = calendar.get(Calendar.MONTH)
        for (idx in 1..num) {
            days.add(Day(y, m, idx))
        }
        return days
    }
}