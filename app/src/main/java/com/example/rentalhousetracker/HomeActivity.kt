package com.example.rentalhousetracker

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        //connects buttons from XML
        val viewRentalsButton = findViewById<Button>(R.id.viewRentalsButton)
        val addRentalButton = findViewById<Button>(R.id.addRentalButton)

        //button opens list activity
        viewRentalsButton.setOnClickListener {
            val intent = Intent(this, RentalListActivity::class.java)
            startActivity(intent)
        }

        //button opens add rental activity
        addRentalButton.setOnClickListener {
            val intent = Intent(this, RentalEditActivity::class.java)
            startActivity(intent)
        }
    }
}

