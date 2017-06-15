package com.liuguangqiang.irunning.extension

import android.content.Context
import android.util.Log
import android.widget.Toast

/**
 * Created by Eric on 2017/5/19.
 */

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}