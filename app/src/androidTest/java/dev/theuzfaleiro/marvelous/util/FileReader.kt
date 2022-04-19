package dev.theuzfaleiro.marvelous.util

import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import dagger.hilt.android.testing.HiltTestApplication
import java.io.IOException
import java.io.InputStreamReader

private const val RESPONSE = "response"

fun String.asJsonString(): String {
    try {
        val inputStream =
            (getInstrumentation().targetContext.applicationContext as HiltTestApplication)
                .assets.open("${RESPONSE}/$this")

        val builder = StringBuilder()
        val reader = InputStreamReader(inputStream, "UTF-8")

        reader.readLines().forEach {
            builder.append(it)
        }

        return builder.toString()
    } catch (e: IOException) {
        throw e
    }
}