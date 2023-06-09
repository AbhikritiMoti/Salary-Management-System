package com.example.salarymanagementsystem

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity2 : AppCompatActivity() {
    lateinit var bottomNavigationView: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val quick = findViewById<Button>(R.id.quick)
        quick.setOnClickListener {
            val myintent = Intent(this, MainActivity13::class.java)
            startActivity(myintent)
        }

        bottomNavigationView = findViewById(R.id.bottomNav)
        title = "Home"

        bottomNavigation(bottomNavigationView)

        val empMasterBtn = findViewById<Button>(R.id.empMasterBtn)
        empMasterBtn.setOnClickListener {
            val intent = Intent(this, MainActivity3::class.java)
            startActivity(intent)
        }

        val salaryGenBtn = findViewById<Button>(R.id.salaryGenBtn)
        salaryGenBtn.setOnClickListener {
            val intent = Intent(this, MainActivity5::class.java)
            startActivity(intent)
        }

        val salaryDisplay = findViewById<Button>(R.id.salaryDisplay)
        salaryDisplay.setOnClickListener {
            val intent = Intent(this, MainActivity7::class.java)
            startActivity(intent)
        }

        val salaryExit = findViewById<Button>(R.id.salaryExit)
        salaryExit.setOnClickListener {
            exit()
        }
    }

    fun bottomNavigation(bottomNavigationView: BottomNavigationView) {
        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> {
                    val intent = Intent(this, MainActivity2::class.java)
                    startActivity(intent)
                    true
                }
                R.id.rate -> {
                    val intent = Intent(this, MainActivity12::class.java)
                    startActivity(intent)
                    true
                }
                R.id.git -> {
                    var url = "https://github.com/abhikritimoti/Salary-Management-System.git"
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.setDataAndType(Uri.parse(url), "text/plain")
                    val choose = Intent.createChooser(intent, "Share URL")
                    startActivity(choose)
                    true
                }

                R.id.share -> {
                    val sendIntent: Intent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, "https://github.com/abhikritimoti/Salary-Management-System.git")
                        type = "text/plain"
                    }

                    val shareIntent = Intent.createChooser(sendIntent, null)
                    startActivity(shareIntent)
                    true
                }
                R.id.exit -> {
                    exit()
                    true
                }
                else -> true

            }
        }
    }

    fun exit() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Exit Alert")
            .setMessage("Are you sure, you want to exit ?")
            .setCancelable(true)
            // .setMessage("this is alert")
            .setIcon(android.R.drawable.ic_dialog_alert)


        //performing positive action
        builder.setPositiveButton("Yes") { dialogInterface, which ->
            finishAffinity()
        }

        //performing negative action
        builder.setNegativeButton("No") { dialogInterface, which ->
            val vg: ViewGroup? = findViewById(R.id.custom_toast)
            val inflater = layoutInflater
            val layout: View = inflater.inflate(R.layout.custom_toast, vg)
            val tv = layout.findViewById<TextView>(R.id.txtVw)
            tv.text = "clicked No"
            val toast = Toast(applicationContext)
            toast.setGravity(Gravity.BOTTOM, 0, 400)
            toast.setView(layout)
            toast.duration = Toast.LENGTH_SHORT
            toast.show()
        }

        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()
    }
}