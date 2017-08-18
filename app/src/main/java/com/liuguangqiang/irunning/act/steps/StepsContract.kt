package com.liuguangqiang.irunning.act.steps

import com.liuguangqiang.irunning.data.entity.Step

interface StepsContract {

    interface View {

        fun showSteps(steps: List<Step>)
    }

    interface Presenter {

        fun getSteps()
    }

}