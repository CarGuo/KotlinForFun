package com.example.`fun`.kotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        val listData = listOf("委托模式")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainListView.adapter = ArrayAdapter<String>(this, R.layout.layout_main_list_item, listData)
        mainListView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            when (position) {
                0 -> {
                    val bird = Bird()
                    val bat = Bat()
                    bird.fly()
                    bat.fly()
                }
            }
        }

    }
}

