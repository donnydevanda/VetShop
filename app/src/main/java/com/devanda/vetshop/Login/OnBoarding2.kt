package com.devanda.vetshop.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.devanda.vetshop.R
import kotlinx.android.synthetic.main.activity_on_boarding1.*
import kotlinx.android.synthetic.main.activity_on_boarding2.*

class OnBoarding2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding2)

        onboarding2_masuk.setOnClickListener{
            val intent = Intent(this@OnBoarding2,
                Login::class.java)
            startActivity(intent)
        }
    }
}
