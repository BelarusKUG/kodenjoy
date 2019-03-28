package kt.school.kodenjoy.bomberman

import com.codenjoy.dojo.bomberman.client.Board
import com.codenjoy.dojo.client.Solver

class Solver : Solver<Board> {
    override fun get(board: Board): String {
        // BANZAI!!!!!
        // return Direction.ACT.toString()

        // Chaotic evil
        // if (board.bombs.any { it == board.bomberman || it == board.bomberman.apply { y - 1 } }) {
        //     return Direction.UP.toString()
        // } else if (Random.nextInt() > 0 /* 50% */) {
        //     return Direction.ACT.toString()
        // } else {
        //     return Direction.random().toString()
        // }

        TODO("not implemented")
    }
}
