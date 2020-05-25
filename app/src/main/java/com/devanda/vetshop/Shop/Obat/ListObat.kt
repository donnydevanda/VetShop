package com.devanda.vetshop.Shop.Obat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.devanda.vetshop.R
import com.devanda.vetshop.Utils.Preferences
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_list_obat.*
import java.util.ArrayList

class ListObat : AppCompatActivity() {

    private lateinit var preferences: Preferences
    lateinit var mDatabase: DatabaseReference

    private var dataList = ArrayList<Obat>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_obat)

        preferences = Preferences(this)
        mDatabase = FirebaseDatabase.getInstance().getReference("Obat")
        shop_list_rv.layoutManager = GridLayoutManager(this@ListObat, 2)
        getData()

        detailO_back_btn.setOnClickListener{
            finish()
        }

        detailO_back_text.setOnClickListener{
            finish()
        }

        detailO_sell.setOnClickListener{
            val intent = Intent(this@ListObat,
                KategoriObat::class.java)
            startActivity(intent)
        }
    }

    private fun getData() {
        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                dataList.clear()
                for (getdataSnapshot in dataSnapshot.getChildren()) {

                    val ObatX = getdataSnapshot.getValue(Obat::class.java)
                    dataList.add(ObatX!!)
                }

                shop_list_rv.adapter =
                    ObatAdapter(dataList) {
                        val intent = Intent(
                            this@ListObat,
                            DetilObat::class.java
                        ).putExtra("data", it)
                        startActivity(intent)
                    }

            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@ListObat, ""+error.message, Toast.LENGTH_LONG).show()
            }
        })
    }
}
