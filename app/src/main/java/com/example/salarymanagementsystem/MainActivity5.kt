package com.example.salarymanagementsystem

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.*

class MainActivity5 : AppCompatActivity() {
    lateinit var optionSelectedForMonth: String
    lateinit var salaryMonth: Spinner
    lateinit var leaveWithoutPayDays: EditText
    lateinit var sharedPreference: SharedPreferences
    val fileName = "employeeSalaryGen"
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main5)

        salaryMonth =  findViewById(R.id.salaryMonth)
        leaveWithoutPayDays = findViewById(R.id.leaveWithoutPayDays)
        val salaryGen = findViewById<Button>(R.id.salaryGen)

        sharedPreference=getSharedPreferences(fileName, Context.MODE_PRIVATE)

        monthSpinner()

        salaryGen.setOnClickListener {
                saveDetails()
                val vg: ViewGroup? = findViewById(R.id.custom_toast)
                val inflater = layoutInflater
                val layout: View = inflater.inflate(R.layout.custom_toast, vg)
                val tv = layout.findViewById<TextView>(R.id.txtVw)
                tv.text = "Salary Generated Successfully"
                val toast = Toast(applicationContext)
                toast.setGravity(Gravity.BOTTOM, 0, 400)
                toast.setView(layout)
                toast.duration = Toast.LENGTH_SHORT
                toast.show()

        }

        val backToMainMenuBtn = findViewById<Button>(R.id.backToMainMenuBtn)
        backToMainMenuBtn.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
    }

    private fun monthSpinner() {
        val month = arrayOf("January", "Feburary", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December")
        optionSelectedForMonth = ""
        if (salaryMonth!=null) {
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, month)
            salaryMonth.adapter = adapter

            salaryMonth.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    optionSelectedForMonth = month[position]
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
            }
        }
    }

    fun saveDetails() {
        val salaryMonth = optionSelectedForMonth

        var leaveWithoutPayDay = leaveWithoutPayDays.text.toString()
        val edit=sharedPreference.edit()

        if (leaveWithoutPayDays.text.isEmpty()) {
            leaveWithoutPayDay="0"
            edit.putString("LeaveWithoutPayDays", leaveWithoutPayDay)
        }

        edit.putString("SalaryMonth", salaryMonth)
        edit.putString("LeaveWithoutPayDays", leaveWithoutPayDay)
        edit.apply()
    }
}