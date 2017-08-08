package com.liuguangqiang.irunning.extension

import android.support.design.internal.BottomNavigationItemView
import android.support.design.internal.BottomNavigationMenuView
import android.support.design.widget.BottomNavigationView
import com.liuguangqiang.support.utils.Logger

/**
 * Disable the shift mode of a BottomNavigationView.
 *
 * Created by Eric on 2017/6/15.
 */
fun BottomNavigationView.disableShiftMode() {
    val menuView = getChildAt(0) as BottomNavigationMenuView
    try {
        val shiftingMode = menuView.javaClass.getDeclaredField("mShiftingMode")
        shiftingMode.isAccessible = true
        shiftingMode.setBoolean(menuView, false)
        shiftingMode.isAccessible = false
        for (i in 0..menuView.childCount - 1) {
            val item = menuView.getChildAt(i) as BottomNavigationItemView

            item.setShiftingMode(false)
            item.setChecked(item.itemData.isChecked)
        }
    } catch (e: NoSuchFieldException) {
        Logger.e("Unable to get shift mode field", e)
    } catch (e: IllegalAccessException) {
        Logger.e("Unable to change value of shift mode", e)
    }
}