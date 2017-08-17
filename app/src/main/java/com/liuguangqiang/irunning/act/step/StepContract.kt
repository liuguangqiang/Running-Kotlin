package com.liuguangqiang.irunning.act.step

import com.liuguangqiang.irunning.data.entity.Step

/**
 * Created by eric on 16/8/2017.
 */
interface StepContract {

    interface View {
        fun showCurrentSteps(step: Step)
    }

    interface Presenter {
        fun getCurrentSteps()
    }

}