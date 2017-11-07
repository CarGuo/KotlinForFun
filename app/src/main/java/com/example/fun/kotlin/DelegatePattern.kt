package com.example.`fun`.kotlin

import android.content.Context
import android.util.Log
import android.widget.Toast
import kotlin.properties.Delegates

/**
 * 委托模式
 * Created by guoshuyu on 2017/11/7.
 */

/**
 * 定义一个可以飞的接口
 */
interface CanFly {
    //kotlin 接口可以定义未初始化的参数
    /**
     * 飞行类型
     */
    val type: String
    /**
     * 飞行物
     */
    var flyObject: String

    fun fly()
}

/**
 * 动物飞行方式，扇动翅膀
 */
class AnimalWithWings : CanFly {

    /**
     * 代理属性，首次调用才初始化，线程安全
     */
    override val type: String by lazy {
        Log.i(javaClass.name, "飞行类型初始化")
        "动物"
    }
    /**
     * 委托观察
     */
    override var flyObject: String  by Delegates.observable("未知") { _, old, new ->
        Log.i(javaClass.name, "动物飞行物从“{$old}”变成“{$new}")
    }

    override fun fly() {
        Log.i(javaClass.name, "飞行方式：动物抖动翅膀！！")
    }
}

/**
 * 机器飞行方式，拥有动力
 */
class MachineWithPower : CanFly {

    /**
     * 代理属性，首次调用才初始化，线程不安全
     */
    override val type: String  by lazy(LazyThreadSafetyMode.NONE) {
        Log.i(javaClass.name, "飞行类型初始化")
        "机器"
    }
    /**
     * 委托观察
     */
    override var flyObject: String  by Delegates.vetoable("未知") { _, old, new ->
        val changed = new != "未知"
        if (changed)
            Log.i(javaClass.name, "机器飞行物尝试从“{$old}”变成“{$new}")
        else
            Log.i(javaClass.name, "赋值失败，“{$old}”机器飞行物无法变为“{$new}”")
        changed
    }

    override fun fly() {
        Log.i(javaClass.name, "飞行方式：机械使用动力飞行！！")
    }
}


/**
 * 将Bird的飞行方式委托给AnimalWithWings
 */
class Bird : CanFly by AnimalWithWings()

/**
 * 将Bat的飞行方式委托给AnimalWithWings
 */
class Bat : CanFly by AnimalWithWings()

/**
 * 将Plane的飞行方式委托给MachineWithPower
 */
class Plane : CanFly by MachineWithPower()
