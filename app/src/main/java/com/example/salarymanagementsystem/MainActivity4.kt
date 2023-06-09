package com.example.salarymanagementsystem

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.*
import java.util.Calendar

class MainActivity4 : AppCompatActivity() {
    lateinit var cal : Calendar

    lateinit var empId: EditText
    lateinit var empName: EditText
    lateinit var empFatherName: EditText
    lateinit var empDOB: EditText
    lateinit var empAddress: EditText
    lateinit var empEmail: EditText
    lateinit var empMobileNumber: EditText
    lateinit var empDateOfJoining: EditText
    lateinit var empDateOfLeaving: EditText
    lateinit var departmentSpinner: Spinner
    lateinit var designationSpinner: Spinner
    lateinit var empBasicSalary: EditText
    lateinit var empDearnessAllowance: EditText
    lateinit var empTravellingAllowance: EditText
    lateinit var empRentalAllowance: EditText
    lateinit var empOtherAllowance: EditText

    lateinit var optionForDepartment: String
    lateinit var optionForDesignation: String

    val fileName = "employeeDetails"
    lateinit var sharedPreference: SharedPreferences

    var isEditable: Boolean=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        empId = findViewById(R.id.empId)
        empName = findViewById(R.id.empName)
        empFatherName = findViewById(R.id.empFatherName)
        empDOB = findViewById(R.id.empDOB)
        empAddress = findViewById(R.id.empAddress)
        empEmail = findViewById(R.id.empEmail)
        empMobileNumber = findViewById(R.id.empMobileNumber)
        empDateOfJoining=findViewById(R.id.empDateOfJoining)
        empDateOfLeaving=findViewById(R.id.empDateOfLeaving)
        departmentSpinner = findViewById(R.id.departmentSpinner)
        designationSpinner = findViewById(R.id.designationSpinner)
        empBasicSalary = findViewById(R.id.empBasicSalary)
        empDearnessAllowance = findViewById(R.id.empDearnessAllowance)
        empTravellingAllowance = findViewById(R.id.empTravellingAllowance)
        empRentalAllowance = findViewById(R.id.empRentalAllowance)
        empOtherAllowance = findViewById(R.id.empOtherAllowance)
        sharedPreference=getSharedPreferences(fileName, Context.MODE_PRIVATE)

        empDOB.setOnClickListener {
            getDatePicker(empDOB)
        }

        empDateOfJoining.setOnClickListener {
            getDatePicker(empDateOfJoining)
        }

        empDateOfLeaving.setOnClickListener {
            getDatePicker(empDateOfLeaving)
        }

        val backToEmployeeMasterMainMenuBtn = findViewById<Button>(R.id.backToEmployeeMasterMainMenuBtn)
        backToEmployeeMasterMainMenuBtn.setOnClickListener {
            val intent = Intent(this, MainActivity3::class.java)
            startActivity(intent)
        }


       DOJSpinner()
       DOLSpinner()


        val btnSubmit = findViewById<Button>(R.id.btnSubmit)
        btnSubmit.setOnClickListener {
            if (!empMobileNumber.text.toString().isEmpty()) {
                if (empMobileNumber.length()<10 || empMobileNumber.length()>10) {
                    val vg: ViewGroup? = findViewById(R.id.custom_toast)
                    val inflater = layoutInflater
                    val layout: View = inflater.inflate(R.layout.custom_toast, vg)
                    val tv = layout.findViewById<TextView>(R.id.txtVw)
                    tv.text = "Please enter a valid mobile number"
                    val toast = Toast(applicationContext)
                    toast.setGravity(Gravity.BOTTOM, 0, 400)
                    toast.setView(layout)
                    toast.duration = Toast.LENGTH_SHORT
                    toast.show()

                }
            }
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
            saveEmployeeDetails()
        }


        isEditable = intent.getBooleanExtra("isEditable", false)
        if (isEditable) {
            getEmployeeDetails()
        }

    }

     fun getDatePicker(myEditText: EditText) {
        cal = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)

        val datePicker = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            myEditText.setText("$dayOfMonth/${month+1}/$year")
        }, year, month, day)
        datePicker.show()
    }

    fun DOJSpinner() {
        val department = arrayOf("Select an employee department","Accounts","Production","Purchase","Sale","Marketing","Information Technology","Research & Development")
        optionForDepartment=""

        if (departmentSpinner!=null) {
            val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,department)
            departmentSpinner.adapter=adapter

            departmentSpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long
                ) {
                    if(department[position]!="Select an employee department")
                        optionForDepartment = department[position]
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    optionForDepartment="Department"
                }
            }
        }
    }

    fun DOLSpinner() {
        val designation = arrayOf("Select an employee designation","Assistant Manager", "Senior Manager","Accountant","Account Manager","Software Engineer", "Data Entry Operator", "Programmer", "Clerk", "Labour")
        optionForDesignation=""


        if (designationSpinner!=null) {
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item,designation)
            designationSpinner.adapter=adapter

            designationSpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long
                ) {
                    if (designation[position]!="Select an employee designation")
                        optionForDesignation=designation[position]
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
            }
        }
    }


    fun saveEmployeeDetails() {
        val empId = empId.text.toString()
        val empName = empName.text.toString()
        val empFatherName = empFatherName.text.toString()
        val empDOB = empDOB.text.toString()
        val empAddress = empAddress.text.toString()
        val empEmail = empEmail.text.toString()
        val empMobileNumber = empMobileNumber.text.toString()
        val empDateOfJoining = empDateOfJoining.text.toString()
        val empDateOfLeaving = empDateOfLeaving.text.toString()
        val departmentSpinner = optionForDepartment
        val designationSpinner = optionForDesignation
        val empBasicSalary = empBasicSalary.text.toString()
        val empDearnessAllowance = empDearnessAllowance.text.toString()
        val empTravellingAllowance = empTravellingAllowance.text.toString()
        val empRentalAllowance = empRentalAllowance.text.toString()
        val empOtherAllowance = empOtherAllowance.text.toString()
        val edit=sharedPreference.edit()

        edit.putString("EmpID", empId)
        edit.putString("EmpName", empName)
        edit.putString("EmpFatherName", empFatherName)
        edit.putString("EmpDOB", empDOB)
        edit.putString("EmpAddress", empAddress)
        edit.putString("EmpEmail", empEmail)
        edit.putString("EmpMobileNumber", empMobileNumber)
        edit.putString("EmpDateOfJoining", empDateOfJoining)
        edit.putString("EmpDateOfLeaving", empDateOfLeaving)
        edit.putString("Department", departmentSpinner)
        edit.putString("Designation", designationSpinner)
        edit.putString("EmpBasicSalary", empBasicSalary)
        edit.putString("EmpDearnessAllowance", empDearnessAllowance)
        edit.putString("EmpTravellingAllowance", empTravellingAllowance)
        edit.putString("EmpRentalAllowance", empRentalAllowance)
        edit.putString("EmpOtherAllowance", empOtherAllowance)

        edit.apply()
    }

    private fun getEmployeeDetails() {
        sharedPreference=getSharedPreferences(fileName, Context.MODE_PRIVATE)
        empId.setText(sharedPreference.getString("EmpID",""))
        empName.setText(sharedPreference.getString("EmpName", ""))
        empFatherName.setText(sharedPreference.getString("EmpFatherName",""))
        empDOB.setText(sharedPreference.getString("EmpDOB", ""))
        empAddress.setText(sharedPreference.getString("EmpAddress", ""))
        empEmail.setText(sharedPreference.getString("EmpEmail", ""))
        empMobileNumber.setText(sharedPreference.getString("EmpMobileNumber",""))
        empDateOfJoining.setText(sharedPreference.getString("EmpDateOfJoining",""))
        empDateOfLeaving.setText(sharedPreference.getString("EmpDateOfLeaving", ""))
        empBasicSalary.setText(sharedPreference.getString("EmpBasicSalary", ""))
        empDearnessAllowance.setText(sharedPreference.getString("EmpDearnessAllowance", ""))
        empTravellingAllowance.setText(sharedPreference.getString("EmpTravellingAllowance", ""))
        empRentalAllowance.setText(sharedPreference.getString("EmpRentalAllowance", ""))
        empOtherAllowance.setText(sharedPreference.getString("EmpOtherAllowance", ""))

    }

    fun delEmployeeDetails(sharedPreference: SharedPreferences) {
        val edit = sharedPreference.edit()
        edit.clear()
        edit.apply()
        finish()
    }

}