package ru.spbau.bachelor2015.veselov.hw01.utilities

import ru.spbau.bachelor2015.veselov.hw01.ExecutionResult
import ru.spbau.bachelor2015.veselov.hw01.environment.Utility

import com.xenomachina.argparser.ArgParser
import com.xenomachina.argparser.default
import java.nio.file.Files
import java.nio.file.Paths
import java.util.stream.Stream

object GrepUtility : Utility {
    override fun execute(args: List<String>, input: String): ExecutionResult {
        val parsedArgs = ArgParser(args.toTypedArray()).parseInto(::Arguments)

        return ExecutionResult(
            parsedArgs.run {
                val regex = Regex(pattern)

                buildString {
                    if (files.size == 1) {
                        Files.lines(Paths.get(files.single())).use {
                            append(processStream(regex, it, ""))
                        }
                    } else {
                        for (file in files) {
                            Files.lines(Paths.get(file)).use {
                                append(processStream(regex, it, file + ":"))
                            }
                        }
                    }
                }
            },
            false
        )
    }

    private fun processStream(regex: Regex, stream: Stream<String>, prefix: String): String {
        return buildString {
            stream.forEach {
                if (regex.containsMatchIn(it)) {
                    append(prefix).appendln(it)
                }
            }
        }
    }

    private class Arguments(parser: ArgParser) {
        val ignoreCase by parser.flagging("-i", help = "")

        val wordsMatching by parser.flagging("-w", help = "")

        val linesAfterMatch by parser.storing("-A", help = "").default(0)

        val pattern by parser.positional("PATTERN", help = "")

        val files by parser.positionalList("FILES", help = "", sizeRange = 1..Int.MAX_VALUE)
    }
}