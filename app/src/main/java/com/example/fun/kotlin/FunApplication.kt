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
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}