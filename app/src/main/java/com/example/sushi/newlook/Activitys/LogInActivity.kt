package com.example.sushi.newlook.Activitys

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.backendless.Backendless
import com.backendless.BackendlessUser
import com.backendless.async.callback.AsyncCallback
import com.backendless.exceptions.BackendlessFault
import com.example.sushi.newlook.R
import kotlinx.android.synthetic.main.activity_log_in.*


class LogInActivity : AppCompatActivity() {

    var APPLICATION_ID = "7ADBB4CC-2828-5411-FFDA-D0EF158F3400"
    var API_KEY = "55C289BA-F757-B233-FFA5-E9A213B45400"


    //todo set permitions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
        setPointer()
    }


    private fun setPointer() {
        var context : Context = this

        //backendlessconnection
        Backendless.initApp( context,APPLICATION_ID,API_KEY)

        //TODO check if user have calendar on his name if do open it if not open calendarlist
        ALI_BTN_LogIN.setOnClickListener {
            var Username = TextInput_UserName.editText?.text.toString()
            var Password  = TextInput_pass.editText?.text.toString()

         Backendless.UserService.login(Username,Password,object: AsyncCallback<BackendlessUser>{

             override fun handleFault(fault: BackendlessFault?) {
               Toast.makeText(context,fault?.message ,Toast.LENGTH_SHORT ).show()
             }

             override fun handleResponse(response: BackendlessUser?) {

                 
                 val intent = Intent(context, MainActivity::class.java)
                 // To pass any data to next activity
                 intent.putExtra("keyIdentifier", "")
                // start your next activity
                 startActivity(intent)
             }

         })




        }
    }



}
