package com.example.test

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue

class MainActivity2 : AppCompatActivity() {
    lateinit var courseRV: RecyclerView
    lateinit var courseRVAdapter: Parking_Adapter
    lateinit var courseList: ArrayList<parking_Model>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        courseRV = findViewById(R.id.idRVCourses)
        //val1=parking_Model("taken","1")
        //val2=parking_Model("not taken","2")
        // on below line we are initializing our list
        courseList = ArrayList()
        val layoutManager = GridLayoutManager(this, 2)

        courseRV.layoutManager = layoutManager

        // on below line we are initializing our adapter
        courseRVAdapter = Parking_Adapter(courseList, this)

        // on below line we are setting
        // adapter to our recycler view.
        courseRV.adapter = courseRVAdapter
        // Read from the database
        val database:FirebaseDatabase=FirebaseDatabase.getInstance()
        val myRef=database.reference.child("parkings")
        courseList.add(parking_Model("yes","available"))
        myRef.addValueEventListener(object: ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                    for(dat in snapshot.children)
                    {
                        val val1=dat.child("ParkingAvailability").value.toString()
                        val val2=dat.child("ParkingNumber").value.toString()
                        courseList.add(parking_Model(val1,val2))
                        courseList.add(parking_Model("yes","available"))
                    }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "Failed to read value.", error.toException())
            }

        })
        // on below line we are adding data to our list

        // on below line we are notifying adapter
        // that data has been updated.
        courseRVAdapter.notifyDataSetChanged()

    }
}