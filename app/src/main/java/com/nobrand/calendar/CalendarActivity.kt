package com.nobrand.calendar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.nobrand.calendar.databinding.ActivityCalendarBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CalendarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCalendarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCalendarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        this.onButtonNavigation()
    }

    /*
    * NavigationUI 제공하는 함수를 이용해서 Bottom Navigation 관련된 UI 를 처리합니다.
    * 1. androidx.navigation.fragment.NavHostFragment 가져오고
    * 2. androidx.navigation.NavController 가져오고
    * 3. androidx.navigation.ui.BottomNavigationView.setupWithNavController 실행해서 연결
    *
    * @onCreate
    * https://developer.android.com/guide/navigation/navigation-ui#bottom_navigation
    * */
    private fun onButtonNavigation() {
        val bottomNavigation = binding.bottomNavigation
        val navigationController = findNavController(R.id.navigation_host)

        bottomNavigation.setupWithNavController(navigationController)
    }
}
