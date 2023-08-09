package com.example.test

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// on below line we are creating
// a course rv adapter class.
class bookings_adapter(
    // on below line we are passing variables
    // as course list and context
    private val courseList: ArrayList<bookings_model>,
    private val context: Context
) : RecyclerView.Adapter<bookings_adapter.CourseViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): bookings_adapter.CourseViewHolder {
        // this method is use to inflate the layout file
        // which we have created for our recycler view.
        // on below line we are inflating our layout file.
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.booking_item,
            parent, false
        )
        // at last we are returning our view holder
        // class with our item View File.
        return CourseViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: bookings_adapter.CourseViewHolder, position: Int) {
        // on below line we are setting data to our text view and our image view.
        holder.user.text = courseList.get(position).user
        holder.parking_spot.text=courseList.get(position).parking_spot
    }

    override fun getItemCount(): Int {
        // on below line we are
        // returning our size of our list
        return courseList.size
    }

    class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // on below line we are initializing our course name text view and our image view.
        val user: TextView = itemView.findViewById(R.id.user)
        val parking_spot: TextView= itemView.findViewById(R.id.parking_spot)
    }
}