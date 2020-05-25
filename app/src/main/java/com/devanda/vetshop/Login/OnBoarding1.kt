package com.devanda.vetshop.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.devanda.vetshop.Main
import com.devanda.vetshop.R
import com.devanda.vetshop.Utils.Preferences
import kotlinx.android.synthetic.main.activity_on_boarding1.*

class OnBoarding1 : AppCompatActivity() {

    lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding1)

        preferences = Preferences(this)

        if (preferences.getValues("onboarding").equals("1")) {
            finishAffinity()

            val intent = Intent(this@OnBoarding1,
                Login::class.java)
            startActivity(intent)
        }

        onboarding1_lanjutkan.setOnClickListener{
            val intent = Intent(this@OnBoarding1,
                OnBoarding2::class.java)
            startActivity(intent)
        }
    }
}
