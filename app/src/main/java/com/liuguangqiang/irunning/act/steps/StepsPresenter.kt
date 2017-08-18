package com.liuguangqiang.irunning.act.steps

import com.liuguangqiang.irunning.data.entity.Step
import io.realm.Realm
import io.realm.RealmResults
import javax.inject.Inject

/**
 * Created by eric on 18/8/2017.
 */
class StepsPresenter : StepsContract.Presenter {

    var view: StepsContract.View

    private var realm: Realm? = null

    @Inject
    constructor(view: StepsContract.View) {
        this.view = view
        realm = Realm.getDefaultInstance()
    }

    override fun getSteps() {
        var results: RealmResults<Step> = realm!!.where(Step::class.java).findAll()
        view.showSteps(realm!!.copyFromRealm(results))
    }
}