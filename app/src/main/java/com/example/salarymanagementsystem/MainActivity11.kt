package com.example.salarymanagementsystem

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.Toast

class MainActivity11 : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main11)

        Handler(Looper.getMainLooper()).postDelayed({
            var int = Intent(this,MainActivity::class.java);
            startActivity(int);
        },2000);


    }
}