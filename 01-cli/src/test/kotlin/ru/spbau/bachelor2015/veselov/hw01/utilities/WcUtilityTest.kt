package ru.spbau.bachelor2015.veselov.hw01.utilities

import org.apache.commons.io.FileUtils
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import ru.spbau.bachelor2015.veselov.hw01.ExecutionResult
import java.nio.charset.Charset

class WcUtilityTest {
    @Rule
    @JvmField
    val folder = TemporaryFolder()

    @Test
    fun oneFile() {
        val file = folder.newFile()

        FileUtils.writeStringToFile(file, "abc \n abd", Charset.defaultCharset())

        val executionResult =
                WcUtility.execute(listOf(file.absolutePath), "")

        MatcherAssert.assertThat(
            executionResult,
            CoreMatchers.`is`(CoreMatchers.equalTo(ExecutionResult("1 2 9", false)))
        )
    }

    @Test
    fun inputStream() {
        val executionResult =
                WcUtility.execute(listOf(), "a  b")

        MatcherAssert.assertThat(
            executionResult,
            CoreMatchers.`is`(CoreMatchers.equalTo(ExecutionResult("0 2 4", false)))
        )
    }
}
