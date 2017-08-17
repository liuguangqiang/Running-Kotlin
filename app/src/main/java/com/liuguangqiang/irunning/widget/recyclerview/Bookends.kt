package com.liuguangqiang.irunning.widget.recyclerview

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import java.util.ArrayList

/**
 *
 * A RecyclerView.Adapter that allows for headers and footers as well.
 *
 *
 * This class wraps a base adapter that's passed into the constructor. It works by creating extra view items types
 * that are returned in [.getItemViewType], and mapping these to the header and footer views provided via
 * [.addHeader] and [.addFooter].
 *
 *
 * There are two restrictions when using this class:
 *
 *
 * 1) The base adapter can't use negative view types, since this class uses negative view types to keep track
 * of header and footer views.
 *
 *
 * 2) You can't add more than 1000 headers or footers.
 *
 */
class Bookends<T : RecyclerView.Adapter<*>>
/**
 * Constructor.

 * @param base the adapter to wrap
 */
(
        /**
         * Gets the base adapter that this is wrapping.
         */
        val wrappedAdapter: T) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val mHeaders = ArrayList<View>()
    private val mFooters = ArrayList<View>()

    /**
     * Adds a header view.
     */
    fun addHeader(view: View) {
        if (view == null) {
            throw IllegalArgumentException("You can't have a null header!")
        }
        mHeaders.add(view)
    }

    /**
     * Adds a footer view.
     */
    fun addFooter(view: View) {
        if (view == null) {
            throw IllegalArgumentException("You can't have a null footer!")
        }
        mFooters.add(view)
    }

    fun removeHeader(view: View) {
        if (view == null) {
            throw IllegalArgumentException("You can't remove a null header!")
        }
        mHeaders.remove(view)
    }

    fun removeFooter(view: View) {
        if (view == null) {
            throw IllegalArgumentException("You can't remove a null footer!")
        }
        mFooters.remove(view)
    }

    /**
     * Toggles the visibility of the header views.
     */
    fun setHeaderVisibility(shouldShow: Boolean) {
        for (header in mHeaders) {
            header.visibility = if (shouldShow) View.VISIBLE else View.GONE
        }
    }

    fun setHeaderVisibility(view: View, shouldShow: Boolean) {
        for (header in mHeaders) {
            if (header === view) {
                header.visibility = if (shouldShow) View.VISIBLE else View.GONE
            }
        }
    }

    /**
     * Toggles the visibility of the footer views.
     */
    fun setFooterVisibility(shouldShow: Boolean) {
        for (footer in mFooters) {
            footer.visibility = if (shouldShow) View.VISIBLE else View.GONE
        }
    }

    fun setFooterVisibility(view: View, shouldShow: Boolean) {
        for (footer in mFooters) {
            if (footer === view) {
                footer.visibility = if (shouldShow) View.VISIBLE else View.GONE
            }
        }
    }

    /**
     * @return the number of headers.
     */
    val headerCount: Int
        get() = mHeaders.size

    /**
     * @return the number of footers.
     */
    val footerCount: Int
        get() = mFooters.size

    /**
     * Gets the indicated header, or null if it doesn't exist.
     */
    fun getHeader(i: Int): View? {
        return if (i < mHeaders.size) mHeaders[i] else null
    }

    /**
     * Gets the indicated footer, or null if it doesn't exist.
     */
    fun getFooter(i: Int): View? {
        return if (i < mFooters.size) mFooters[i] else null
    }

    private fun isHeader(viewType: Int): Boolean {
        return viewType >= HEADER_VIEW_TYPE && viewType < HEADER_VIEW_TYPE + mHeaders.size
    }

    private fun isFooter(viewType: Int): Boolean {
        return viewType >= FOOTER_VIEW_TYPE && viewType < FOOTER_VIEW_TYPE + mFooters.size
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (isHeader(viewType)) {
            val whichHeader = Math.abs(viewType - HEADER_VIEW_TYPE)
            val headerView = mHeaders[whichHeader]
            return object : RecyclerView.ViewHolder(headerView) {

            }
        } else if (isFooter(viewType)) {
            val whichFooter = Math.abs(viewType - FOOTER_VIEW_TYPE)
            val footerView = mFooters[whichFooter]
            return object : RecyclerView.ViewHolder(footerView) {

            }

        } else {
            return wrappedAdapter.onCreateViewHolder(viewGroup, viewType)
        }
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        if (position < mHeaders.size) {
            // Headers don't need anything special

        } else if (position < mHeaders.size + wrappedAdapter.itemCount) {
            // This is a real position, not a header or footer. Bind it.
            wrappedAdapter.onBindViewHolder(viewHolder as Nothing?, position - mHeaders.size)
        } else {
            // Footers don't need anything special
        }
    }

    override fun getItemCount(): Int {
        return mHeaders.size + wrappedAdapter.itemCount + mFooters.size
    }

    override fun getItemViewType(position: Int): Int {
        if (position < mHeaders.size) {
            return HEADER_VIEW_TYPE + position

        } else if (position < mHeaders.size + wrappedAdapter.itemCount) {
            return wrappedAdapter.getItemViewType(position - mHeaders.size)

        } else {
            return FOOTER_VIEW_TYPE + position - mHeaders.size - wrappedAdapter.itemCount
        }
    }

    companion object {

        /**
         * Defines available view type integers for headers and footers.
         *
         *
         * How this works:
         * - Regular views use view types starting from 0, counting upwards
         * - Header views use view types starting from -1000, counting upwards
         * - Footer views use view types starting from -2000, counting upwards
         *
         *
         * This means that you're safe as long as the base adapter doesn't use negative view types,
         * and as long as you have fewer than 1000 headers and footers
         */
        private val HEADER_VIEW_TYPE = -1000
        private val FOOTER_VIEW_TYPE = -2000
    }
}
