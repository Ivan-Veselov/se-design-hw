package ru.spbau.bachelor2015.veselov.hw01.utilities

import org.apache.commons.io.FileUtils
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import ru.spbau.bachelor2015.veselov.hw01.ExecutionResult
import java.io.File
import java.nio.charset.Charset

class GrepUtilityTest {
    @Rule
    @JvmField
    val folder = TemporaryFolder()

    @Test
    fun oneFile() {
        testFiles("bc", listOf(), "abcd", expected = "abcd\n")
    }

    @Test
    fun twoFiles() {
        testFiles("bc", listOf(), "abcd", "bcbc", expected = "abcd\nbcbc\n")
    }

    @Test
    fun severalLinesInOneFile() {
        testFiles("bc", listOf(), "abcd\nbcde\ncdef", expected = "abcd\nbcde\n")
    }

    private fun testFiles(
        pattern: String,
        options: List<String>,
        vararg contents: String,
        expected: String
    ) {
        val files = prepareFiles(*contents)

        MatcherAssert.assertThat(
            GrepUtility.execute(
                listOf(pattern) + options + files.map{ it.absolutePath },
                ""
            ),
            CoreMatchers.`is`(CoreMatchers.equalTo(ExecutionResult(expected, false)))
        )
    }

    private fun prepareFiles(vararg contents: String): List<File> {
        return contents.map {
            val file = folder.newFile()
            FileUtils.writeStringToFile(file, it, Charset.defaultCharset())

            file
        }
    }
}
