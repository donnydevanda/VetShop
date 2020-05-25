package com.devanda.vetshop.Shop.Obat

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.devanda.vetshop.R
import com.devanda.vetshop.Shop.Makanan.Makanan
import kotlinx.android.synthetic.main.activity_detil_makanan.*
import kotlinx.android.synthetic.main.activity_detil_obat.*

class DetilObat : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detil_obat)

        val data = intent.getParcelableExtra<Obat>("data")

        deto_nama.text = data?.nama
        deto_harga.text = data?.harga
        deto_desc.text = data?.deskripsi
        Glide.with(this)
            .load(data?.images)
            .into(deto_pic)

        deto_back_btn.setOnClickListener{
            finish()
        }

        deto_back_text.setOnClickListener{
            finish()
        }

        deto_button_buy.setOnClickListener{
            val nomor = "628176010002"
            val url = "https://api.whatsapp.com/send?phone=$nomor"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }
    }
}
