package com.example.sushi.newlook.Adapters

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sushi.newlook.Activitys.CalendarActivity
import com.example.sushi.newlook.DataClass.CalendarData
import com.example.sushi.newlook.R
import kotlinx.android.synthetic.main.itemcard_calendar.view.*

class CalendarListAdapter(var calendars: List<CalendarData>): RecyclerView.Adapter<CalendarListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
      val view = LayoutInflater.from(parent.context).inflate(R.layout.itemcard_calendar, parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return calendars.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val calendar = calendars[position]
        holder.setData(calendar,position)
    }


    inner class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {

        var currentCalendar : CalendarData? = null
        var currentPosition : Int = 0

        init {
            itemview.setOnClickListener {
             var intent = Intent(itemview.context,CalendarActivity::class.java)
                 intent.putExtra("CurrentCalendarName", currentCalendar!!.title)
                 itemview.context.startActivity(intent)
            }


        }




       fun setData(calendar: CalendarData,position: Int){
           itemView.carditem_calemdarTV.text = calendar.title

           this.currentCalendar= calendar
           this.currentPosition = position

       }
    }

}


