package com.devanda.vetshop.Shop

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.devanda.vetshop.R
import com.devanda.vetshop.Shop.Kategori.KategoriMainan
import kotlinx.android.synthetic.main.fragment_shop.*

/**
 * A simple [Fragment] subclass.
 */
class ShopFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shop, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //Mainan
        kategori_mainan.setOnClickListener {
            val i = Intent(activity, KategoriMainan::class.java)
            startActivity(i)
            (activity as Activity?)!!
        }
    }
}
