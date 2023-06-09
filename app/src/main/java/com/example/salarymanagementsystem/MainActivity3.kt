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
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity3 : AppCompatActivity() {
    val fileName = "employeeDetails"
    lateinit var sharedPreference: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        sharedPreference=getSharedPreferences(fileName, Context.MODE_PRIVATE)

        val addEmpBtn = findViewById<Button>(R.id.addEmpBtn)
        val backToMainMenuBtn = findViewById<Button>(R.id.backToMainMenuBtn)
        val displayEmpBtn = findViewById<Button>(R.id.displayEmpBtn)
        val editEmpBtn = findViewById<Button>(R.id.editEmpBtn)
        val deleteEmpBtn = findViewById<Button>(R.id.deleteEmpBtn)

        addEmpBtn.setOnClickListener {
            val intent = Intent(this, MainActivity4::class.java)
            startActivity(intent)
        }

        backToMainMenuBtn.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

        displayEmpBtn.setOnClickListener {
            val intent = Intent(this, MainActivity6::class.java)
            startActivity(intent)
        }

        editEmpBtn.setOnClickListener {
            val intent = Intent(this, MainActivity4::class.java)
            intent.putExtra("isEditable", true)
            startActivity(intent)
        }

        deleteEmpBtn.setOnClickListener {
            val delDetails = MainActivity4()
            delDetails.delEmployeeDetails(sharedPreference)

            val vg: ViewGroup? = findViewById(R.id.custom_toast)
            val inflater = layoutInflater
            val layout: View = inflater.inflate(R.layout.custom_toast, vg)
            val tv = layout.findViewById<TextView>(R.id.txtVw)
            tv.text = "Success"
            val toast = Toast(applicationContext)
            toast.setGravity(Gravity.BOTTOM, 0, 400)
            toast.setView(layout)
            toast.duration = Toast.LENGTH_SHORT
            toast.show()
        }

    }


}