package com.nouranmontaser.spacenews

import androidx.test.platform.app.InstrumentationRegistry
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import dagger.hilt.android.testing.HiltTestApplication
import okio.buffer
import okio.source
import java.io.IOException
import java.io.InputStreamReader
import java.lang.reflect.Type

object Utils {

    fun readStringFromFile(fileName: String): String {
        try {
            val inputStream = (InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as HiltTestApplication).assets.open(fileName)
            val builder = StringBuilder()
            val reader = InputStreamReader(inputStream, Charsets.UTF_8)
            reader.readLines().forEach {
                builder.append(it)
            }
            return builder.toString()
        } catch (e: IOException) {
            throw e
        }
    }

    inline fun <reified T> readFileResponseToListOfObject(fileName: String, moshi: Moshi): List<T>? {
        val inputStream = (InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as HiltTestApplication).assets.open(fileName)
        val temp = inputStream.source().buffer().readString(Charsets.UTF_8)
        val type: Type = Types.newParameterizedType(
            MutableList::class.java,
            T::class.java
        )

        val jsonAdapter: JsonAdapter<out List<T>> = moshi.adapter(type)
        return jsonAdapter.fromJson(temp)
    }
}