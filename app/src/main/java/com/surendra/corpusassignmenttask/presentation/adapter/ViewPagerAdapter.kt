package com.surendra.corpusassignmenttask.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.surendra.corpusassignmenttask.presentation.fragments.home.HomeFragment
import com.surendra.corpusassignmenttask.presentation.fragments.about.AboutFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    
    override fun getItemCount(): Int = 2
    
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> AboutFragment()
            else -> HomeFragment()
        }
    }
}

