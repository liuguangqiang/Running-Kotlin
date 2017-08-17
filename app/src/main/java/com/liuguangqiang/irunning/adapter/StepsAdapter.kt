package com.liuguangqiang.irunning.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.Bind
import com.liuguangqiang.irunning.R
import com.liuguangqiang.irunning.adapter.StepsAdapter.ViewHolder
import com.liuguangqiang.irunning.data.entity.Step

import com.liuguangqiang.irunning.widget.recyclerview.BaseAdapter

import butterknife.ButterKnife

/**
 * Created by eric on 17/8/2017.
 */
class StepsAdapter(context: Context, data: List<Step>) : BaseAdapter<Step, ViewHolder>(context, data) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder? {
        return ViewHolder(layoutInflater.inflate(R.layout.item_steps, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.bindData(data[position])
    }

    class ViewHolder(view: View)
        : RecyclerView.ViewHolder(view) {

        @Bind(R.id.tvTitle)
        lateinit var tvTitle: TextView

        init {
            ButterKnife.bind(this, itemView)
        }

        fun bindData(entity: Step) {
            tvTitle.text = "" + entity.count
        }
    }

}
