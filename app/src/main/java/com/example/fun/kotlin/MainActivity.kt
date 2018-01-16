package com.example.`fun`.kotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        val listData = listOf("委托模式", "拓展函数", "集合操作符")
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
                2 -> {
                    setOperatorFun()
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
        Log.i(javaClass.name, "第一次打印鸟代理飞行类型: " + bird.type)
        Log.i(javaClass.name, "第二次打印鸟代理飞行类型: " + bird.type)
        //设置委托观察属性
        bird.flyObject = "鸟"
        bird.objectType = "鸟"
        bird.eatWhat = "虫子"
        bird.favoriteType = "虫子"
        bird.eat()

        bat.fly()
        bat.flyObject = "蝙蝠"
        bat.objectType = "蝙蝠"
        bat.eatWhat = "血"
        bat.favoriteType = "血"
        bat.eat()


        plane.fly()
        plane.flyObject = "飞机"
        plane.flyObject = "未知"

        plane.eat()
    }

    /**
     * 拓展函数
     */
    private fun expendFun() {
        expendToast("拓展了Context的吐司")
    }

    /**
     * 集合操作符
     */
    private fun setOperatorFun() {
        val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12,
                13, 14, 151, 6, 32, 231, 4564, 23, 23, 4, 645)
        //是否有任意数据符合
        Log.i(javaClass.name, "(any operator it % 2) " + list.any { it % 2 == 0 })
        //没有任何数据符合
        Log.i(javaClass.name, "(none operator it % 7 == 0) " + list.none { it % 7 == 0 })
        //所有数据是否符合
        Log.i(javaClass.name, "(all operator it < 10) " + list.all { it < 10 })
        //符合条件的数量
        Log.i(javaClass.name, "(count operator it % 3 == 0) " + list.count { it % 3 == 0 })
        //从4开始累计数
        Log.i(javaClass.name, "(fold operator 4 total) " + list.fold(4) { total, next -> total + next })
        //最大最小
        Log.i(javaClass.name, "(max min operator) " + list.max() + " " + list.min())
        //根据给定函数从第一项开始去掉指定元素的列表
        Log.i(javaClass.name, "(dropWhile operator) " + list.dropWhile { it < 3 })
        //根据给定函数从第一项开始返回指定元素的列表
        Log.i(javaClass.name, "(takeWhile operator) " + list.takeWhile { it < 23 })
        //过滤
        Log.i(javaClass.name, "(filter operator) " + list.filter { it % 2 == 0 })
        //每一个元素根据给定的函数转换
        Log.i(javaClass.name, "(map operator) " + list.map { it * 2 })
        //遍历所有的元素，为每一个创建一个集合，最后把所有的集合放在一个集合中
        Log.i(javaClass.name, "(flatMap operator) " + list.flatMap { listOf(it, it + 1) })
        //根据给定函数分组后的map
        Log.i(javaClass.name, "(groupBy operator) " + list.groupBy { if (it % 2 == 0) "even" else "odd" })
        //一个给定的集合分割成两个
        Log.i(javaClass.name, "(partition operator) " + list.partition { it % 2 == 0 })
        //合并
        Log.i(javaClass.name, "(plus operator) " + list + listOf(7, 8))
        //返回由pair	组成的List，每个 pair由两个集合中相同index的元素组成
        Log.i(javaClass.name, "(zip operator) " + list.zip(listOf(999, 888)))
        //从包含pair的List中生成包含List的Pair
        Log.i(javaClass.name, "(unzip operator) " + listOf(Pair(5, 7), Pair(6, 8)).unzip())
        //排序
        Log.i(javaClass.name, "(sorted operator) " + list.sorted())
        //根据条件排序
        Log.i(javaClass.name, "(sortedBy operator) " + list.sortedBy { it % 3 })

    }

}

