package com.example.sushi.newlook.Adapters

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.support.design.widget.BottomSheetDialog
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import com.example.sushi.newlook.R
import com.examples.newlook.data.CustomerItem
import kotlinx.android.synthetic.main.botomsheet_customer.view.*
import kotlinx.android.synthetic.main.itemcard_customer.view.*


var itsET: Boolean = false
var mytext: String = ""



class CustomerAdapter (var customers:List<CustomerItem>): RecyclerView.Adapter<CustomerAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itemcard_customer, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return customers.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val customer = customers[position]
        holder.setDataToRecycleViewItems(customer, position)
    }


    inner class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {

        var currentCustomer: CustomerItem? = null
        var currentPosition: Int = 0

        init {
            itemview.setOnClickListener {
                val modelBottomSheet = LayoutInflater.from(it.context).inflate(R.layout.botomsheet_customer, null)

                val dialog = BottomSheetDialog(it.context)
                dialog.setCanceledOnTouchOutside(false)
                dialog.setContentView(modelBottomSheet)

                var listofET = listOf<EditText>(modelBottomSheet.BS_Name,modelBottomSheet.BS_Lastname, modelBottomSheet.BS_BirthDay,modelBottomSheet.BS_PhoneNumb,modelBottomSheet.BS_MoreInfo)
                val originalDrawable = modelBottomSheet.BS_MoreInfo.background

                modelBottomSheet.BS_Name.text = Editable.Factory.getInstance().newEditable(currentCustomer!!.firstName)
                modelBottomSheet.BS_Lastname.text = Editable.Factory.getInstance().newEditable(currentCustomer!!.lastName)
                modelBottomSheet.BS_BirthDay.text = Editable.Factory.getInstance().newEditable(currentCustomer!!.birthday)
                modelBottomSheet.BS_PhoneNumb.text = Editable.Factory.getInstance().newEditable(currentCustomer!!.phone)
                modelBottomSheet.BS_MoreInfo.text = Editable.Factory.getInstance().newEditable(currentCustomer!!.discription)
                ToTextview(listofET,modelBottomSheet.BS_EditCustomerCancel,modelBottomSheet.BS_EditCustomer)

                dialog.show()


                modelBottomSheet.BS_EditCustomerCancel.setOnClickListener {
                    when (itsET) {
                        true -> ToTextview(listofET,modelBottomSheet.BS_EditCustomerCancel,modelBottomSheet.BS_EditCustomer)
                        false -> ToEditText( listofET, originalDrawable,modelBottomSheet.BS_EditCustomerCancel,modelBottomSheet.BS_EditCustomer)
                    }
                    modelBottomSheet.BS_Name.text = Editable.Factory.getInstance().newEditable(currentCustomer!!.firstName)
                    modelBottomSheet.BS_Lastname.text = Editable.Factory.getInstance().newEditable(currentCustomer!!.lastName)
                    modelBottomSheet.BS_BirthDay.text = Editable.Factory.getInstance().newEditable(currentCustomer!!.birthday)
                    modelBottomSheet.BS_PhoneNumb.text = Editable.Factory.getInstance().newEditable(currentCustomer!!.phone)
                    modelBottomSheet.BS_MoreInfo.text = Editable.Factory.getInstance().newEditable(currentCustomer!!.discription)

                }
                
                modelBottomSheet.BS_EditCustomer.setOnClickListener {
                    ToTextview(listofET,modelBottomSheet.BS_EditCustomerCancel,modelBottomSheet.BS_EditCustomer)

//                    customers.get(currentPosition).renameAsync(modelBottomSheet.BS_Name.text.toString(),modelBottomSheet.BS_Lastname.text.toString(),modelBottomSheet.BS_BirthDay.text.toString(),
//                        modelBottomSheet.BS_PhoneNumb.text.toString(),modelBottomSheet.BS_MoreInfo.text.toString(),
//                        object : AsyncCallback<CustomerItem> {
//                            override fun handleResponse(response: CustomerItem) {
//                                notifyItemChanged(currentPosition)
//                            }
//
//                            override fun handleFault(fault: BackendlessFault) {
//                            }
//                        })

                }

                modelBottomSheet.BS_Cardview_Call.setOnClickListener {
                    CallToCurrentCustomer(it)

                }

                modelBottomSheet.BS_Cardview_Add_Appontment.setOnClickListener {
                         TODO("set APPONTMET ACTIVITY")
                }

            }


            //make the call to customer
            itemview.CustomerCard_call.setOnClickListener {
                CallToCurrentCustomer(itemview)
            }

        }

    

        //funs
        private fun AddAppointment() {
            //TODO ADD APPOINTMENT
        }


        private fun CallToCurrentCustomer(itemview: View) {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:" + currentCustomer!!.phone)
            itemview.context.startActivity(intent)
        }


        @SuppressLint("SetTextI18n")
        fun setDataToRecycleViewItems(customer: CustomerItem, position: Int) {
            itemView.carditem_CustomerName.text = customer.firstName +" "+ customer.lastName

            this.currentCustomer = customer
            this.currentPosition = position
        }
    }

    private fun ToEditText(X: List<EditText>, originalDrawable: Drawable,img: ImageView,imgCancel: ImageView) {
        X.forEach {
            it.isFocusable = true
            it.isCursorVisible = true
            it.isLongClickable = true
            it.isClickable = true
            it.isEnabled = true
            it.showSoftInputOnFocus = true
            it.isFocusableInTouchMode = true
            it.background = originalDrawable
        }
        img.setImageResource(R.drawable.ic_close)
        imgCancel.visibility = View.VISIBLE
        itsET = true
    }

    private fun ToTextview(X: List<EditText>,img: ImageView,imgCancel: ImageView) {
        X.forEach {

            it.isFocusable = false
            it.isCursorVisible = false
            it.isLongClickable = false
            it.isEnabled = false
            it.isClickable = false
            it.background = null

        }
        img.setImageResource(R.drawable.ic_edit)
        imgCancel.visibility = View.INVISIBLE
        itsET = false
    }

    



}

