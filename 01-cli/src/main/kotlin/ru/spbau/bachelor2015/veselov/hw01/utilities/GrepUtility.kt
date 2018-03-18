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
                val regex = if (ignoreCase) {
                    Regex(pattern, RegexOption.IGNORE_CASE)
                } else {
                    Regex(pattern)
                }

                buildString {
                    when (files.size) {
                        0 -> append(processStream(
                            regex,
                            input.split("\\R".toRegex()).stream(),
                            "",
                            linesAfterMatch
                        ))

                        1 -> Files.lines(Paths.get(files.single())).use {
                            append(processStream(regex, it, "", linesAfterMatch))
                        }

                        else -> for (file in files) {
                            Files.lines(Paths.get(file)).use {
                                append(processStream(regex, it, file + ":", linesAfterMatch))
                            }
                        }
                    }
                }
            },
            false
        )
    }

    private fun processStream(
        regex: Regex,
        stream: Stream<String>,
        prefix: String, linesAfterMatch: Int
    ): String {
        return buildString {
            var counter = 0
            stream.forEach {
                if (regex.containsMatchIn(it)) {
                    append(prefix).appendln(it)
                    counter = linesAfterMatch
                } else if (counter > 0) {
                    append(prefix).appendln(it)
                    counter--
                }
            }
        }
    }

    private class Arguments(parser: ArgParser) {
        val ignoreCase by parser.flagging("-i", help = "")

        val wordsMatching by parser.flagging("-w", help = "")

        val linesAfterMatch by parser.storing("-A", help = "") {
            Integer.parseInt(this)
        }.default(0)

        val pattern by parser.positional("PATTERN", help = "")

        val files by parser.positionalList("FILES", help = "", sizeRange = 0..Int.MAX_VALUE)
    }
}