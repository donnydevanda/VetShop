package com.devanda.vetshop.Shop.Mainan

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.devanda.vetshop.R
import kotlinx.android.synthetic.main.activity_detil_mainan.*

class DetilMainan : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detil_mainan)

        val data = intent.getParcelableExtra<Mainan>("data")

        detma_nama.text = data?.nama
        detma_harga.text = data?.harga
        detma_desc.text = data?.deskripsi
        Glide.with(this)
            .load(data?.images)
            .into(detma_pic)

        detma_back_btn.setOnClickListener{
            finish()
        }

        detma_back_text.setOnClickListener{
            finish()
        }

        detma_button_buy.setOnClickListener{
            val nomor = "628176010002"
            val url = "https://api.whatsapp.com/send?phone=$nomor"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }
    }
}
