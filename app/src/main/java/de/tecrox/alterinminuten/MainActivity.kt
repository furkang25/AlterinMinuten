package de.tecrox.alterinminuten

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDataPicker.setOnClickListener { view ->
            onClickDatePicker(view)
        }

    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun onClickDatePicker(view: View) {

        val myCalender =  Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener
            { view, year, month, dayofMonth ->
                Toast.makeText(this, "Geburtsdatum: " + "$dayofMonth.$month.$year", Toast.LENGTH_LONG).show()
                var selectedDate = "$dayofMonth.${month+1}.$year"
                tvSelectedDate.setText(selectedDate)

                val sdf = SimpleDateFormat("dd.MM.yyyy", Locale.GERMAN)
                val sdfDate = sdf.parse(selectedDate)

                val selectedDateToMinutes = sdfDate!!.time / 60000

                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                val currentDateToMinutes = currentDate!!.time / 60000

                val differenceInMinutes = currentDateToMinutes - selectedDateToMinutes

                tvSelectedDateMinuten.setText(differenceInMinutes.toString())
            }
            , year, month, day)

        dpd.datePicker.maxDate = Date().time
        dpd.show()

    }
}