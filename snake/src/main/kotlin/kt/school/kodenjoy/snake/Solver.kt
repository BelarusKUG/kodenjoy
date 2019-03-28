package kt.school.kodenjoy.snake

import com.codenjoy.dojo.client.Solver
import com.codenjoy.dojo.services.Point
import com.codenjoy.dojo.snake.client.Board

// Use all the power of Kotlin!
operator fun Point.component1(): Int = this.x
operator fun Point.component2(): Int = this.y

class Solver : Solver<Board> {
    override fun get(board: Board): String {
        // Just go up
        // return Direction.UP.toString()

        // Clockwise cycle, no obstacle detection
        // return when (board.snakeDirection) {
        //     Direction.UP -> if (board.walls.any { (x, y) -> (x == board.head.x) && (y == board.head.y + 1) }) Direction.LEFT else Direction.UP
        //     Direction.LEFT -> if (board.walls.any { (x, y) -> (x == board.head.x - 1) && (y == board.head.y) }) Direction.DOWN else Direction.LEFT
        //     Direction.DOWN -> if (board.walls.any { (x, y) -> (x == board.head.x) && (y == board.head.y - 1) }) Direction.RIGHT else Direction.DOWN
        //     Direction.RIGHT -> if (board.walls.any { (x, y) -> (x == board.head.x + 1) && (y == board.head.y) }) Direction.UP else Direction.RIGHT
        //     else -> Direction.UP
        // }.toString()

        TODO("not implemented")
    }
}
