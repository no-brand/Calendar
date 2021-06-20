package com.nobrand.calendar.ui.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.nobrand.calendar.adapter.CalendarAdapter
import com.nobrand.calendar.databinding.FragmentCalendarBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CalendarFragment : Fragment() {

    private lateinit var binding: FragmentCalendarBinding

    private val viewModel: CalendarViewModel by viewModels()

    private val adapter: CalendarAdapter = CalendarAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCalendarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.calendar.adapter = adapter
        viewModel.days.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
        viewModel.updateCalendar()
    }

}