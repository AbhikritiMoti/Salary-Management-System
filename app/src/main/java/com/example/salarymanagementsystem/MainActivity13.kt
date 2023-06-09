package com.example.salarymanagementsystem
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar



class MainActivity13 : AppCompatActivity() {

    private var data1 = ArrayList<String>()
    private var data2 = ArrayList<String>()
    private var data3 = ArrayList<String>()
    private var data4 = ArrayList<String>()
    private var data5 = ArrayList<String>()
    private lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main13)

        val empName = findViewById<EditText>(R.id.ed1)
        val salary = findViewById<EditText>(R.id.ed2)
        val bonous = findViewById<EditText>(R.id.ed3)
        val subtotal = findViewById<EditText>(R.id.ed4)
        val tax = findViewById<EditText>(R.id.ed5)
        val bal = findViewById<EditText>(R.id.ed6)
        val id = findViewById<EditText>(R.id.empID)

        val b1 = findViewById<Button>(R.id.btn1)
        val b2 = findViewById<Button>(R.id.btn2)

        val view = findViewById<LinearLayout>(R.id.view)

        val actionBar = supportActionBar
        // providing subtitle for the ActionBar
        if (actionBar != null) {
            actionBar.subtitle = "Salary Management"
        }


        bottomNav = findViewById(R.id.bottomNav)
        title = "Quick Calculation"




        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> {
                    val intent = Intent(this, MainActivity2::class.java)
                    startActivity(intent)
                    true
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

                    true
                }

                else -> true

            }
        }

        b2.setOnClickListener {
            val check = subtotal.text.toString()
            val check2 = tax.text.toString()
            if (check.isEmpty() || check2.isEmpty()) {
                val vg: ViewGroup? = findViewById(R.id.custom_toast)
                val inflater = layoutInflater
                val layout: View = inflater.inflate(R.layout.custom_toast, vg)
                val tv = layout.findViewById<TextView>(R.id.txtVw)
                tv.text = "Fill the Total and Tax fields"
                val toast = Toast(applicationContext)
                toast.setGravity(Gravity.BOTTOM, 0, 400)
                toast.setView(layout)
                toast.duration = Toast.LENGTH_SHORT
                toast.show()

            } else {
                val sub = Integer.parseInt(subtotal.getText().toString())
                val taxx = Integer.parseInt(tax.getText().toString())
                val balance = sub - (sub * taxx / 100)
                bal.setText(balance.toString())

                val snack2 = Snackbar.make(view, "In-hand Slary Updated", Snackbar.LENGTH_LONG)
                snack2.setAction("OK") {
                }
                snack2.show()
            }

        }

        b1.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Salary Update Alert")
                .setMessage("Add Salary ?")
                .setCancelable(true)
                // .setMessage("this is alert")
                .setIcon(android.R.drawable.ic_dialog_alert)


            //performing positive action
            builder.setPositiveButton("Add") { dialogInterface, which ->
                val eidd = id.text.toString()
                val enamee = empName.text.toString()
                val sall = salary.text.toString()
                val bnss = bonous.text.toString()


                if (eidd.isEmpty() || enamee.isEmpty() ||
                    sall.isEmpty() || bnss.isEmpty()
                ) {

                    val vg: ViewGroup? = findViewById(R.id.custom_toast)
                    val inflater = layoutInflater
                    val layout: View = inflater.inflate(R.layout.custom_toast, vg)
                    val tv = layout.findViewById<TextView>(R.id.txtVw)
                    tv.text = "Empty Fields"
                    val toast = Toast(applicationContext)
                    toast.setGravity(Gravity.BOTTOM, 0, 400)
                    toast.setView(layout)
                    toast.duration = Toast.LENGTH_SHORT
                    toast.show()

                } else {
                    val eid = eidd.toInt()
                    val sal = sall.toInt()
                    val bns = bnss.toInt()


                    val total = sal + bns

                    data1.add(enamee)
                    data2.add(sal.toString())
                    data3.add(bns.toString())
                    data4.add(total.toString())
                    data5.add(eid.toString())

                    val table = findViewById<TableLayout>(R.id.tab1)

                    val row = TableRow(this)

                    val t1 = TextView(this)
                    val t2 = TextView(this)
                    val t3 = TextView(this)
                    val t4 = TextView(this)
                    val t5 = TextView(this)


                    var tot: String
                    var sum = 0

                    for (i in data1.indices) {

                        val ei = data5[i]
                        val en = data1[i]
                        val sa = data2[i]
                        val bn = data3[i]
                        val to = data4[i]


                        t5.text = ei
                        t1.text = en
                        t2.text = sa
                        t3.text = bn
                        t4.text = to


                        sum = sum + Integer.parseInt(data4[i])


                        t1.setTextColor(Color.BLACK);
                        t2.setTextColor(Color.BLACK);
                        t3.setTextColor(Color.BLACK);
                        t4.setTextColor(Color.BLACK);
                        t5.setTextColor(Color.BLACK);

                        t5.setTypeface(null, Typeface.BOLD);
                        t1.setTypeface(null, Typeface.BOLD);
                        t2.setTypeface(null, Typeface.BOLD);
                        t3.setTypeface(null, Typeface.BOLD);
                        t4.setTypeface(null, Typeface.BOLD);


                        t5.setBackgroundColor(Color.rgb(220, 220, 220))
                        t2.setBackgroundColor(Color.rgb(220, 220, 220))
                        t4.setBackgroundColor(Color.rgb(220, 220, 220))


                        t1.textSize = 17F
                        t2.textSize = 17F
                        t3.textSize = 17F
                        t4.textSize = 17F
                        t5.textSize = 17F


                        t1.textAlignment = View.TEXT_ALIGNMENT_CENTER
                        t2.textAlignment = View.TEXT_ALIGNMENT_CENTER
                        t3.textAlignment = View.TEXT_ALIGNMENT_CENTER
                        t4.textAlignment = View.TEXT_ALIGNMENT_CENTER
                        t5.textAlignment = View.TEXT_ALIGNMENT_CENTER

                    }

                    row.addView(t5)
                    row.addView(t1)
                    row.addView(t2)
                    row.addView(t3)
                    row.addView(t4)

                    table.addView(row)


                    subtotal.setText(sum.toString())
                    empName.setText("")
                    salary.setText("")
                    empName.setText("")
                    bonous.setText("")
                    id.setText("")


                    empName.requestFocus()

                    val snack =
                        Snackbar.make(view, "Salary Transaction Added", Snackbar.LENGTH_LONG)
                    snack.setAction("OK") {
                    }
                    snack.show()

                }
            }
            //performing cancel action
            builder.setNeutralButton("Cancel") { dialogInterface, which ->

                val vg: ViewGroup? = findViewById(R.id.custom_toast)
                val inflater = layoutInflater
                val layout: View = inflater.inflate(R.layout.custom_toast, vg)
                val tv = layout.findViewById<TextView>(R.id.txtVw)
                tv.text = "clicked cancel\n" +
                        " operation cancel"
                val toast = Toast(applicationContext)
                toast.setGravity(Gravity.BOTTOM, 0, 400)
                toast.setView(layout)
                toast.duration = Toast.LENGTH_SHORT
                toast.show()

            }
            //performing negative action
            builder.setNegativeButton("Decline") { dialogInterface, which ->
                val vg: ViewGroup? = findViewById(R.id.custom_toast)
                val inflater = layoutInflater
                val layout: View = inflater.inflate(R.layout.custom_toast, vg)
                val tv = layout.findViewById<TextView>(R.id.txtVw)
                tv.text = "Request Declined"
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.actionmenu, menu)
        val item = menu.findItem(R.id.app_bar_search)
        val searchView = item.actionView as SearchView
        searchView.queryHint = "Search People"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                Toast.makeText(applicationContext, query, Toast.LENGTH_SHORT).show()
                return true
            }

            override fun onQueryTextChange(query: String): Boolean {
                Toast.makeText(applicationContext, query, Toast.LENGTH_SHORT).show()
                return true
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val eid =findViewById<EditText>(R.id.empID)
        val ename =findViewById<EditText>(R.id.ed1)
        val sal =findViewById<EditText>(R.id.ed2)
        val bon =findViewById<EditText>(R.id.ed3)
        val tot =findViewById<EditText>(R.id.ed4)
        val tax =findViewById<EditText>(R.id.ed5)
        val isal =findViewById<EditText>(R.id.ed6)

        when (item.itemId) {
            R.id.add -> {
                val vg: ViewGroup? = findViewById(R.id.custom_toast)
                val inflater = layoutInflater
                val layout: View = inflater.inflate(R.layout.custom_toast, vg)
                val tv = layout.findViewById<TextView>(R.id.txtVw)
                tv.text = "Sample data added!"
                val toast = Toast(applicationContext)
                toast.setGravity(Gravity.BOTTOM, 0, 400)
                toast.setView(layout)
                toast.duration = Toast.LENGTH_SHORT
                toast.show()
                eid.setText("12007073")
                ename.setText("Abhikriti")
                sal.setText("150000")
                bon.setText("70000")
                tax.setText("12")
                return true
            }
            R.id.reset -> {
                val vg: ViewGroup? = findViewById(R.id.custom_toast)
                val inflater = layoutInflater
                val layout: View = inflater.inflate(R.layout.custom_toast, vg)
                val tv = layout.findViewById<TextView>(R.id.txtVw)
                tv.text = "Data cleared !"
                val toast = Toast(applicationContext)
                toast.setGravity(Gravity.BOTTOM, 0, 400)
                toast.setView(layout)
                toast.duration = Toast.LENGTH_SHORT
                toast.show()

                eid.setText("")
                ename.setText("")
                sal.setText("")
                bon.setText("")
                tot.setText("")
                tax.setText("")
                isal.setText("")
                return true
            }
            R.id.rateus -> {
                val vg: ViewGroup? = findViewById(R.id.custom_toast)
                val inflater = layoutInflater
                val layout: View = inflater.inflate(R.layout.custom_toast, vg)
                val tv = layout.findViewById<TextView>(R.id.txtVw)
                tv.text = "Rate Us"
                val toast = Toast(applicationContext)
                toast.setGravity(Gravity.BOTTOM, 0, 400)
                toast.setView(layout)
                toast.duration = Toast.LENGTH_SHORT
                toast.show()

                val intent = Intent(this, MainActivity12::class.java)
                startActivity(intent)
                return true
            }

            R.id.Share -> {
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, "https://github.com/abhikritimoti/Salary-Management-System.git")
                    type = "text/plain"
                }

                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
            }

            R.id.git -> {
                val vg: ViewGroup? = findViewById(R.id.custom_toast)
                val inflater = layoutInflater
                val layout: View = inflater.inflate(R.layout.custom_toast, vg)
                val tv = layout.findViewById<TextView>(R.id.txtVw)
                tv.text = "Opening Source Code"
                val toast = Toast(applicationContext)
                toast.setGravity(Gravity.BOTTOM, 0, 400)
                toast.setView(layout)
                toast.duration = Toast.LENGTH_SHORT
                toast.show()

                var url = "https://github.com/abhikritimoti/Salary-Management-System.git"
                val intent = Intent(Intent.ACTION_VIEW)
                intent.setDataAndType(Uri.parse(url), "text/plain")
                val choose = Intent.createChooser(intent, "Share URL")
                startActivity(choose)
                return true
            }
            R.id.exit -> {
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
                return true
            }
        }
        return true
    }

}