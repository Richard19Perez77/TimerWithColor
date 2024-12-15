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
class DoubleClickTest {

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
    fun doubleClickTest1() {
        val x = device.displayWidth / 2
        val y = device.displayHeight / 2
        device.click(x, y)
        Thread.sleep(50)
        device.click(x,y)
        Thread.sleep(50)
        val text = device.findObject(By.text("HSV Cool"))
        assert(!text.isClickable)
    }

    @Test
    fun doubleClickTest2() {
        val x = device.displayWidth / 2
        val y = device.displayHeight / 2
        device.click(x, y)
        Thread.sleep(50)
        device.click(x,y)

        Thread.sleep(300)

        device.click(x,y)
        Thread.sleep(50)
        device.click(x,y)
        Thread.sleep(50)
        val text = device.findObject(By.text("HSV Warm"))
        assert(!text.isClickable)
    }

    @Test
    fun doubleClickTest3() {
        val x = device.displayWidth / 2
        val y = device.displayHeight / 2
        device.click(x, y)
        Thread.sleep(50)
        device.click(x,y)

        Thread.sleep(300)

        device.click(x,y)
        Thread.sleep(50)
        device.click(x,y)

        Thread.sleep(300)

        device.click(x,y)
        Thread.sleep(50)
        device.click(x,y)
        Thread.sleep(50)

        val text = device.findObject(By.text("RGB Cool"))
        assert(!text.isClickable)
    }

    @Test
    fun doubleClickTest4() {
        val x = device.displayWidth / 2
        val y = device.displayHeight / 2
        device.click(x, y)
        Thread.sleep(50)
        device.click(x,y)

        Thread.sleep(300)

        device.click(x,y)
        Thread.sleep(50)
        device.click(x,y)

        Thread.sleep(300)

        device.click(x,y)
        Thread.sleep(50)
        device.click(x,y)

        Thread.sleep(300)

        device.click(x,y)
        Thread.sleep(50)
        device.click(x,y)
        Thread.sleep(50)

        val text = device.findObject(By.text("RGB Warm"))
        assert(!text.isClickable)
    }


    @Test
    fun doubleClickTest5() {
        val x = device.displayWidth / 2
        val y = device.displayHeight / 2
        device.click(x, y)
        Thread.sleep(50)
        device.click(x,y)

        Thread.sleep(300)

        device.click(x,y)
        Thread.sleep(50)
        device.click(x,y)

        Thread.sleep(300)

        device.click(x,y)
        Thread.sleep(50)
        device.click(x,y)

        Thread.sleep(300)

        device.click(x,y)
        Thread.sleep(50)
        device.click(x,y)

        Thread.sleep(300)

        device.click(x,y)
        Thread.sleep(50)
        device.click(x,y)
        Thread.sleep(50)

        val text = device.findObject(By.text("RGB"))
        assert(!text.isClickable)
    }

    @Test
    fun doubleClickTest6() {
        val x = device.displayWidth / 2
        val y = device.displayHeight / 2
        device.click(x, y)
        Thread.sleep(50)
        device.click(x,y)

        Thread.sleep(300)

        device.click(x,y)
        Thread.sleep(50)
        device.click(x,y)

        Thread.sleep(300)

        device.click(x,y)
        Thread.sleep(50)
        device.click(x,y)

        Thread.sleep(300)

        device.click(x,y)
        Thread.sleep(50)
        device.click(x,y)

        Thread.sleep(300)

        device.click(x,y)
        Thread.sleep(50)
        device.click(x,y)

        Thread.sleep(300)

        device.click(x,y)
        Thread.sleep(50)
        device.click(x,y)
        Thread.sleep(50)

        val text = device.findObject(By.text("HSV"))
        assert(!text.isClickable)
    }
}