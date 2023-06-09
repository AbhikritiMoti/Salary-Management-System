package com.example.salarymanagementsystem

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlin.properties.Delegates

class MainActivity7 : AppCompatActivity() {
    lateinit var grossSalaryDisplay: TextView
    lateinit var providentFundDisplay: TextView
    lateinit var incomeTaxDisplay: TextView
    lateinit var netSalaryDisplay: TextView
    lateinit var sharedPreferences: SharedPreferences
    lateinit var basicSalaryDisplay: TextView
    lateinit var dearnessAllowanceDisplay: TextView
    lateinit var travellingAllowanceDisplay: TextView
    lateinit var houseRentalAllowanceDisplay: TextView
    lateinit var otherAllowanceDisplay: TextView
    lateinit var salaryDisplay: TextView
    lateinit var salaryMonth: String
    var noOfLeaveDays: Float=0f

    var providentFund: Float = 0f
    var incomeTax:Float=0f
    var grossSalary: Float= 0f
    var netSalary:Float=0f
    var noOfDaysInMonth=30

    val fileName = "employeeDetails"
    val fileNameSecond = "employeeSalaryGen"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main7)

        grossSalaryDisplay=findViewById(R.id.grossSalaryDisplay)
        providentFundDisplay=findViewById(R.id.providentFundDisplay)
        incomeTaxDisplay=findViewById(R.id.incomeTaxDisplay)
        netSalaryDisplay=findViewById(R.id.netSalaryDisplay)
        basicSalaryDisplay = findViewById(R.id.basicSalaryDisplay)
        dearnessAllowanceDisplay = findViewById(R.id.dearnessAllowanceDisplay)
        travellingAllowanceDisplay = findViewById(R.id.travellingAllowanceDisplay)
        houseRentalAllowanceDisplay = findViewById(R.id.houseRentalAllowanceDisplay)
        otherAllowanceDisplay = findViewById(R.id.otherAllowanceDisplay)
        salaryDisplay = findViewById(R.id.salaryDisplay)


        getEmployeeDetails()

        val backToMainMenuBtn = findViewById<Button>(R.id.backToMainMenuBtn)
        backToMainMenuBtn.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
    }

    fun getEmployeeDetails() {
        sharedPreferences=getSharedPreferences(fileName, Context.MODE_PRIVATE)
        val basicSalary = sharedPreferences.getString("EmpBasicSalary","")!!.toFloat()
        val dearnessAllowance = sharedPreferences.getString("EmpDearnessAllowance","")!!.toFloat()
        val travellingAllowance = sharedPreferences.getString("EmpTravellingAllowance", "")!!.toFloat()
        val homeRentalAllowance = sharedPreferences.getString("EmpRentalAllowance", "")!!.toFloat()
        val otherAllowance = sharedPreferences.getString("EmpOtherAllowance", "")!!.toFloat()
        sharedPreferences=getSharedPreferences(fileNameSecond, Context.MODE_PRIVATE)
        salaryMonth = sharedPreferences.getString("SalaryMonth", "").toString()
        noOfLeaveDays = sharedPreferences.getString("LeaveWithoutPayDays", "")!!.toFloat()

        val basicSalaryWithLeave = ((noOfDaysInMonth - noOfLeaveDays)*basicSalary)/noOfDaysInMonth
        val dearnessAllowanceWithLeave = ((noOfDaysInMonth - noOfLeaveDays)*dearnessAllowance)/noOfDaysInMonth
        val travellingAllowanceWithLeave = ((noOfDaysInMonth - noOfLeaveDays)*travellingAllowance)/noOfDaysInMonth
        val homeRentalAllowanceWithLeave = ((noOfDaysInMonth - noOfLeaveDays)*homeRentalAllowance)/noOfDaysInMonth
        val otherAllowanceWithLeave = ((noOfDaysInMonth - noOfLeaveDays)*otherAllowance)/noOfDaysInMonth

        grossSalary = basicSalaryWithLeave + dearnessAllowanceWithLeave + travellingAllowanceWithLeave + homeRentalAllowanceWithLeave + otherAllowanceWithLeave
        providentFund = (grossSalary*12)/100
        incomeTax = (grossSalary*10)/100

        netSalary = grossSalary - providentFund - incomeTax

        salaryDisplay.setText("Salary Display for ${salaryMonth}")

        basicSalaryDisplay.setText(basicSalaryWithLeave.toString())
        dearnessAllowanceDisplay.setText(dearnessAllowanceWithLeave.toString())
        travellingAllowanceDisplay.setText(travellingAllowanceWithLeave.toString())
        houseRentalAllowanceDisplay.setText(homeRentalAllowanceWithLeave.toString())
        otherAllowanceDisplay.setText(otherAllowanceWithLeave.toString())
        grossSalaryDisplay.setText(grossSalary.toString())
        providentFundDisplay.setText(providentFund.toString())
        incomeTaxDisplay.setText(incomeTax.toString())
        netSalaryDisplay.setText(netSalary.toString())
    }

}