package com.simple.weather.ui.common

import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

/**
 * Custom divider class to handle not being shown at the end of the last entry in a list
 */
class DividerItemDecorator(private val mDivider: Drawable) : ItemDecoration() {
    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val dividerTop = parent.paddingTop
        val dividerBottom = parent.height - parent.paddingBottom
        val childCount = parent.childCount
        for (i in 0..childCount - 2) {
            val child = parent.getChildAt(i)
            val params =
                child.layoutParams as RecyclerView.LayoutParams
            val dividerLeft = child.right + params.rightMargin
            val dividerRight = dividerLeft + mDivider.intrinsicWidth
            mDivider.setBounds(dividerLeft, dividerTop, dividerRight, dividerBottom)
            mDivider.draw(canvas)
        }
    }

}