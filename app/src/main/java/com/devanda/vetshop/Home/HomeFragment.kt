package com.devanda.vetshop.Home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.devanda.vetshop.R
import com.devanda.vetshop.Utils.Preferences
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    private lateinit var preferences: Preferences
    lateinit var mDatabase: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        preferences = Preferences(activity!!.applicationContext)
        mDatabase = FirebaseDatabase.getInstance().getReference("User")

        home_greeting.text = "Halo " + preferences.getValues("username") +" !"

        //KONSULTASI
        home_konsultasi.setOnClickListener {
            val i = Intent(activity, List::class.java)
            startActivity(i)
            (activity as Activity?)!!
        }

        home_salon.setOnClickListener {
            Toast.makeText(getActivity(),"Cooming Soon!",Toast.LENGTH_SHORT).show();
        }

        home_latih.setOnClickListener {
            Toast.makeText(getActivity(),"Cooming Soon!",Toast.LENGTH_SHORT).show();
        }

        home_titip.setOnClickListener {
            Toast.makeText(getActivity(),"Cooming Soon!",Toast.LENGTH_SHORT).show();
        }
    }

}
