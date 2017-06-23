package com.liuguangqiang.irunning.act.step

import android.os.Bundle
import com.liuguangqiang.irunning.R
import com.liuguangqiang.irunning.app.BaseFragment
import com.liuguangqiang.irunning.utils.event.StepEvent
import kotlinx.android.synthetic.main.fragment_step.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

/**
 * 记步.
 * Created by Eric on 2017/6/15.
 */
class StepFragment : BaseFragment() {

    override fun onCreated(bundle: Bundle?) {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }
    }

    override fun getContentLayout(): Int {
        return R.layout.fragment_step
    }

    @Subscribe
    fun onStepChanged(stepEvent: StepEvent) {
        tvSteps.text = "" + stepEvent.count
    }

}