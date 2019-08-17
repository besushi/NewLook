package com.example.sushi.newlook.Activitys

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import com.example.sushi.newlook.Fragments.FragmentCalendarList
import com.example.sushi.newlook.Fragments.FragmentCustomerList
import com.example.sushi.newlook.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setPointer()
    }

    private fun setPointer() {
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

        // Set up the ViewPager with the sections adapter.
        container.adapter = mSectionsPagerAdapter

        container.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(container))
    }


    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment? {
            when (position) {
                0 -> { return FragmentCalendarList() }

                1 -> { return FragmentCustomerList() }
            }
            return null
        }
        override fun getCount(): Int {
            return 2
        }


        override fun getPageTitle(position: Int): CharSequence? {
            when (position) {
                0 -> { return "Calendar" }

                1 -> { return "Customers" }
            }
            return null
        }
    }
    }


