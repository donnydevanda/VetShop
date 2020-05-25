package com.devanda.vetshop.Shop.Rumah

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.devanda.vetshop.R
import kotlinx.android.synthetic.main.activity_detil_rumah.*

class DetilRumah : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detil_rumah)

        val data = intent.getParcelableExtra<Rumah>("data")

        detr_nama.text = data?.nama
        detr_harga.text = data?.harga
        detr_desc.text = data?.deskripsi
        Glide.with(this)
            .load(data?.images)
            .into(detr_pic)

        detr_back_btn.setOnClickListener{
            finish()
        }

        detr_back_text.setOnClickListener{
            finish()
        }

        detr_button_buy.setOnClickListener{
            val nomor = "628176010002"
            val url = "https://api.whatsapp.com/send?phone=$nomor"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }
    }
}
