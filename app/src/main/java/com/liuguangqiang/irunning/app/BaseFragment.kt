package com.liuguangqiang.irunning.app

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife

/**
 * Created by Eric on 2017/6/15.
 */
abstract class BaseFragment : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        inject()
        onCreated(arguments)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layoutId = getContentLayout()
        return if (layoutId > 0) {
            val rootView = inflater!!.inflate(layoutId, container, false)
            ButterKnife.bind(this, rootView)
            rootView
        } else {
            super.onCreateView(inflater, container, savedInstanceState)
        }
    }

    abstract fun onCreated(bundle: Bundle?)

    abstract fun getContentLayout(): Int

    fun inject() {

    }

}
