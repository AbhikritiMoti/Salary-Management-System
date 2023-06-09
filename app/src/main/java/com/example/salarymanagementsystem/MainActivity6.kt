package com.example.salarymanagementsystem

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity6 : AppCompatActivity() {
    lateinit var sharedPreferences: SharedPreferences
    val fileName = "employeeDetails"

    lateinit var empIdDisplay: TextView
    lateinit var empName: TextView
    lateinit var empNameDisplay: TextView
    lateinit var empFatherNameDisplay: TextView
    lateinit var empDOBDisplay: TextView
    lateinit var empAddressDisplay: TextView
    lateinit var empEmailDisplay: TextView
    lateinit var empMobileNumberDisplay: TextView
    lateinit var empDOJDisplay: TextView
    lateinit var empDOLDisplay: TextView
    lateinit var empDepartmentDisplay: TextView
    lateinit var empDesignationDisplay: TextView
    lateinit var empBasicSalaryDisplay: TextView
    lateinit var empDearnessAllowanceDisplay: TextView
    lateinit var empTravellingAllowanceDisplay: TextView
    lateinit var empHRADisplay: TextView
    lateinit var empOADisplay: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main6)

        empIdDisplay = findViewById(R.id.empIdDisplay)
        empName = findViewById(R.id.empName)
        empNameDisplay = findViewById(R.id.empNameDisplay)
        empFatherNameDisplay = findViewById(R.id.empFatherNameDisplay)
        empDOBDisplay = findViewById(R.id.empDOBDisplay)
        empAddressDisplay = findViewById(R.id.empAddressDisplay)
        empEmailDisplay = findViewById(R.id.empEmailDisplay)
        empMobileNumberDisplay = findViewById(R.id.empMobileNumberDisplay)
        empDOJDisplay = findViewById(R.id.empDOJDisplay)
        empDOLDisplay = findViewById(R.id.empDOLDisplay)
        empDepartmentDisplay = findViewById(R.id.empDepartmentDisplay)
        empDesignationDisplay = findViewById(R.id.empDesignationDisplay)
        empBasicSalaryDisplay = findViewById(R.id.empBasicSalaryDisplay)
        empDearnessAllowanceDisplay = findViewById(R.id.empDearnessAllowanceDisplay)
        empTravellingAllowanceDisplay = findViewById(R.id.empTravellingAllowanceDisplay)
        empHRADisplay = findViewById(R.id.empHRADisplay)
        empOADisplay = findViewById(R.id.empOADisplay)

        getEmployeeDetails()

        val backToEmployeeMasterMainMenuBtn = findViewById<Button>(R.id.backToEmployeeMasterMainMenuBtn)
        backToEmployeeMasterMainMenuBtn.setOnClickListener {
            val intent = Intent(this, MainActivity3::class.java)
            startActivity(intent)
        }
    }

    fun getEmployeeDetails() {
        sharedPreferences = getSharedPreferences(fileName, Context.MODE_PRIVATE)
        empIdDisplay.setText(sharedPreferences.getString("EmpID",""))
        empNameDisplay.setText(sharedPreferences.getString("EmpName", ""))
        empFatherNameDisplay.setText(sharedPreferences.getString("EmpFatherName",""))
        empDOBDisplay.setText(sharedPreferences.getString("EmpDOB", ""))
        empAddressDisplay.setText(sharedPreferences.getString("EmpAddress", ""))
        empEmailDisplay.setText(sharedPreferences.getString("EmpEmail", ""))
        empMobileNumberDisplay.setText(sharedPreferences.getString("EmpMobileNumber",""))
        empDOJDisplay.setText(sharedPreferences.getString("EmpDateOfJoining",""))
        empDOLDisplay.setText(sharedPreferences.getString("EmpDateOfLeaving", ""))
        empDepartmentDisplay.setText(sharedPreferences.getString("Department", ""))
        empDesignationDisplay.setText(sharedPreferences.getString("Designation", ""))
        empBasicSalaryDisplay.setText(sharedPreferences.getString("EmpBasicSalary", ""))
        empDearnessAllowanceDisplay.setText(sharedPreferences.getString("EmpDearnessAllowance", ""))
        empTravellingAllowanceDisplay.setText(sharedPreferences.getString("EmpTravellingAllowance", ""))
        empHRADisplay.setText(sharedPreferences.getString("EmpRentalAllowance", ""))
        empOADisplay.setText(sharedPreferences.getString("EmpOtherAllowance", ""))

    }
}