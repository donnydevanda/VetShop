package com.devanda.vetshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.devanda.vetshop.Home.HomeFragment
import com.devanda.vetshop.Profile.ProfileFragment
import com.devanda.vetshop.Shop.ShopFragment
import kotlinx.android.synthetic.main.activity_main.*

class Main : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragmentHome = HomeFragment()
        val fragmentShop = ShopFragment()
        val fragmentProfile = ProfileFragment()

        setFragment(fragmentHome)

        nb_home.setOnClickListener {
            setFragment(fragmentHome)

            changeIcon(nb_home,
                R.drawable.navbar_home_on
            )
            changeIcon(nb_cart,
                R.drawable.navbar_cart_off
            )
            changeIcon(nb_user,
                R.drawable.navbar_user_off
            )
        }

        nb_cart.setOnClickListener {
            setFragment(fragmentShop)

            changeIcon(nb_home,
                R.drawable.navbar_home_off
            )
            changeIcon(nb_cart,
                R.drawable.navbar_cart_on
            )
            changeIcon(nb_user,
                R.drawable.navbar_user_off
            )
        }

        nb_user.setOnClickListener {
            setFragment(fragmentProfile)

            changeIcon(nb_home,
                R.drawable.navbar_home_off
            )
            changeIcon(nb_cart,
                R.drawable.navbar_cart_off
            )
            changeIcon(nb_user,
                R.drawable.navbar_user_on
            )
        }
    }

    protected fun setFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.layout_frame, fragment)
        fragmentTransaction.commit()
    }

    private fun changeIcon(imageView: ImageView, int: Int){
        imageView.setImageResource(int)
    }
}
