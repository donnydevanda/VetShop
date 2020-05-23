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
import kotlinx.android.synthetic.main.activity_register.*

class Register : AppCompatActivity() {

    lateinit var sEmail:String
    lateinit var sPhone:String
    lateinit var sUsername:String
    lateinit var sPassword:String


    private lateinit var mFirebaseDatabase: DatabaseReference
    private lateinit var mFirebaseInstance: FirebaseDatabase
    private lateinit var mDatabase: DatabaseReference

    private lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mFirebaseInstance = FirebaseDatabase.getInstance()
        mDatabase = FirebaseDatabase.getInstance().getReference()
        mFirebaseDatabase = mFirebaseInstance.getReference("User")

        preferences = Preferences(this)

        register_masuk.setOnClickListener{
            sEmail = register_email.text.toString()
            sPhone = register_nomor.text.toString()
            sUsername = register_username.text.toString()
            sPassword = register_password.text.toString()


            if (sUsername.equals("")) {
                register_username.error = "Isi Username"
                register_username.requestFocus()
            } else if (sPassword.equals("")) {
                register_password.error = "Isi Password"
                register_password.requestFocus()
            } else if (sEmail.equals("")) {
                register_email.error = "Isi Email"
                register_email.requestFocus()
            } else if (sPhone.equals("")) {
                register_email.error = "Isi Nomor Handphone"
                register_email.requestFocus()
            } else {
                saveUser(sUsername, sPassword, sEmail, sPhone   )
            }
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

    private fun saveUser(sUsername: String?, sPassword: String?, sEmail: String?, sPhone: String?) {
        val user = User()
        user.email = sEmail
        user.nomor = sPhone
        user.username = sUsername
        user.password = sPassword

        if (sUsername != null) {
            checkingUsername(sUsername, user)
        }
    }

    private fun checkingUsername(iUsername: String, data: User) {
        mFirebaseDatabase.child(iUsername).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                val user = dataSnapshot.getValue(User::class.java)
                if (user == null) {
                    mFirebaseDatabase.child(iUsername).setValue(data)
                    preferences.setValues("email", data.email.toString())
                    preferences.setValues("nomor", data.nomor.toString())
                    preferences.setValues("username", data.username.toString())
                    preferences.setValues("password", data.password.toString())
                    preferences.setValues("status", "1")

                    val intent = Intent(this@Register,
                        Main::class.java)
                    startActivity(intent)

                } else {
                    Toast.makeText(this@Register, "Username telah digunakan", Toast.LENGTH_LONG).show()

                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@Register, ""+error.message, Toast.LENGTH_LONG).show()
            }
        })
    }
}
