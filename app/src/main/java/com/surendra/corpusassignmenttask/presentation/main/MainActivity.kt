package com.surendra.corpusassignmenttask.presentation.main


import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.surendra.corpusassignmenttask.R
import com.surendra.corpusassignmenttask.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
  //  private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

      //  setupViewPager()
    }
//
//    private fun setupViewPager() {
//        val adapter = ViewPagerAdapter(this)
//        binding.viewPager.adapter = adapter
//
//        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
//            tab.text = when (position) {
//                0 -> getString(R.string.tab_home)
//                1 -> getString(R.string.tab_about)
//                else -> ""
//            }
//        }.attach()
//    }
}