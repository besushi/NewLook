package com.example.sushi.newlook.Adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.sushi.newlook.DataClass.AppointmentData
import com.example.sushi.newlook.R
import kotlinx.android.synthetic.main.itemcard_customer.view.*

class AppointmentListAdapter (var appointments : List<AppointmentData>): RecyclerView.Adapter<AppointmentListAdapter.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.itemcard_customer, parent, false)
            return ViewHolder(view)
        }


    override fun getItemCount(): Int {
         return appointments.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val appointment = appointments[position]
        holder.setData(appointment,position)
    }





    inner class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {

        var currentAppointment : AppointmentData? = null
        var currentPosition : Int = 0


        init {
            itemview.setOnClickListener {
                Toast.makeText(itemview.context,"yo",Toast.LENGTH_SHORT).show()
            }


        }


        fun setData(appointment: AppointmentData, position: Int) {
            itemView.carditem_CustomerName.text = appointment.title

            this.currentAppointment = appointment
            this.currentPosition = position

        }
    }
}