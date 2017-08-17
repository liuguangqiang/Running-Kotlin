package com.liuguangqiang.irunning.act.step

import com.liuguangqiang.irunning.data.entity.Step
import com.liuguangqiang.support.utils.TimeUtils
import io.realm.Realm
import javax.inject.Inject

/**
 * Created by eric on 16/8/2017.
 */
class StepPresenter : StepContract.Presenter {

    var view: StepContract.View

    private var realm: Realm? = null

    @Inject
    constructor(view: StepContract.View) {
        this.view = view
        realm = Realm.getDefaultInstance()
    }

    private fun getDateTime(): String {
        return TimeUtils.getDateTimeByFormat("yyyy-MM-dd")
    }
    
    override fun getCurrentSteps() {
        var today: String? = getDateTime()
        var currentStep: Step? = realm?.where(Step::class.java)?.equalTo("date", today)?.findFirst()
        view.showCurrentSteps(currentStep!!)
    }

}