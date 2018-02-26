package ru.spbau.bachelor2015.veselov.hw01.utilities

import org.apache.commons.io.FileUtils.writeStringToFile
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import ru.spbau.bachelor2015.veselov.hw01.ExecutionResult
import java.nio.charset.Charset

class CatUtilityTest {
    @Rule
    @JvmField
    val folder = TemporaryFolder()

    @Test
    fun testUsingTempFolder() {
        val file1 = folder.newFile()
        val file2 = folder.newFile()

        val content1 = "abc"
        val content2 = "efg"

        writeStringToFile(file1, content1, Charset.defaultCharset())
        writeStringToFile(file2, content2, Charset.defaultCharset())

        val executionResult =
            CatUtility.execute(listOf(file1.absolutePath, file2.absolutePath), "")

        assertThat(
            executionResult,
            `is`(equalTo(ExecutionResult(content1 + content2, false)))
        )
    }
}
