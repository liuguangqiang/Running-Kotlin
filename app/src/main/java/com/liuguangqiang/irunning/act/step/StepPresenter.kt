package com.liuguangqiang.irunning.act.step

import com.liuguangqiang.irunning.data.entity.CreateResponse
import com.liuguangqiang.irunning.data.entity.Step
import com.liuguangqiang.irunning.data.service.StepService
import com.liuguangqiang.irunning.utils.LoginManager
import com.liuguangqiang.support.utils.Logger
import com.liuguangqiang.support.utils.TimeUtils
import io.realm.Realm
import rx.Observer
import javax.inject.Inject

/**
 * Created by eric on 16/8/2017.
 */
class StepPresenter : StepContract.Presenter {

    var view: StepContract.View

    private var realm: Realm? = null

    @Inject lateinit var service: StepService

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

    override fun uploadSteps(count: Int) {
        service.post(LoginManager.instance.user!!.id, count, getDateTime())
                .subscribe(object : Observer<CreateResponse> {
                    override fun onNext(createResponse: CreateResponse?) {
                        Logger.d("onNext")
                    }

                    override fun onError(p0: Throwable?) {
                        Logger.d("onError:" + p0.toString())
                    }

                    override fun onCompleted() {
                    }
                })
    }

}