package com.example.salarymanagementsystem

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity10 : AppCompatActivity() {
    lateinit var sharedPreferences: SharedPreferences
    val myFile = "myProfile"
    var is_login = "login"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main10)

        sharedPreferences = getSharedPreferences(myFile , Context.MODE_PRIVATE)

        var btn = findViewById<Button>(R.id.start)
        var signout = findViewById<Button>(R.id.signOut)

        btn.setOnClickListener{
//login
            var int = Intent(this,MainActivity8::class.java);
            startActivity(int);
        }

        signout.setOnClickListener {

            val editor = sharedPreferences.edit()
            editor.putString(is_login , "false")
            editor.apply()

            val vg: ViewGroup? = findViewById(R.id.custom_toast)
            val inflater = layoutInflater
            val layout: View = inflater.inflate(R.layout.custom_toast, vg)
            val tv = layout.findViewById<TextView>(R.id.txtVw)
            tv.text = "Logout Successfull"
            val toast = Toast(applicationContext)
            toast.setGravity(Gravity.BOTTOM, 0, 400)
            toast.setView(layout)
            toast.duration = Toast.LENGTH_SHORT
            toast.show()


            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}