package com.devanda.vetshop.Shop.Rumah

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.devanda.vetshop.R
import com.devanda.vetshop.Utils.Preferences
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_list_rumah.*
import java.util.ArrayList

class ListRumah : AppCompatActivity() {

    private lateinit var preferences: Preferences
    lateinit var mDatabase: DatabaseReference

    private var dataList = ArrayList<Rumah>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_rumah)

        preferences = Preferences(this)
        mDatabase = FirebaseDatabase.getInstance().getReference("Rumah")
        shop_list_rv.layoutManager = GridLayoutManager(this@ListRumah, 2)
        getData()

        detailR_back_btn.setOnClickListener{
            finish()
        }

        detailR_back_text.setOnClickListener{
            finish()
        }

        detailR_sell.setOnClickListener{
            val intent = Intent(this@ListRumah,
                KategoriRumah::class.java)
            startActivity(intent)
        }
    }

    private fun getData() {
        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                dataList.clear()
                for (getdataSnapshot in dataSnapshot.getChildren()) {

                    val RumahX = getdataSnapshot.getValue(Rumah::class.java)
                    dataList.add(RumahX!!)
                }

                shop_list_rv.adapter =
                    RumahAdapter(dataList) {
                        val intent = Intent(
                            this@ListRumah,
                            DetilRumah::class.java
                        ).putExtra("data", it)
                        startActivity(intent)
                    }

            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@ListRumah, ""+error.message, Toast.LENGTH_LONG).show()
            }
        })
    }
}
