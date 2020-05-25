package com.devanda.vetshop.Shop.Makanan

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.devanda.vetshop.R
import com.devanda.vetshop.Utils.Preferences
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_list_makanan.*
import java.util.*


class ListMakanan : AppCompatActivity() {

    private lateinit var preferences: Preferences
    lateinit var mDatabase: DatabaseReference

    private var dataList = ArrayList<Makanan>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_makanan)

        preferences = Preferences(this)
        mDatabase = FirebaseDatabase.getInstance().getReference("Makanan")
        shop_list_rv.layoutManager = GridLayoutManager(this@ListMakanan, 2)
        getData()

        detailM_back_btn.setOnClickListener{
            finish()
        }

        detailM_back_text.setOnClickListener{
            finish()
        }

        detailM_sell.setOnClickListener{
            val intent = Intent(this@ListMakanan,
                KategoriMakanan::class.java)
            startActivity(intent)
        }
    }

    private fun getData() {
        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                dataList.clear()
                for (getdataSnapshot in dataSnapshot.getChildren()) {

                    val makananX = getdataSnapshot.getValue(Makanan::class.java)
                    dataList.add(makananX!!)
                }

                shop_list_rv.adapter =
                    MakananAdapter(dataList) {
                        val intent = Intent(
                            this@ListMakanan,
                            DetilMakanan::class.java
                        ).putExtra("data", it)
                        startActivity(intent)
                    }

            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@ListMakanan, ""+error.message, Toast.LENGTH_LONG).show()
            }
        })
    }
}
