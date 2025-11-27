package com.example.rentalhousetracker

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

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

        //initializes database
        db = RentalDatabase.getDatabase(this)

        //find views
        recyclerView = findViewById(R.id.recyclerViewRentals)
        addButton = findViewById(R.id.buttonAddRental)
        emptyText = findViewById(R.id.textViewEmpty)
        progressBar = findViewById(R.id.progressBarLoading)


        //recyclerView setup
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = RentalAdapter(
            rentals,
            onItemClick = { rental ->
                //edits rental
                val intent = Intent(this, RentalEditActivity::class.java)
                intent.putExtra("rental", rental)
                startActivity(intent)
            },
            onItemLongClick = { rental ->
                //deletes rental
                db.rentalDao().delete(rental)
                Toast.makeText(this, "Rental deleted", Toast.LENGTH_SHORT).show()
                loadRentals()
            }
        )

        recyclerView.adapter = adapter

        //adds new rental
        addButton.setOnClickListener {
            val intent = Intent(this, RentalEditActivity::class.java)
            startActivity(intent)
        }

        loadRentals()
    }

    //refreshes rentals
    override fun onResume() {
        super.onResume()
        loadRentals()
    }

    //loads rentals from database
    private fun loadRentals() {
        rentals.clear()
        rentals.addAll(db.rentalDao().getAll())
        adapter.notifyDataSetChanged()


        emptyText.visibility = if (rentals.isEmpty()) View.VISIBLE else View.GONE
    }

    //spinner bar
    private fun showLoading(isLoading: Boolean) {
        progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        recyclerView.visibility = if (isLoading) View.GONE else View.VISIBLE
    }
}