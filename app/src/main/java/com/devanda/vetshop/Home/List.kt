package com.devanda.vetshop.Home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.devanda.vetshop.R
import com.devanda.vetshop.Utils.Preferences
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_list.*
import java.util.ArrayList

class List : AppCompatActivity() {

    private lateinit var preferences: Preferences
    lateinit var mDatabase: DatabaseReference

    private var dataList = ArrayList<Doctor>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        preferences = Preferences(this)
        mDatabase = FirebaseDatabase.getInstance().getReference("Project")
        list_rv.layoutManager = LinearLayoutManager(this@List, LinearLayoutManager.VERTICAL, false)
        getData()

        list_back_btn.setOnClickListener{
            finish()
        }

        list_back_text.setOnClickListener{
            finish()
        }
    }

    private fun getData() {
        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                dataList.clear()
                for (getdataSnapshot in dataSnapshot.getChildren()) {

                    val doctorX = getdataSnapshot.getValue(Doctor::class.java)
                    dataList.add(doctorX!!)
                }

                list_rv.adapter =
                    DoctorAdapter(dataList) {
                        val intent = Intent(
                            this@List,
                            ListDetail::class.java
                        ).putExtra("data", it)
                        startActivity(intent)
                    }

            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@List, ""+error.message, Toast.LENGTH_LONG).show()
            }
        })
    }
}
