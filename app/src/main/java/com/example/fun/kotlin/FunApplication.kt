package com.example.`fun`.kotlin

import android.app.Application
import kotlin.properties.Delegates

/**
 * Application
 * Created by guoshuyu on 2017/11/7.
 */
class FunApplication : Application() {

    companion object {
        //委托notNull，这个值在被获取之前没有被分配，它就会抛出一个异常。
        var instance: FunApplication by Delegates.notNull()

        //自定义委托的使用,限制外部不能修改这个值
        var instanceExt: FunApplication by DelegatesExt.notNullSingleValue()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        instanceExt = this
    }
}