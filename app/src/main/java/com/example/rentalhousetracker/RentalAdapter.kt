package com.example.rentalhousetracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

//connects list of rental objects to recyclerview
//allows for edit and delete
class RentalAdapter(
    private var rentals: MutableList<Rental>,
    private val onItemClick: (Rental) -> Unit,
    private val onItemLongClick: (Rental) -> Unit
) : RecyclerView.Adapter<RentalAdapter.RentalViewHolder>() {

    //inner class for viewholder which holds refrences for the list views
    class RentalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleText: TextView = itemView.findViewById(R.id.textViewTitle)
        val locationText: TextView = itemView.findViewById(R.id.textViewLocation)
        val priceText: TextView = itemView.findViewById(R.id.textViewPrice)
        val rentalImage: ImageView = itemView.findViewById(R.id.imageViewRental)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RentalViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rental_item, parent, false)
        return RentalViewHolder(view)
    }

    override fun onBindViewHolder(holder: RentalViewHolder, position: Int) {
        val rental = rentals[position]
        holder.titleText.text = rental.title
        holder.locationText.text = rental.location
        holder.priceText.text = rental.price

        //uses glide method to allow for image url upload
        if (rental.imageUrl.isNotEmpty()) {
            Glide.with(holder.itemView.context)
                .load(rental.imageUrl)
                .into(holder.rentalImage)
        } else {
            holder.rentalImage.setImageDrawable(null)
        }

        holder.itemView.setOnClickListener { onItemClick(rental) }
        holder.itemView.setOnLongClickListener {
            onItemLongClick(rental)
            true
        }
    }

    override fun getItemCount(): Int = rentals.size

    //update feature
    fun updateData(newList: List<Rental>) {
        rentals.clear()
        rentals.addAll(newList)
        notifyDataSetChanged()
    }
}
