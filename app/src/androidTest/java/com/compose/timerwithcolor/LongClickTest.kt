package com.compose.timerwithcolor

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LongClickTest {

    private lateinit var device: UiDevice

    @Before
    fun setup() {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        device.pressHome()
        val launcherPackage: String = device.launcherPackageName
        assertThat(launcherPackage, notNullValue())
        device.wait(
            Until.hasObject(By.pkg(launcherPackage).depth(0)),
            LAUNCH_TIMEOUT
        )
        val context = ApplicationProvider.getApplicationContext<Context>()
        val intent = context.packageManager.getLaunchIntentForPackage(
            BASIC_SAMPLE_PACKAGE
        ).apply { this!!.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK) }
        context.startActivity(intent)
        // Wait for the app to appear
        device.wait(
            Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)),
            LAUNCH_TIMEOUT
        )
    }

    @Test
    fun longClickTest1() {
        val x = device.displayWidth / 2
        val y = device.displayHeight / 2

        val duration = 100
        device.swipe(x, y, x, y, duration)

        val text = device.findObject(By.text("With Darkness"))
        assert(!text.isClickable)
    }

    @Test
    fun longClickTest2() {
        val x = device.displayWidth / 2
        val y = device.displayHeight / 2

        val duration = 100
        device.swipe(x, y, x, y, duration)
        Thread.sleep(50)
        device.swipe(x, y, x, y, duration)

        val text = device.findObject(By.text("No Darkness"))
        assert(!text.isClickable)
    }
}