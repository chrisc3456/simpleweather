package com.simple.weather.util

import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout

/**
 * Helper class for a collapsing toolbar layout where the title should only be shown when the layout is fully collapsed
 */
class TitleOnCollapseAppbarOffsetListener(private val toolbarLayout: CollapsingToolbarLayout, private val titleDisplayProvider: ToolbarTitleDisplayProvider): AppBarLayout.OnOffsetChangedListener {

    private val titleShown = true
    private var scrollRange = -1

    override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {

        if (scrollRange == -1) {
            scrollRange = appBarLayout!!.totalScrollRange
        }

        if (scrollRange + verticalOffset == 0) {
            toolbarLayout.title = titleDisplayProvider.getTitle()
            titleShown
        } else if (titleShown) {
            toolbarLayout.title = ""
            !titleShown
        }
    }
}