package ru.spbau.bachelor2015.veselov.hw01.utilities

import org.apache.commons.io.FileUtils.readFileToByteArray
import ru.spbau.bachelor2015.veselov.hw01.ExecutionResult
import ru.spbau.bachelor2015.veselov.hw01.environment.Utility
import java.nio.charset.Charset
import java.nio.file.Path
import java.nio.file.Paths

/**
 * Wc utility counts number of newlines, words and bytes in given files. If command receives no
 * arguments then input stream is used.
 */
object WcUtility : Utility {
    override fun execute(args: List<String>, input: String): ExecutionResult {
        val output = if (args.isEmpty()) {
            countStatistics(input.toByteArray(Charset.defaultCharset())).toString()
        } else {
            args.joinToString("\n") { countFileStatistics(Paths.get(it)).toString() }
        }

        return ExecutionResult(output, false)
    }

    private fun countFileStatistics(path: Path): Statistics {
        return countStatistics(readFileToByteArray(path.toFile()))
    }

    private fun countStatistics(bytes: ByteArray): Statistics {
        val string = bytes.toString(Charset.defaultCharset())

        return Statistics(
            string.count { it == '\n' },
            string.split("\\s+".toRegex()).size,
            bytes.size
        )
    }

    private class Statistics(val newLines: Int, val words: Int, val bytes: Int) {
        override fun toString(): String {
            return "$newLines $words $bytes"
        }
    }
}
