package com.liuguangqiang.irunning.act.steps

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.liuguangqiang.irunning.R
import com.liuguangqiang.irunning.adapter.StepsAdapter
import com.liuguangqiang.irunning.data.entity.Step
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.android.synthetic.main.activity_steps.*

class StepsActivity : AppCompatActivity() {

    var data: ArrayList<Step> = ArrayList()
    lateinit var adapter: StepsAdapter
    private var realm: Realm? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_steps)

        realm = Realm.getDefaultInstance()
        adapter = StepsAdapter(this, data)
        rvSteps.layoutManager = LinearLayoutManager(this)
        rvSteps.adapter = adapter

        mockup()
    }

    fun mockup() {
        var results: RealmResults<Step> = realm!!.where(Step::class.java).findAll()
        data.addAll(realm!!.copyFromRealm(results))
        adapter.notifyDataSetChanged()
    }

}
