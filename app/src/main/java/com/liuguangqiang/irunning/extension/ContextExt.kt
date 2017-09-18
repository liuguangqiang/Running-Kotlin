package com.liuguangqiang.irunning.extension

import android.content.Context
import android.support.v4.app.Fragment
import android.widget.Toast

/**
 * Created by Eric on 2017/5/19.
 */

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun Context.toast(resId: Int) {
    Toast.makeText(this, resId, Toast.LENGTH_LONG).show()
}

fun Fragment.toast(message: String) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}

fun Fragment.toast(resId: Int) {
    Toast.makeText(context, resId, Toast.LENGTH_LONG).show()
}