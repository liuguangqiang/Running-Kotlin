package com.liuguangqiang.irunning.app

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import com.liuguangqiang.irunning.R
import kotlinx.android.synthetic.main.activity_toolbar.*

/**
 * Created by eric on 18/8/2017.
 */
abstract class ToolbarActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        super.setContentView(R.layout.activity_toolbar)
        initToolbar()
    }

    override fun setContentView(layoutResID: Int) {
        val view = LayoutInflater.from(this).inflate(layoutResID, null)
        container.addView(view)
    }

    private fun initToolbar() {
        toolbar!!.setTitleTextColor(Color.WHITE)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(true)
    }

    override fun setTitle(title: CharSequence) {
        toolbar.title = title
    }

    override fun setTitle(resId: Int) {
        toolbar.title = title
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}