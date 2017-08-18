package com.liuguangqiang.irunning.act.steps

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.liuguangqiang.irunning.R
import com.liuguangqiang.irunning.adapter.StepsAdapter
import com.liuguangqiang.irunning.app.ToolbarActivity
import com.liuguangqiang.irunning.data.entity.Step
import kotlinx.android.synthetic.main.activity_steps.*
import javax.inject.Inject

class StepsActivity : ToolbarActivity(), StepsContract.View {

    var data: ArrayList<Step> = ArrayList()
    private lateinit var adapter: StepsAdapter
    @Inject lateinit var presenter: StepsContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_steps)
        setTitle(R.string.title_steps)

        adapter = StepsAdapter(this, data)
        rvSteps.layoutManager = LinearLayoutManager(this)
        rvSteps.adapter = adapter

        presenter.getSteps()
    }

    override fun inject() {
    }

    override fun showSteps(steps: List<Step>) {
        data.addAll(steps)
    }
}
