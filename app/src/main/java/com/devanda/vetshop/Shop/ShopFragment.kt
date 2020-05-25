package com.devanda.vetshop.Shop

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.devanda.vetshop.R
import com.devanda.vetshop.Shop.Makanan.Makanan
import com.devanda.vetshop.Shop.Mainan.ListMainan
import com.devanda.vetshop.Shop.Makanan.ListMakanan
import com.devanda.vetshop.Shop.Obat.ListObat
import com.devanda.vetshop.Shop.Rumah.ListRumah
import com.devanda.vetshop.Utils.Preferences
import com.google.firebase.database.DatabaseReference
import kotlinx.android.synthetic.main.fragment_shop.*
import java.util.ArrayList

class ShopFragment : Fragment() {

    private lateinit var preferences: Preferences
    lateinit var mDatabase: DatabaseReference

    private var dataList = ArrayList<Makanan>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shop, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //Obat
        kategori_obat.setOnClickListener {
            val i = Intent(activity, ListObat::class.java)
            startActivity(i)
            (activity as Activity?)!!
        }

        //Mainan
        kategori_makanan.setOnClickListener {
            val i = Intent(activity, ListMakanan::class.java)
            startActivity(i)
            (activity as Activity?)!!
        }

        //Mainan
        kategori_mainan.setOnClickListener {
            val i = Intent(activity, ListMainan::class.java)
            startActivity(i)
            (activity as Activity?)!!
        }

        //Rumah
        kategori_rumah.setOnClickListener {
            val i = Intent(activity, ListRumah::class.java)
            startActivity(i)
            (activity as Activity?)!!
        }



    }
}
