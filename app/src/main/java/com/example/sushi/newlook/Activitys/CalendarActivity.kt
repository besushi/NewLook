package com.example.sushi.newlook.Activitys

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.sushi.newlook.Adapters.AppointmentListAdapter
import com.example.sushi.newlook.DataClass.AppointmentDataSupplier
import com.example.sushi.newlook.R
import kotlinx.android.synthetic.main.activity_calendar.*

class CalendarActivity :  AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)
        getData()
        setPointer()
    }

    private val appointmentListAdapter = AppointmentListAdapter(AppointmentDataSupplier.AppointmentData)

    private fun setPointer() {

        appointmentRecyclerView.layoutManager = LinearLayoutManager(this)
        appointmentRecyclerView.adapter = AppointmentListAdapter(AppointmentDataSupplier.AppointmentData)




    }

    private fun getData() {
        var name= intent.getStringExtra("CurrentCalendarName")
        CurrentCalendarTitle.text = name
    }
}