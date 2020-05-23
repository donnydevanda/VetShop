package com.devanda.vetshop.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.devanda.vetshop.R
import kotlinx.android.synthetic.main.activity_on_boarding1.*

class OnBoarding1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding1)

        onboarding1_lanjutkan.setOnClickListener{
            val intent = Intent(this@OnBoarding1,
                OnBoarding2::class.java)
            startActivity(intent)
        }
    }
}
