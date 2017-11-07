package com.example.`fun`.kotlin

import android.util.Log

/**
 * 委托模式
 * Created by guoshuyu on 2017/11/7.
 */

/**
 * 定义一个可以飞的接口
 */
interface CanFly {

    val lazyType: String

    fun fly()
}

/**
 * 动物飞行方式，扇动翅膀
 */
class AnimalWithWings : CanFly {

    /**
     * 代理属性
     */
    override val lazyType: String by lazy {
        Log.i(javaClass.name, "属性初始化")
        "动物"
    }

    override fun fly() {
        Log.i(javaClass.name, "动物抖动翅膀！！")
    }
}

/**
 * 机器飞行方式，拥有动力
 */
class MachineWithPower : CanFly {

    /**
     * 代理属性
     */
    override val lazyType: String  by lazy {
        Log.i(javaClass.name, "属性初始化")
        "机器"
    }

    override fun fly() {
        Log.i(javaClass.name, "机械使用动力飞行！！")
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