package com.example.`fun`.kotlin

import android.util.Log
import kotlin.properties.Delegates
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

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
 * 定义一个吃货的接口
 */
interface CanEat {
    //kotlin 接口可以定义未初始化的参数
    var eatWhat:String
    /**
     * 类型
     */
    var objectType: String
    /**
     * 最喜欢吃的
     */
    var favoriteType: String

    fun eat()
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
 * 动物爱吃的
 */
class AnimalEat : CanEat {

    override var eatWhat: String  by Delegates.observable("未知") { _, old, new ->
        Log.i(javaClass.name, "吃什么从“{$old}”变成“{$new}")
    }


    override var objectType: String  by Delegates.observable("未知") { _, old, new ->
        Log.i(javaClass.name, "类型从“{$old}”变成“{$new}")
    }


    override var favoriteType: String  by Delegates.observable("未知") { _, old, new ->
        Log.i(javaClass.name, "{$objectType}最爱吃的从“{$old}”变成“{$new}")
    }

    override fun eat() {
        Log.i(javaClass.name, "{$objectType}通过嘴巴吃：{$eatWhat}")
    }
}


/**
 * 将Bird的飞行方式委托给AnimalWithWings，将动物爱吃的委托给AnimalEat
 */
class Bird : CanFly by AnimalWithWings(), CanEat by AnimalEat()

/**
 * 将Bat的飞行方式委托给AnimalWithWings，将动物爱吃的委托给AnimalEat
 */
class Bat : CanFly by AnimalWithWings(), CanEat by AnimalEat()

/**
 * 将Plane的飞行方式委托给MachineWithPower
 */
class Plane : CanFly by MachineWithPower()


object DelegatesExt {
    fun <T> notNullSingleValue(): ReadWriteProperty<Any?, T> = NotNullSingleValueVar()
}

/**
 * 自定义委托
 */
private class NotNullSingleValueVar<T> : ReadWriteProperty<Any?, T> {

    private var value: T? = null

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return value ?: throw IllegalStateException("${property.name} not initialized")
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        this.value = if (this.value == null) value
        else throw IllegalStateException("${property.name} already initialized")
    }
}