package com.simple.weather.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.simple.weather.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setWindowDecor()
    }

    private fun setWindowDecor() {
        // Set system visibility to overlap the status bar at the top of the screen
        // colorPrimaryDark set to transparent with child fragments handling insets to adjust which content moves through the status bar on scroll
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN + View.SYSTEM_UI_FLAG_LAYOUT_STABLE + View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
    }

    fun toggleDrawerState() {
        if (drawerLayoutMain.isDrawerOpen(GravityCompat.START)) {
            drawerLayoutMain.closeDrawer(GravityCompat.START)
        } else {
            drawerLayoutMain.openDrawer(GravityCompat.START)
        }
    }
}
