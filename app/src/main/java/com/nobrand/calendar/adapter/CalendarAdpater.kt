package com.nobrand.calendar.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nobrand.calendar.data.Day
import com.nobrand.calendar.databinding.ItemDayBinding


class CalendarAdapter : ListAdapter<Day, RecyclerView.ViewHolder>(DayDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return DayViewHolder(ItemDayBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val day = getItem(position)
        (holder as DayViewHolder).bind(position, day)
    }

    class DayViewHolder(val binding: ItemDayBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int, item: Day) {
            binding.apply {
                this.day = item
                this.label.text = item.day.toString()

                val color = when (position % 7) {
                    0 -> Color.RED
                    6 -> Color.BLUE
                    else -> Color.BLACK
                }
                this.label.setTextColor(color)
            }
        }
    }
}

private class DayDiffCallback : DiffUtil.ItemCallback<Day>() {
    /* 같은 대상 확인 */
    override fun areItemsTheSame(oldItem: Day, newItem: Day): Boolean {
        return oldItem == newItem
    }

    /* 값에 변화가 생겼는지 확인 */
    override fun areContentsTheSame(oldItem: Day, newItem: Day): Boolean {
        return oldItem == newItem
    }
}