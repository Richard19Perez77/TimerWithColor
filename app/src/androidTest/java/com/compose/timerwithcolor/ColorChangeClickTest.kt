package com.compose.timerwithcolor

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
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
import java.io.File
import java.util.UUID
import kotlin.math.abs

const val BASIC_SAMPLE_PACKAGE = "com.compose.timerwithcolor"
const val LAUNCH_TIMEOUT = 5000L
const val STRING_TO_BE_TYPED = "UiAutomator"

@RunWith(AndroidJUnit4::class)
class ColorChangeClickTest {

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
    fun colorClickTest0() {
        var id = UUID.randomUUID().toString()
        device.takeScreenshot(File("/sdcard/Pictures/test$id.png"), 1F, 100)
        var bitmap = BitmapFactory.decodeFile("/sdcard/Pictures/test$id.png")
        var pixelColor = bitmap.getPixel(100, 100)

        var red = Color.red(pixelColor)
        var green = Color.green(pixelColor)
        var blue = Color.blue(pixelColor)

        Thread.sleep(1000)

        var id2 = UUID.randomUUID().toString()
        device.takeScreenshot(File("/sdcard/Pictures/test$id2.png"), 1F, 100)
        bitmap = BitmapFactory.decodeFile("/sdcard/Pictures/test$id2.png")
        pixelColor = bitmap.getPixel(100, 100)

        var red2 = Color.red(pixelColor)
        var green2 = Color.green(pixelColor)
        var blue2 = Color.blue(pixelColor)

        assert(abs(red - red2) == 0 && abs(green - green2) == 0 && abs(blue - blue2) == 0)
    }

    @Test
    fun colorClickTest1() {
        var id = UUID.randomUUID().toString()
        device.takeScreenshot(File("/sdcard/Pictures/test$id.png"), 1F, 100)
        var bitmap = BitmapFactory.decodeFile("/sdcard/Pictures/test$id.png")
        var pixelColor = bitmap.getPixel(100, 100)

        var red = Color.red(pixelColor)
        var green = Color.green(pixelColor)
        var blue = Color.blue(pixelColor)

        val x = device.displayWidth / 2
        val y = device.displayHeight / 2

        device.click(x, y)
        Thread.sleep(1000)

        var id2 = UUID.randomUUID().toString()
        device.takeScreenshot(File("/sdcard/Pictures/test$id2.png"), 1F, 100)
        bitmap = BitmapFactory.decodeFile("/sdcard/Pictures/test$id2.png")
        pixelColor = bitmap.getPixel(100, 100)

        var red2 = Color.red(pixelColor)
        var green2 = Color.green(pixelColor)
        var blue2 = Color.blue(pixelColor)

        assert(abs(red - red2) > 0 || abs(green - green2) > 0 || abs(blue - blue2) > 0)
        Thread.sleep(1000)

        var id3 = UUID.randomUUID().toString()
        device.takeScreenshot(File("/sdcard/Pictures/test$id3.png"), 1F, 100)
        bitmap = BitmapFactory.decodeFile("/sdcard/Pictures/test$id3.png")
        pixelColor = bitmap.getPixel(100, 100)

        var red3 = Color.red(pixelColor)
        var green3 = Color.green(pixelColor)
        var blue3 = Color.blue(pixelColor)

        assert(abs(red2 - red3) > 0 || abs(green2 - green3) > 0 || abs(blue2 - blue3) > 0)
    }
}