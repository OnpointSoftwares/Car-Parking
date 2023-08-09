package com.example.test

import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

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
        holder.parkingPosition.text = courseList.get(position).ParkingNumber
        holder.parkingAvailability.text=courseList.get(position).ParkingAvailability
        if(courseList.get(position).ParkingAvailability=="taken")
        {
            holder.itemView.setBackgroundColor(Color.BLACK)
            holder.btn_book.setBackgroundColor(Color.BLACK)
        }
        else{
            holder.itemView.setBackgroundColor(Color.BLUE)
        }
        holder.btn_book.setOnClickListener {
            val pdialog:ProgressDialog=ProgressDialog(context)
            pdialog.setTitle("loading");
            pdialog.setMessage("Booking processing...")
            pdialog.show()
            val firebaseDatabase:FirebaseDatabase=FirebaseDatabase.getInstance()
            val ref=firebaseDatabase.reference
            val user= FirebaseAuth.getInstance().currentUser?.email
            val id=ref.child("bookings").push()
            val hashmap:HashMap<String,String> = HashMap()
            hashmap["email"] = user.toString()
            val key=ref.push().key
            hashmap["parkingNumber"]= courseList[position].ParkingNumber
            ref.child("bookings").child(key.toString()).setValue(hashmap).addOnCompleteListener {
                Toast.makeText(context,"Booking Successfully completed",Toast.LENGTH_LONG).show()
                pdialog.dismiss()
            }
        }
    }

    override fun getItemCount(): Int {
        // on below line we are
        // returning our size of our list
        return courseList.size
    }

    class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // on below line we are initializing our course name text view and our image view.
        val parkingPosition: TextView = itemView.findViewById(R.id.idTVParking)
        val parkingAvailability: TextView= itemView.findViewById(R.id.idTVAvailability)
        val btn_book:Button=itemView.findViewById(R.id.Btn_book)
    }
}