@file:JvmName("Snake")

package kt.school.kodenjoy.snake

import com.codenjoy.dojo.client.WebSocketRunner
import com.codenjoy.dojo.snake.client.Board

// Replace this with your own!
private const val URL = "http://kod.enjoy:8080/codenjoy-contest/board/player/xxx99xxxxxx9xxxx9xx9?xxxx=9999999999999999999"

fun main() {
    WebSocketRunner
            .runClient(
                    URL,
                    Solver(),
                    Board()
            )
}
