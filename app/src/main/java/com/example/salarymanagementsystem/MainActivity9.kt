package com.example.salarymanagementsystem

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity9 : AppCompatActivity() {
    lateinit var name: EditText
    lateinit var pass: EditText
    lateinit var save: Button
    lateinit var clear: Button
    lateinit var get: Button
    lateinit var sharedPreferences: SharedPreferences
    val Name = "nameKey"
    val Pass = "passKey"
    var is_login = "login";
    val myFile = "myProfile"

    //Date picker

    lateinit var btnDatePicker: Button
    lateinit var btnTimePicker: Button
    lateinit var txtDate: EditText
    lateinit var txtTime: EditText
    private var mYear:Int = 0
    private var mMonth:Int = 0
    private var mDay:Int = 0
    private var mHour:Int = 0
    private var mMinute:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main9)

        name = findViewById(R.id.etName)
        pass = findViewById(R.id.etEmail)
        save = findViewById(R.id.btnsave)
        clear = findViewById(R.id.btnEdit)
        get = findViewById(R.id.btnGet)



        sharedPreferences = getSharedPreferences(myFile , Context.MODE_PRIVATE)
        name.setText(sharedPreferences.getString(Name,""))
        pass.setText(sharedPreferences.getString(Pass , "" ))

        val is_loggedin = sharedPreferences.getString(is_login , "" )
        if(is_loggedin == "true"){

            val vg: ViewGroup? = findViewById(R.id.custom_toast)
            val inflater = layoutInflater
            val layout: View = inflater.inflate(R.layout.custom_toast, vg)
            val tv = layout.findViewById<TextView>(R.id.txtVw)
            tv.text = "already logged in"
            val toast = Toast(applicationContext)
            toast.setGravity(Gravity.BOTTOM, 0, 400)
            toast.setView(layout)
            toast.duration = Toast.LENGTH_SHORT
            toast.show()

            var int = Intent(this,MainActivity10::class.java);
            startActivity(int);
        }



        //Date picker
        btnDatePicker = findViewById(R.id.btn1)
        txtDate = findViewById(R.id.et1)


        btnDatePicker.setOnClickListener {
            val c = Calendar.getInstance()
            mYear = c[Calendar.YEAR]
            mMonth = c[Calendar.MONTH]
            mDay = c[Calendar.DAY_OF_MONTH]

            val datePickerDialog = DatePickerDialog(
                this ,
                {view , year , monthOfYear , dayOfMonth
                    ->
                    txtDate.setText(
                        dayOfMonth.toString() + "-" +
                                (monthOfYear + 1) + "-" + year
                    )
                },
                mYear , mMonth , mDay
            )
            datePickerDialog.show()
        }
    }

    fun save(v: View){
        val n = name.text.toString()
        val e = pass.text.toString()
        val l = "false";
        val editor = sharedPreferences.edit()
        editor.putString(Name , n)
        editor.putString(Pass , e)
        editor.putString(is_login, l)

        editor.apply()

        val vg: ViewGroup? = findViewById(R.id.custom_toast)
        val inflater = layoutInflater
        val layout: View = inflater.inflate(R.layout.custom_toast, vg)
        val tv = layout.findViewById<TextView>(R.id.txtVw)
        tv.text = "SignUp successfull"
        val toast = Toast(applicationContext)
        toast.setGravity(Gravity.BOTTOM, 0, 400)
        toast.setView(layout)
        toast.duration = Toast.LENGTH_SHORT
        toast.show()


//        login
        var int = Intent(this,MainActivity8::class.java);
        startActivity(int);

    }

    fun clear(v: View){
        name.setText("");
        pass.setText("");
    }

    fun get(v: View){
        sharedPreferences = getSharedPreferences(myFile , Context.MODE_PRIVATE)
        name.setText(sharedPreferences.getString(Name,""))
        pass.setText(sharedPreferences.getString(Pass , "" ))
    }
}