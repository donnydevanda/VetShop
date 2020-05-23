package com.devanda.vetshop.Home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.devanda.vetshop.R
import kotlinx.android.synthetic.main.activity_list_detail.*

class ListDetail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_detail)

        val data = intent.getParcelableExtra<Doctor>("data")

        listdet_top_title.text = data?.nama
        alamat_desc.text = data?.alamat
        jam_desc.text = data?.praktek
        info_desc.text = data?.info
        Glide.with(this)
            .load(data?.images)
            .into(listdet_top_photo)
        val nomor = data?.telefon

        listdet_back_btn.setOnClickListener{
            val intent = Intent(this@ListDetail,
                List::class.java)
            startActivity(intent)
            finish()
        }

        listdet_back_text.setOnClickListener{
            val intent = Intent(this@ListDetail,
                List::class.java)
            startActivity(intent)
            finish()
        }

        listdest_wa.setOnClickListener{
            val url = "https://api.whatsapp.com/send?phone=$nomor"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }
    }
}
