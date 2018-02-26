package ru.spbau.bachelor2015.veselov.hw01.utilities

import org.apache.commons.io.FileUtils.readFileToString
import ru.spbau.bachelor2015.veselov.hw01.ExecutionResult
import ru.spbau.bachelor2015.veselov.hw01.environment.Utility
import java.io.IOException
import java.nio.charset.Charset
import java.nio.file.Path
import java.nio.file.Paths

/**
 * Cat utility concatenates and prints content of files which are passed as arguments.
 */
object CatUtility : Utility {
    override fun execute(args: List<String>, input: String): ExecutionResult {
        return ExecutionResult(
            args.joinToString("") { processFile(Paths.get(it)) },
            false
        )
    }

    private fun processFile(path: Path): String {
        return try {
            readFileToString(path.toFile(), Charset.defaultCharset())
        } catch (_: IOException) {
            "unable to open file $path"
        }
    }
}
