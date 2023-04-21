package com.riopermana.core.data.network.fake

import java.io.InputStreamReader

fun interface FakeAssetManager {
    fun open(fileName: String) : InputStreamReader
}