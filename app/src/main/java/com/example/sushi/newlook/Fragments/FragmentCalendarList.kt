package com.example.sushi.newlook.Fragments

import android.app.Fragment
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sushi.newlook.Adapters.CalendarListAdapter
import com.example.sushi.newlook.DataClass.CalendarDataSupplier
import com.example.sushi.newlook.R



class FragmentCalendarList: Fragment() {




    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {



        val rootView = inflater.inflate(R.layout.fragment_calendar_list, container, false)

        val CalendarRecycleView = rootView.findViewById(R.id.CalendarRecycleView) as RecyclerView // Add thi
        val layoutManager =  GridLayoutManager(activity,3)
        CalendarRecycleView.layoutManager = layoutManager

        val adapter = CalendarListAdapter(CalendarDataSupplier.Calendars)
        CalendarRecycleView.adapter = adapter

        return rootView
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        


}
}
