package kt.school.kodenjoy.battlecity

import com.codenjoy.dojo.battlecity.client.Board
import com.codenjoy.dojo.client.Solver

class Solver : Solver<Board> {
    override fun get(board: Board): String {
        // Just fire
        // return Direction.ACT.toString()

        // Lawful evil
        // return when (Random.nextInt(100)) {
        //     in 0..80 -> Direction.ACT
        //     in 81..85 -> Direction.UP
        //     in 86..90 -> Direction.DOWN
        //     in 91..95 -> Direction.LEFT
        //     in 96..100 -> Direction.RIGHT
        //     else -> Direction.STOP
        // }.toString()

        TODO("not implemented")
    }
}
