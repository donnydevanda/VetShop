package com.devanda.vetshop.Shop.Mainan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.devanda.vetshop.R
import com.devanda.vetshop.Utils.Preferences
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_list_mainan.*
import java.util.ArrayList

class ListMainan : AppCompatActivity() {

    private lateinit var preferences: Preferences
    lateinit var mDatabase: DatabaseReference

    private var dataList = ArrayList<Mainan>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_mainan)

        preferences = Preferences(this)
        mDatabase = FirebaseDatabase.getInstance().getReference("Mainan")
        shop_list_rv.layoutManager = GridLayoutManager(this@ListMainan, 2)
        getData()

        detailMa_back_btn.setOnClickListener{
            finish()
        }

        detailMa_back_text.setOnClickListener{
            finish()
        }

        detailMa_sell.setOnClickListener{
            val intent = Intent(this@ListMainan,
                KategoriMainan::class.java)
            startActivity(intent)
        }
    }

    private fun getData() {
        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                dataList.clear()
                for (getdataSnapshot in dataSnapshot.getChildren()) {

                    val MainanX = getdataSnapshot.getValue(Mainan::class.java)
                    dataList.add(MainanX!!)
                }

                shop_list_rv.adapter =
                    MainanAdapter(dataList) {
                        val intent = Intent(
                            this@ListMainan,
                            DetilMainan::class.java
                        ).putExtra("data", it)
                        startActivity(intent)
                    }

            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@ListMainan, ""+error.message, Toast.LENGTH_LONG).show()
            }
        })
    }
}
