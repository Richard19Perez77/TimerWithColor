package com.compose.timerwithcolor

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

/**
 * BaseActivity provides a base implementation for activities requiring full-screen mode.
 *
 * This class ensures the application utilizes as much screen space as possible by hiding
 * system UI elements such as the status bar and navigation bar. It adapts its behavior
 * based on the device's SDK version to support a consistent experience across versions.
 */
open class BaseActivity : ComponentActivity() {

    /**
     * Enables full-screen mode by hiding system UI elements like the status bar and navigation bar.
     *
     * This method uses modern APIs for Android 11 (API 30) and above, while retaining backward
     * compatibility with deprecated APIs for older versions.
     */
    private fun enableFullscreenMode() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            WindowCompat.setDecorFitsSystemWindows(window, false)
            WindowInsetsControllerCompat(window, window.decorView).apply {
                hide(WindowInsetsCompat.Type.statusBars() or WindowInsetsCompat.Type.navigationBars())
                systemBarsBehavior =
                    WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        } else {
            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility =
                (View.SYSTEM_UI_FLAG_IMMERSIVE
                        or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_FULLSCREEN)
        }
    }

    /**
     * Lifecycle method called when the activity is created.
     *
     * This method initializes the activity and enables full-screen mode.
     *
     * @param savedInstanceState A bundle containing the most recent data saved, or null if this
     * is a fresh launch.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableFullscreenMode()
    }
}