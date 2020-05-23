package com.devanda.vetshop.Profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.devanda.vetshop.Main
import com.devanda.vetshop.R
import kotlinx.android.synthetic.main.activity_succes.*

class Success : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_succes)

        success_selesai.setOnClickListener {
            val intent = Intent(
                this@Success,
                Main::class.java
            )
            startActivity(intent)
        }
    }
}
