package com.example.test

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class BookingsFragment : Fragment() {
    lateinit var courseRV: RecyclerView
    lateinit var courseRVAdapter: bookings_adapter
    lateinit var courseList: ArrayList<bookings_model>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_bookings, container, false)
        courseRV = view.findViewById(R.id.bookings)

        // on below line we are initializing our list
        courseList = ArrayList()

        // on below line we are creating a variable
        // for our grid layout manager and specifying
        // column count as 2
        val layoutManager = GridLayoutManager(requireContext(), 2)

        courseRV.layoutManager = layoutManager

        // on below line we are initializing our adapter
        courseRVAdapter = bookings_adapter(courseList, requireContext())

        // on below line we are setting
        // adapter to our recycler view.
        courseRV.adapter = courseRVAdapter
        return view
    }

}