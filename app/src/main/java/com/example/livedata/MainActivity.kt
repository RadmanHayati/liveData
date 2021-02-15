package com.example.livedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // the standard form used in the google's doc
        val liveData: MutableLiveData<String> by lazy { MutableLiveData<String>() }
        // it could be like this
        // var liveData:MutableLiveData<String>=MutableLiveData()
        // liveData.value = "hello" you can assign value to it by this way
        // - when the element which changes the value is from the main thread
        // - otherwise you should use the following line:
        // post value can be used when value is modified from main or background thread
        liveData.postValue("hello world")
        // we need an observer
        //it runs whenever the object changes
        liveData.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }) // this line toasts the new string
        /*
          it could look like this:
      liveData.observe(this,object :Observer<String>{
          override fun onChanged(t: String?) {
         ("Not yet implemented")
          }
      }
      )
      */

        var incrementCounter = 0
        var btnhi=findViewById<Button>(R.id.btnHelloWorld)
        btnhi.setOnClickListener(){
            liveData.postValue(incrementCounter++.toString())
        }
        liveData.observe(this, Observer {

            btnhi.text="hello $it"
        })
        //we made a variable with type of live data now whenever it changes we can do an operation
        //-so we put the incrementCounter in it and whenever incrementCounter changes the value of
        //- that variable changes too.
        // now that we find out when it changes and the changes are the number of our variable
        // we can do as many operations on it as we want in this case we have two observers
        // one of them toasts the changed value which is incrementCounter++ and the other one
        //-sets it as the text attribute of our button (btnHelloWorld)q
    }
}