package com.devanda.vetshop.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.devanda.vetshop.Main
import com.devanda.vetshop.R
import com.devanda.vetshop.Utils.Preferences
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_on_boarding2.*

class Login : AppCompatActivity() {

    lateinit var iUsername :String
    lateinit var iPassword :String

    lateinit var mDatabase: DatabaseReference
    lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mDatabase = FirebaseDatabase.getInstance().getReference("User")
        preferences = Preferences(this)

        preferences.setValues("onboarding", "1")
        if (preferences.getValues("status").equals("1")) {
            finishAffinity()

            val intent = Intent(this@Login,
                Main::class.java)
            startActivity(intent)
        }

        login_masuk.setOnClickListener{
            iUsername = login_username.text.toString()
            iPassword = login_password.text.toString()

            if (iUsername == "") {
                login_username.error = "Isi Username"
                login_username.requestFocus()
            } else if (iPassword == "") {
                login_password.error = "Isi Password"
                login_password.requestFocus()
            } else {
                pushLogin(iUsername, iPassword)
            }
        }

        login_daftar.setOnClickListener{
            val intent = Intent(this@Login,
                Register::class.java)
            startActivity(intent)
        }
    }

    private fun pushLogin(iUsername: String, iPassword: String) {
        mDatabase.child(iUsername).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                val user = dataSnapshot.getValue(User::class.java)
                if (user == null) {
                    Toast.makeText(this@Login, "Username / Password salah", Toast.LENGTH_LONG).show()

                } else {
                    if (user.password.equals(iPassword)){
                        Toast.makeText(this@Login, "Selamat Datang!", Toast.LENGTH_LONG).show()
                        preferences.setValues("email", user.email.toString())
                        preferences.setValues("nomor", user.nomor.toString())
                        preferences.setValues("username", user.username.toString())
                        preferences.setValues("status", "1")

                        finishAffinity()

                        val intent = Intent(this@Login,
                            Main::class.java)
                        startActivity(intent)

                    } else {
                        Toast.makeText(this@Login, "Username / Password salah", Toast.LENGTH_LONG).show()
                    }

                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@Login, ""+error.message, Toast.LENGTH_LONG).show()
            }
        })
    }
}
