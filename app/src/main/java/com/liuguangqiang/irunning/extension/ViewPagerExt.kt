package com.liuguangqiang.irunning.extension

import android.support.v4.view.ViewPager

/**
 * Created by eric on 17/8/2017.
 */

fun ViewPager.setScrollable(scrollable: Boolean) {
    this.setOnTouchListener { v, event -> !scrollable }
}