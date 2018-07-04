package com.brianroper.andevweekly

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initAdapter()
    }

    private fun initAdapter(){
        home_recycler.layoutManager = LinearLayoutManager(this)
        home_recycler.adapter = HomeScreenAdapter(configHomeItems(), this)
    }

    private fun configHomeItems(): ArrayList<HomeItem> {
        val homeItems: ArrayList<HomeItem> = ArrayList()
        val homeItem = HomeItem("Android Weekly", R.drawable.logo)
        homeItems.add(homeItem)
        return homeItems
    }
}
