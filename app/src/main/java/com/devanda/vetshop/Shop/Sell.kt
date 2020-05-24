package com.devanda.vetshop.Shop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListPopupWindow
import android.widget.Spinner
import com.devanda.vetshop.R
import kotlinx.android.synthetic.main.activity_sell.*

class Sell : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sell)

        val list : MutableList<String> = ArrayList()
        list.add("Obat Hewan")
        list.add("Makanan Hewan")
        list.add("Mainan Hewan")
        list.add("Rumah Hewan")
        val adapter  = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, list)
        sell_kategori.adapter = adapter
    }
}
