package com.example.`fun`.kotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        val listData = listOf("委托模式", "拓展函数")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainListView.adapter = ArrayAdapter<String>(this, R.layout.layout_main_list_item, listData)
        mainListView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            when (position) {
                0 -> {
                    delegateFun()
                }
                1 -> {
                    expendFun()
                }
            }
        }
    }

    /**
     * 委托代理demo
     */
    private fun delegateFun() {
        val bird = Bird()
        val bat = Bat()
        val plane = Plane()

        //调用代理方法
        bird.fly()
        //两次打印内代理属性，查看输出
        Log.i(javaClass.name, "第一次打印代理飞行类型: " + bird.type)
        Log.i(javaClass.name, "第二次打印代理飞行类型: " + bird.type)
        //设置委托观察属性
        bird.flyObject = "鸟"

        bat.fly()
        bat.flyObject = "蝙蝠"

        plane.fly()
        plane.flyObject = "飞机"
        plane.flyObject = "未知"
    }

    /**
     * 拓展函数
     */
    private fun expendFun() {
        expendToast("拓展了Context的吐司")
    }

}

