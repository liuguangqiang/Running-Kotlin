package com.liuguangqiang.irunning.act.step

import android.os.Bundle
import com.liuguangqiang.irunning.R
import com.liuguangqiang.irunning.app.BaseFragment
import com.liuguangqiang.irunning.data.entity.Step
import com.liuguangqiang.irunning.utils.event.StepEvent
import kotlinx.android.synthetic.main.fragment_step.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import javax.inject.Inject

/**
 * Steps
 *
 * Created by Eric on 2017/6/15.
 */
class StepFragment : BaseFragment(), StepContract.View {

    @Inject lateinit var presenter: StepContract.Presenter

    override fun onCreated(bundle: Bundle?) {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }

        DaggerStepComponent.builder().stepModule(StepModule(this)).build().inject(this)
        presenter.getCurrentSteps()
    }

    override fun getContentLayout(): Int {
        return R.layout.fragment_step
    }

    override fun showCurrentSteps(step: Step) {
        tvSteps.text = "" + step.count
    }

    @Subscribe
    fun onStepChanged(stepEvent: StepEvent) {
        tvSteps.text = "" + stepEvent.count
    }

}