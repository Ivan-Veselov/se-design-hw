package ru.spbau.bachelor2015.veselov.hw01.utilities

import org.apache.commons.io.FileUtils
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
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
        testFile("bc", listOf(), "abcd", "abcd\n")
    }

    @Test
    fun twoFiles() {
        val files = prepareFiles("abcd", "bcbc")
        val expected = "${files[0].absolutePath}:abcd\n${files[1].absolutePath}:bcbc\n"

        MatcherAssert.assertThat(
            GrepUtility.execute(
                listOf("bc") + files.map{ it.absolutePath },
                ""
            ),
            CoreMatchers.`is`(CoreMatchers.equalTo(ExecutionResult(expected, false)))
        )
    }

    @Test
    fun severalLinesInOneFile() {
        testFile("bc", listOf(), "abcd\nbcde\ncdef", "abcd\nbcde\n")
    }

    @Test
    fun stdInput() {
        testStdInput("bc", listOf(), "a bcd\nabc d\n", "a bcd\nabc d\n")
    }

    @Test
    fun afterOption() {
        testStdInput("bc", listOf("-A", "1"), "abcd\nzzz\nxxx", "abcd\nzzz\n")
    }

    @Test
    fun afterOptionInOneWord() {
        testStdInput("bc", listOf("-A1"), "abcd\nzzz\nxxx", "abcd\nzzz\n")
    }

    @Test
    fun ignoreCaseOption() {
        testStdInput("bc", listOf("-i"), "aBcD\n", "aBcD\n")
    }

    @Test
    fun wordsMatchingOption() {
        testStdInput("cc", listOf("-w"), "bccb\ncb cc\n", "cb cc\n")
    }

    private fun testFile(
        pattern: String,
        options: List<String>,
        content: String,
        expected: String
    ) {
        val file = prepareFiles(content).single()

        MatcherAssert.assertThat(
            GrepUtility.execute(
                listOf(pattern) + options + file.absolutePath,
                ""
            ),
            CoreMatchers.`is`(CoreMatchers.equalTo(ExecutionResult(expected, false)))
        )
    }

    private fun testStdInput(
        pattern: String,
        options: List<String>,
        input: String,
        expected: String
    ) {
        MatcherAssert.assertThat(
            GrepUtility.execute(
                listOf(pattern) + options,
                input
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
