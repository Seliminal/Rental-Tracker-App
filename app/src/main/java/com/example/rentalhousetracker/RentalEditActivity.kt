package com.example.rentalhousetracker

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

// creating a class and initializing variables
class RentalEditActivity : AppCompatActivity() {

    private lateinit var titleInput: EditText
    private lateinit var locationInput: EditText
    private lateinit var priceInput: EditText
    private lateinit var imageUrlInput: EditText
    private lateinit var saveButton: Button

    private lateinit var db: RentalDatabase
    private var rentalToEdit: Rental? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rental_edit)

        // initializing the views
        titleInput = findViewById(R.id.editTitle)
        locationInput = findViewById(R.id.editLocation)
        priceInput = findViewById(R.id.editPrice)
        imageUrlInput = findViewById(R.id.editImageUrl)
        saveButton = findViewById(R.id.buttonSave)

        db = RentalDatabase.getDatabase(this)

        // Check if editing an existing rental
        rentalToEdit = intent.getSerializableExtra("rental") as? Rental
        rentalToEdit?.let {
            titleInput.setText(it.title)
            locationInput.setText(it.location)
            priceInput.setText(it.price)
            imageUrlInput.setText(it.imageUrl)
        }

        saveButton.setOnClickListener {
            val title = titleInput.text.toString()
            val location = locationInput.text.toString()
            val price = priceInput.text.toString()
            val imageUrl = imageUrlInput.text.toString() // <--- you were missing this

            if (rentalToEdit == null) {
                // Create
                db.rentalDao().insert(Rental(title = title, location = location, price = price, imageUrl = imageUrl))
            } else {
                // Update
                val updated = rentalToEdit!!.copy(title = title, location = location, price = price, imageUrl = imageUrl)
                db.rentalDao().update(updated)
            }
            finish()
        }

    }
}
