package com.devanda.vetshop.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.devanda.vetshop.Main
import com.devanda.vetshop.R
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*

class Register : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        register_masuk.setOnClickListener{
            val intent = Intent(this@Register,
                Main::class.java)
            startActivity(intent)
        }

        register_back_btn.setOnClickListener{
            val intent = Intent(this@Register,
                Login::class.java)
            startActivity(intent)
        }

        register_back_text.setOnClickListener{
            val intent = Intent(this@Register,
                Login::class.java)
            startActivity(intent)
        }
    }
}
