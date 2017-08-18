package com.liuguangqiang.irunning.extension

import android.os.Build
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity

/**
 * Created by eric on 18/8/2017.
 */
fun AppCompatActivity.setStatusBarColor(color: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        window.statusBarColor = ContextCompat.getColor(this, color)
    }
}