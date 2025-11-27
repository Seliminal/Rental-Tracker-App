package com.example.rentalhousetracker

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RentalListActivity : AppCompatActivity() {

    private lateinit var db: RentalDatabase
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RentalAdapter
    private lateinit var addButton: Button
    private lateinit var emptyText: TextView
    private lateinit var progressBar: ProgressBar

    private var rentals = mutableListOf<Rental>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rental_list)

        // Initialize database
        db = RentalDatabase.getDatabase(this)

        // Find views
        recyclerView = findViewById(R.id.recyclerViewRentals)
        addButton = findViewById(R.id.buttonAddRental)
        emptyText = findViewById(R.id.textViewEmpty)
        progressBar = findViewById(R.id.progressBarLoading)

        // RecyclerView setup
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = RentalAdapter(
            rentals,
            onItemClick = { rental ->
                // Edit rental
                val intent = Intent(this, RentalEditActivity::class.java)
                intent.putExtra("rental", rental) // now Parcelable instead of Serializable
                startActivity(intent)
            },
            onItemLongClick = { rental ->
                // Delete rental
                db.rentalDao().delete(rental)
                Toast.makeText(this, "Rental deleted", Toast.LENGTH_SHORT).show()
                loadRentals()
            }
        )
        recyclerView.adapter = adapter

        // Add new rental
        addButton.setOnClickListener {
            val intent = Intent(this, RentalEditActivity::class.java)
            startActivity(intent)
        }

        // Initial load
        loadRentals()
    }

    override fun onResume() {
        super.onResume()
        loadRentals()
    }

    private fun loadRentals() {
        // Show spinner
        showLoading(true)

        lifecycleScope.launch {
            val rentalList = withContext(Dispatchers.IO) {
                db.rentalDao().getAll() // database call on background thread
            }

            rentals.clear()
            rentals.addAll(rentalList)
            adapter.notifyDataSetChanged()

            // Show empty text if no rentals
            emptyText.visibility = if (rentals.isEmpty()) View.VISIBLE else View.GONE

            // Hide spinner
            showLoading(false)
        }
    }


    // Show/hide spinner while loading
    private fun showLoading(isLoading: Boolean) {
        progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        recyclerView.visibility = if (isLoading) View.GONE else View.VISIBLE
    }
}
