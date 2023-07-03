package com.example.test

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// on below line we are creating
// a course rv adapter class.
class Parking_Adapter(
    // on below line we are passing variables
    // as course list and context
    private val courseList: ArrayList<parking_Model>,
    private val context: Context
) : RecyclerView.Adapter<Parking_Adapter.CourseViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): Parking_Adapter.CourseViewHolder {
        // this method is use to inflate the layout file
        // which we have created for our recycler view.
        // on below line we are inflating our layout file.
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.parking_item,
            parent, false
        )
        // at last we are returning our view holder
        // class with our item View File.
        return CourseViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: Parking_Adapter.CourseViewHolder, position: Int) {
        // on below line we are setting data to our text view and our image view.
        holder.courseNameTV.text = courseList.get(position).ParkingNumber
        holder.courseIV.setImageResource(courseList.get(position).ParkingAvailability)
    }

    override fun getItemCount(): Int {
        // on below line we are
        // returning our size of our list
        return courseList.size
    }

    class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // on below line we are initializing our course name text view and our image view.
        val courseNameTV: TextView = itemView.findViewById(R.id.idTVCourse)
        val courseIV: ImageView = itemView.findViewById(R.id.idIVCourse)
    }
}