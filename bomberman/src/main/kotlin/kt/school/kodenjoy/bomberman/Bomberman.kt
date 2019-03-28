@file:JvmName("Bomberman")

package kt.school.kodenjoy.bomberman

import com.codenjoy.dojo.bomberman.client.Board
import com.codenjoy.dojo.client.WebSocketRunner

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
