package com.onlyi.and.ai.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun signPlaced( view:View){

        //Convert view to button
        val placedOnButton = view as Button

        Log.d("button", "sign " + "X" + " placed on: "+placedOnButton.id.toString())
    }
}
