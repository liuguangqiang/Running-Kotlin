package com.liuguangqiang.irunning.act.main

import android.content.Intent
import android.os.Bundle
import android.support.design.internal.BottomNavigationItemView
import android.support.design.internal.BottomNavigationMenu
import android.support.design.internal.BottomNavigationMenuView
import com.liuguangqiang.irunning.R
import com.liuguangqiang.irunning.app.BaseActivity
import com.liuguangqiang.irunning.di.component.DaggerMainComponent
import com.liuguangqiang.irunning.di.module.MainModule
import com.liuguangqiang.irunning.service.RunningService
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import java.lang.reflect.AccessibleObject.setAccessible
import java.lang.reflect.Array.setBoolean
import android.support.design.widget.BottomNavigationView
import android.util.Log
import com.liuguangqiang.irunning.extension.disableShiftMode


class MainActivity : BaseActivity(), MainContract.View {

    @Inject lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.test()
        startService(Intent(this, RunningService::class.java))
        tvTest.setText(R.string.app_name)


//        var menuView: BottomNavigationMenuView = bottom_menu.getChildAt(0) as BottomNavigationMenuView
//        for (i in 0..menuView.childCount) {
//            val itemView = menuView.getChildAt(i) as BottomNavigationItemView
//            itemView.setShiftingMode(false)
//            itemView.setChecked(false)
//        }
//        disableShiftMode(bottom_menu)
        bottom_menu.disableShiftMode()
    }

//    fun disableShiftMode(view: BottomNavigationView) {
//        val menuView = view.getChildAt(0) as BottomNavigationMenuView
//        try {
//            val shiftingMode = menuView.javaClass.getDeclaredField("mShiftingMode")
//            shiftingMode.isAccessible = true
//            shiftingMode.setBoolean(menuView, false)
//            shiftingMode.isAccessible = false
//            for (i in 0..menuView.childCount - 1) {
//                val item = menuView.getChildAt(i) as BottomNavigationItemView
//
//                item.setShiftingMode(false)
//                // set once again checked value, so view will be updated
//
//                item.setChecked(item.itemData.isChecked)
//            }
//        } catch (e: NoSuchFieldException) {
//            Log.e("BNVHelper", "Unable to get shift mode field", e)
//        } catch (e: IllegalAccessException) {
//            Log.e("BNVHelper", "Unable to change value of shift mode", e)
//        }
//
//    }


    override fun inject() {
        DaggerMainComponent.builder().mainModule(MainModule(this)).build().inject(this)
    }

    override fun testView() {
        TODO("not implemented")
    }
}
