package com.example.practicalexercise3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var myData: PremiumModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myData = ViewModelProviders.of(this).get(PremiumModel::class.java)
        display();

        fun getPremium(): Double {

            return when (SpinnerAge.selectedItemPosition) {
                0 -> 60.00
                1 -> 70.00 +
                        (if (radMale.isChecked) 50.00 else 0.0) +
                        (if (chksmoker.isChecked) 100.00 else 0.0)
                2 -> 90.00 +
                        (if (radMale.isChecked) 100.00 else 0.0) +
                        (if (chksmoker.isChecked) 150.00 else 0.0)
                3 -> 120.00 +
                        (if (radMale.isChecked) 150.00 else 0.0) +
                        (if (chksmoker.isChecked) 200.00 else 0.0)
                4 -> 150.00 +
                        (if (radMale.isChecked) 200.00 else 0.0) +
                        (if (chksmoker.isChecked) 250.00 else 0.0)
                else -> 150.00 +
                        (if (radMale.isChecked) 200.00 else 0.0) +
                        (if (chksmoker.isChecked) 300.00 else 0.0)
            }
        }

        val btnCalculate = findViewById<View>(R.id.Calculate_Button)
        btnCalculate.setOnClickListener {
            myData.premiumAmount = getPremium()
            display()
        }
        Reset_Button.setOnClickListener() {
            it
            Total_Premium.text = "";
            SpinnerAge.selectedItemPosition
            SpinnerAge.setSelection(0)
            Gender_Type.clearCheck()
            chksmoker.setChecked(false)
            myData.premiumAmount = 0.0
        }

    }
    fun display(){
        if(myData.premiumAmount !=0.0)
            Total_Premium.text = "RM" + myData.premiumAmount.toString()
    }
}

