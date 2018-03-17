[![Build Status](https://travis-ci.org/Ivan-Veselov/se-design-hw.svg?branch=02-grep)](https://travis-ci.org/Ivan-Veselov/se-design-hw)

# Описание

## Библиотеки для разбора аргументов

[Список библиотек для Java](http://jewelcli.lexicalscope.com/related.html) &mdash; очень много библиотек. Но все они не используют фишки Kotlin, так как написаны для Java.

[kotlin-cli](https://github.com/leprosus/kotlin-cli) &mdash; очень старая. Написана в Java стиле, то есть ничем не отличается от библиотек из большого списка, который приведен выше.

[Kopper](https://github.com/jimschubert/kopper) &mdash; версия 0.0.3 годовалой давности. Нет подробной документации. Все тот же Java стиль.

[Kotlin --argparser](https://github.com/xenomachina/kotlin-argparser) &mdash; использует фишки Kotlin; исчерпывающая документация в README, из которой следует, что весь требуемый функционал можно реализовать с использованием этой библиотеки; актуальный код.