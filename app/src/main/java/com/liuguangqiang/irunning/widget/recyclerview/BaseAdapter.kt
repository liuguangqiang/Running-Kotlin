package com.liuguangqiang.irunning.widget.recyclerview

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.util.ArrayList

/**
 * A base adapter that is able to listen the click or long click events.
 *
 * Created by Eric on 2017/7/6.
 */
abstract class BaseAdapter<T, VH : RecyclerView.ViewHolder>(context: Context, data: List<T>) : RecyclerView.Adapter<VH>() {

    var data: List<T> = ArrayList()
    var layoutInflater: LayoutInflater

    var onItemClickListener: OnItemClickListener? = null
    var onItemLongClickListener: OnItemLongClickListener? = null

    init {
        this.data = data
        this.layoutInflater = LayoutInflater.from(context)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        if (holder.itemView != null) {
            if (onItemClickListener != null) {
                holder.itemView.setOnClickListener { v -> onItemClickListener!!.onItemClick(v, position) }
            }

            if (onItemLongClickListener != null) {
                holder.itemView.setOnLongClickListener { v ->
                    onItemLongClickListener!!.onItemLongClick(v, position)
                    false
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH? {
        return null
    }

    override fun onViewRecycled(holder: VH?) {
        super.onViewRecycled(holder)
    }

    interface OnItemClickListener {

        fun onItemClick(view: View, position: Int)
    }

    interface OnItemLongClickListener {

        fun onItemLongClick(view: View, position: Int)
    }

}
