package ru.spbau.bachelor2015.veselov.hw11

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import java.lang.Thread.sleep

class MessengerTest {
    private val localhost = "localhost"

    @Test
    fun testSendMessage() {
        val userName1 = "user1"
        val port1 = 2001

        val userName2 = "user2"
        val port2 = 2002

        val messageFrom1 = "message1"
        val messageFrom2 = "message2"

        var delivered1 = false
        var delivered2 = false

        val messenger1 = Messenger(userName1, port1, localhost, port2) {
            assertThat(it.body, `is`(equalTo(messageFrom2)))
            assertThat(it.userName, `is`(equalTo(userName2)))

            synchronized(delivered1) {
                delivered1 = true
            }
        }

        val messenger2 = Messenger(userName2, port2, localhost, port1) {
            assertThat(it.body, `is`(equalTo(messageFrom1)))
            assertThat(it.userName, `is`(equalTo(userName1)))

            synchronized(delivered2) {
                delivered2 = true
            }
        }

        assertThat(messenger1.sendMessage(messageFrom1), `is`(true))
        assertThat(messenger2.sendMessage(messageFrom2), `is`(true))

        sleep(100)

        synchronized(delivered1) {
            assertThat(delivered1, `is`(true))
        }

        synchronized(delivered2) {
            assertThat(delivered2, `is`(true))
        }

        messenger1.shutdown()
        messenger2.shutdown()
    }

    @Test
    fun testNoRecipient() {
        val userName1 = "user1"
        val port1 = 2001
        val port2 = 2002

        val messageFrom1 = "message1"

        val messenger1 = Messenger(userName1, port1, localhost, port2) {}

        assertThat(messenger1.sendMessage(messageFrom1), `is`(false))

        messenger1.shutdown()
    }
}
