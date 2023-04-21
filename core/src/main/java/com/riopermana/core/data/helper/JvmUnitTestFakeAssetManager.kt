package com.riopermana.core.data.helper

import androidx.annotation.VisibleForTesting
import com.riopermana.core.data.network.fake.FakeAssetManager
import java.io.File
import java.io.InputStreamReader


@VisibleForTesting
internal object JvmUnitTestFakeAssetManager : FakeAssetManager {

    override fun open(fileName: String): InputStreamReader =
        File("src\\main\\assets\\$fileName").reader()
}