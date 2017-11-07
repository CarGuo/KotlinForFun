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
    fun fly()
}

/**
 * 飞行方式一：翅膀
 */
class Wings {
    fun move() {
        Log.i(javaClass.name, "抖动翅膀！！")
    }
}

/**
 * 动物飞行方式，扇动翅膀
 */
class AnimalWithWings : CanFly {
    private val wings: Wings = Wings()
    override fun fly() = wings.move()
}

/**
 * 将Bird的飞行方式委托给AnimalWithWings
 */
class Bird : CanFly by AnimalWithWings()

/**
 * 将Bat的飞行方式委托给AnimalWithWings
 */
class Bat : CanFly by AnimalWithWings()