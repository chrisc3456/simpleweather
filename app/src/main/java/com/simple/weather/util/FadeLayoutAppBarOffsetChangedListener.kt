package com.simple.weather.util

import android.view.View
import com.google.android.material.appbar.AppBarLayout
import kotlin.math.abs

class FadeLayoutAppBarOffsetChangedListener(private val fadeMode: FadeMode, private val fadeView: View, private val fadeAdjustmentRate: Float): AppBarLayout.OnOffsetChangedListener {

    enum class FadeMode {
        FADE_IN,
        FADE_OUT
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {

        val adjustment = verticalOffset / appBarLayout!!.totalScrollRange.toFloat()

        if (fadeMode == FadeMode.FADE_IN) {
            fadeView.alpha = 0f + abs(adjustment * fadeAdjustmentRate)
        }

        if (fadeMode == FadeMode.FADE_OUT) {
            fadeView.alpha = 1f - abs(adjustment * fadeAdjustmentRate)
        }
    }
}