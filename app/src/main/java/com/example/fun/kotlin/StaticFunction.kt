package com.example.`fun`.kotlin

import android.content.Context
import android.widget.Toast

/**
 * 扩展函数
 * Created by guoshuyu on 2017/11/7.
 */

fun Context.expendToast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}