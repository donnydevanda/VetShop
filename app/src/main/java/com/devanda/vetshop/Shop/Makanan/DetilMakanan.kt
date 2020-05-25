package com.devanda.vetshop.Shop.Makanan

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.devanda.vetshop.R
import kotlinx.android.synthetic.main.activity_detil_makanan.*

class DetilMakanan : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detil_makanan)

        val data = intent.getParcelableExtra<Makanan>("data")

        detm_nama.text = data?.nama
        detm_harga.text = data?.harga
        detm_desc.text = data?.deskripsi
        Glide.with(this)
            .load(data?.images)
            .into(detm_pic)

        detm_back_btn.setOnClickListener{
            finish()
        }

        detm_back_text.setOnClickListener{
            finish()
        }

        detm_button_buy.setOnClickListener{
            val nomor = "628176010002"
            val url = "https://api.whatsapp.com/send?phone=$nomor"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }
    }
}
