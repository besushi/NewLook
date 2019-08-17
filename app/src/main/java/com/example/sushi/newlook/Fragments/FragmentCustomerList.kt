package com.example.sushi.newlook.Fragments

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.backendless.Backendless
import com.backendless.async.callback.AsyncCallback
import com.backendless.exceptions.BackendlessFault
import com.backendless.persistence.DataQueryBuilder
import com.example.sushi.newlook.Adapters.CustomerAdapter
import com.example.sushi.newlook.R
import com.examples.newlook.data.CustomerItem
import kotlinx.android.synthetic.main.dialog_add_customer.view.*
import kotlinx.android.synthetic.main.fragment_customer_list.*
import java.util.*


class FragmentCustomerList : android.support.v4.app.Fragment() {


    var CustomerRecycleview: RecyclerView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_customer_list, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setPointer(view)
    }


    private fun setPointer(view: View) {


        CustomerRecycleview = view.findViewById(R.id.CustomerRecycleView) as RecyclerView
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        CustomerRecycleview!!.layoutManager = layoutManager

        GetDataFromBackeEndLess()
        AddNewCustomer()

    }


    private fun AddNewCustomer() {

                //FAB oped dialog add new customer
                FB_Add_Customer.setOnClickListener {

                    val dialogview = layoutInflater.inflate(R.layout.dialog_add_customer, null)


                    val dialogbuilder = AlertDialog.Builder(view?.context)
                        .setView(dialogview)


                    val alertdialog = dialogbuilder.show()

                    val c = Calendar.getInstance()
                    val day = c.get(Calendar.DAY_OF_MONTH)
                    val month = c.get(Calendar.MONTH) + 1
                    val year = c.get(Calendar.YEAR)
                    Log.e("Calendar" ,"$day $month $year")
                    dialogview.TextInput_BirthDay.editText?.setText("$day/"+month+"/$year")


                    dialogview.TextInput_BirthDay.editText?.setOnClickListener {

                        val dpd =
                            DatePickerDialog(context!!, DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                                dialogview.TextInput_BirthDay.editText?.setText("$dayOfMonth/"+(monthOfYear+1)+"/$year")
                            }, year, month, day)

                        //show datepicker
                dpd.show()
            }

            //BUTTON create the customer     
            dialogview.BTN_CreateNew_Customer.setOnClickListener {
                var firstname = dialogview.TextInput_FirstName.editText?.text.toString()
                var lastname = dialogview.TextInput_LastName.editText?.text.toString()
                var phonenumb = dialogview.TextInput_PhoneNumber.editText?.text.toString()
                var birthday = dialogview.TextInput_BirthDay.editText?.text.toString()
                var discription = dialogview.TextInput_MoreInfo.editText?.text.toString()
                var index = indexgenerator()

                val newCustomerItem = CustomerItem()
                newCustomerItem.firstName = firstname
                newCustomerItem.lastName = lastname
                newCustomerItem.phone = phonenumb
                newCustomerItem.birthday = birthday
                newCustomerItem.discription = discription

                Backendless.Persistence.save<CustomerItem>(newCustomerItem, object : AsyncCallback<CustomerItem> {

                    override fun handleResponse(response: CustomerItem?) {
                        GetDataFromBackeEndLess()
                        alertdialog.dismiss()
                    }                                          

                    override fun handleFault(fault: BackendlessFault?) {
                        Toast.makeText(activity, "mmm.. what goin on?", Toast.LENGTH_SHORT).show()
                    }
                })
            }


            //BUTTON cancel dialog add new customer
            dialogview.BTN_CancelDialog.setOnClickListener {
                alertdialog.dismiss()
            }

        }
    }

    private fun indexgenerator() {

    }




    private fun GetDataFromBackeEndLess() {
        var query = DataQueryBuilder.create()
        query.setPageSize(100).setOffset(0)
        Backendless.Data.of(CustomerItem::class.java).find(query, object : AsyncCallback<List<CustomerItem>> {
            override fun handleResponse(response: List<CustomerItem>?) {
                var allcustomers = response!!
                Log.e("Customers count",response.count().toString())
                val adapter = CustomerAdapter(allcustomers)
                CustomerRecycleview?.adapter = adapter

            }

            override fun handleFault(fault: BackendlessFault?) {
                Toast.makeText(activity, "something went wrong", Toast.LENGTH_SHORT).show()
            }

        })
    }

}








