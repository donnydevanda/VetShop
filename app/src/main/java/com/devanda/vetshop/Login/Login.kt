package com.devanda.vetshop.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.devanda.vetshop.Main
import com.devanda.vetshop.R
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_on_boarding2.*

class Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login_masuk.setOnClickListener{
            val intent = Intent(this@Login,
                Main::class.java)
            startActivity(intent)
        }

        login_daftar.setOnClickListener{
            val intent = Intent(this@Login,
                Register::class.java)
            startActivity(intent)
        }
    }
}
