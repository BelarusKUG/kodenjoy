package com.codenjoy.dojo.snake.model.artifacts;

/*-
 * #%L
 * Codenjoy - it's a dojo-like platform from developers to developers.
 * %%
 * Copyright (C) 2018 Codenjoy
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */


import com.codenjoy.dojo.services.Dice;
import com.codenjoy.dojo.services.Direction;
import com.codenjoy.dojo.services.Point;
import com.codenjoy.dojo.services.PointImpl;
import com.codenjoy.dojo.snake.model.Hero;
import com.codenjoy.dojo.snake.model.Walls;

import static com.codenjoy.dojo.services.PointImpl.pt;

public class RandomArtifactGenerator implements ArtifactGenerator {

    public static final int MAX = 100;
    private Dice dice;

    public RandomArtifactGenerator(Dice dice) {
        this.dice = dice;
    }

    @Override
    public Stone generateStone(Hero snake, Apple apple, Walls walls, int boardSize) {
        int x;
        int y;
        boolean noSoGoodPlace;

        if (checkForMaxLength(snake, walls, boardSize)){
            return new Stone(-1, -1);
        }

        int c = 0;
        do {
            x = dice.next(boardSize);
            y = dice.next(boardSize);

            boolean onSnake = snake.itsMe(x, y);
            boolean onApple = (apple != null) && apple.itsMe(x, y);
            boolean onWall = walls.itsMe(x, y);

            boolean onSnakeWayWhenGoRight = (snake.getX() + 1) <= x && x <= boardSize && y == snake.getY() && snake.getDirection().equals(Direction.RIGHT);
            boolean onSnakeWayWhenGoLeft = 0 <= x && x <= (snake.getX() - 1) && y == snake.getY() && snake.getDirection().equals(Direction.LEFT);
            boolean onSnakeWayWhenGoDown = (snake.getY() + 1) <= y && y <= boardSize && x == snake.getX() && snake.getDirection().equals(Direction.DOWN);
            boolean onSnakeWayWhenGoUp = 0 <= y && y <= (snake.getY() - 1) && x == snake.getX() && snake.getDirection().equals(Direction.UP);
            boolean onSnakeWay = onSnakeWayWhenGoRight || onSnakeWayWhenGoLeft || onSnakeWayWhenGoDown || onSnakeWayWhenGoUp;

            boolean whenStandstill = isStandstill(apple, pt(x, y), boardSize);

            boolean outOfBoard = pt(x, y).isOutOf(boardSize);

            noSoGoodPlace = outOfBoard || onSnake || onSnakeWay || onApple || whenStandstill || onWall;
        } while (noSoGoodPlace && c++ < 100);

        if (c >= MAX) {
            return new Stone(-1, -1);
        }

        return new Stone(x, y);
    }

    private boolean isStandstill(Point apple, Point stone, int boardSize) {
        if (apple == null) {
            return false;
        }

        int D = 1; // TODO это ширина BasicWalls стен, надо тут учесть реальную конфигурацию стен
        int LEFT = 0 + D;
        int TOP = 0 + D;
        int RIGHT = boardSize - 1 - D;
        int BOTTOM = boardSize - 1 - D;

        boolean whenLeftTopStandstill = apple.itsMe(LEFT, TOP) && (stone.itsMe(LEFT, TOP + 1) || stone.itsMe(LEFT + 1, TOP));
        boolean whenLeftBottomStandstill = apple.itsMe(LEFT, BOTTOM) && (stone.itsMe(LEFT, BOTTOM - 1) || stone.itsMe(LEFT + 1, BOTTOM));
        boolean whenRightTopStandstill = apple.itsMe(RIGHT, TOP) && (stone.itsMe(RIGHT, TOP + 1) || stone.itsMe(RIGHT - 1, TOP));
        boolean whenRightBottomStandstill = apple.itsMe(RIGHT, BOTTOM) && (stone.itsMe(RIGHT, BOTTOM - 1) || stone.itsMe(RIGHT - 1, BOTTOM));

        return whenLeftTopStandstill || whenLeftBottomStandstill || whenRightTopStandstill || whenRightBottomStandstill;
    }

    private boolean checkForMaxLength(Hero snake, Walls walls, int boardSize) {
        int maxSnakeSize = boardSize * boardSize - walls.getCountOfWalls() - 1;
        return snake.getLength() == maxSnakeSize;
    }

    @Override
    public Apple generateApple(Hero snake, Apple apple, Stone stone, Walls walls, int boardSize) {
        int x;
        int y;
        boolean noSoGoodPlace;

        if (checkForMaxLength(snake, walls, boardSize)){
            return new Apple(-1, -1);
        }

        int c = 0;
        do {
            x = dice.next(boardSize);
            y = dice.next(boardSize);

            boolean onSnake = snake.itsMe(x, y);
            boolean onStone = stone.itsMe(x, y);
            boolean onWall = walls.itsMe(x, y);
            boolean onApple = apple != null && apple.itsMe(x, y);

            boolean whenStandstill = isStandstill(pt(x, y), stone, boardSize);

            boolean outOfBoard = pt(x, y).isOutOf(boardSize);

            noSoGoodPlace = outOfBoard || onSnake || onStone || whenStandstill || onWall || onApple;
        } while (noSoGoodPlace && c++ < 100);

        if (c >= MAX) {
            return new Apple(-1, -1);
        }

        return new Apple(x, y);
    }

}
