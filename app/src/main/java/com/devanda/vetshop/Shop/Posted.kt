package com.devanda.vetshop.Shop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.devanda.vetshop.Main
import com.devanda.vetshop.R
import kotlinx.android.synthetic.main.activity_posted.*
import kotlinx.android.synthetic.main.activity_succes.*

class Posted : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posted)

        posted_selesai.setOnClickListener {
            val intent = Intent(
                this@Posted,
                Main::class.java
            )
            startActivity(intent)
        }
    }
}
