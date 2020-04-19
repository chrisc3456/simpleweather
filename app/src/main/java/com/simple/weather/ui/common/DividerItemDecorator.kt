package com.simple.weather.ui.common

import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

/**
 * Custom divider class to handle not being shown at the end of the last entry in a list
 */
class DividerItemDecorator(private val mDivider: Drawable, private val viewOrientation: ViewOrientation) : ItemDecoration() {

    enum class ViewOrientation {
        HORIZONTAL,
        VERTICAL
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        if (parent.getChildAdapterPosition(view) == 0) {
            return
        }

        when (viewOrientation) {
            ViewOrientation.HORIZONTAL -> outRect.left = mDivider.intrinsicWidth
            ViewOrientation.VERTICAL -> outRect.top = mDivider.intrinsicHeight
        }
    }

    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {

        var dividerTop = 0
        var dividerBottom = 0
        var dividerLeft = 0
        var dividerRight = 0

        when (viewOrientation) {
            ViewOrientation.HORIZONTAL -> {
                dividerTop = parent.paddingTop
                dividerBottom = parent.height - parent.paddingBottom
            }
            ViewOrientation.VERTICAL -> {
                dividerLeft = parent.paddingLeft
                dividerRight = parent.width - parent.paddingRight
            }
        }


        val childCount = parent.childCount
        for (i in 0..childCount - 2) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams

            when (viewOrientation) {
                ViewOrientation.HORIZONTAL -> {
                    dividerLeft = child.right + params.rightMargin
                    dividerRight = dividerLeft + mDivider.intrinsicWidth
                }
                ViewOrientation.VERTICAL -> {
                    dividerTop = child.bottom + params.bottomMargin
                    dividerBottom = dividerTop + mDivider.intrinsicHeight
                }
            }

            mDivider.setBounds(dividerLeft, dividerTop, dividerRight, dividerBottom)
            mDivider.draw(canvas)
        }
    }
}