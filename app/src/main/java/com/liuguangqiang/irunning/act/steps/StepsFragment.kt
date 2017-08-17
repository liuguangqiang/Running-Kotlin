package com.liuguangqiang.irunning.act.steps

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.liuguangqiang.irunning.app.BaseFragment
import kotlinx.android.synthetic.main.fragment_steps.*

/**
 * Created by eric on 17/8/2017.
 */
class StepsFragment : BaseFragment() {

    override fun getContentLayout(): Int {
        return 0
    }

    override fun onCreated(bundle: Bundle?) {
        rvSteps.layoutManager = LinearLayoutManager(context)
    }

}