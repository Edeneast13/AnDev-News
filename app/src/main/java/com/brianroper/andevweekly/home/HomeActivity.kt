package com.brianroper.andevweekly.home

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import com.brianroper.andevweekly.R
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        handleToolbarBehavior(home_toolbar)
        initAdapter()
    }

    private fun initAdapter(){
        home_recycler.layoutManager = LinearLayoutManager(this)
        home_recycler.adapter = HomeScreenAdapter(configHomeItems(), this)
    }

    private fun configHomeItems(): ArrayList<HomeItem> {
        val homeItems: ArrayList<HomeItem> = ArrayList()
        val homeItem = HomeItem("Android Weekly", R.drawable.android_weekly)
        homeItems.add(homeItem)
        return homeItems
    }

    /**
     * handles toolbar behavior
     */
    fun handleToolbarBehavior(toolbar: Toolbar) {
        setSupportActionBar(toolbar)
        supportActionBar!!.setLogo(R.drawable.logo)
    }
}
