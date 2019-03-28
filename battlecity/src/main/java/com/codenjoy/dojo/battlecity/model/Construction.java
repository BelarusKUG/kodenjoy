package com.codenjoy.dojo.battlecity.model;

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


import com.codenjoy.dojo.services.*;

public class Construction extends PointImpl implements Tickable, State<Elements, Player> {

    public static final int REGENERATE_TIME = 30;

    private Elements ch;
    private int timer;

    public Construction(int x, int y) {
        super(x, y);
        reset();
    }

    public Construction(Point xy) {
        this(xy.getX(), xy.getY());
    }

    public void destroyFrom(Direction bulletDirection) {
        if (ch.power == 1) {
            ch = Elements.CONSTRUCTION_DESTROYED;
            return;
        }
        if (bulletDirection.equals(Direction.UP)) {
            switch (ch) {
                case CONSTRUCTION : ch = Elements.CONSTRUCTION_DESTROYED_DOWN; break;
                case CONSTRUCTION_DESTROYED_DOWN : ch = Elements.CONSTRUCTION_DESTROYED_DOWN_TWICE; break;
                case CONSTRUCTION_DESTROYED_UP : ch = Elements.CONSTRUCTION_DESTROYED_UP_DOWN; break;
                case CONSTRUCTION_DESTROYED_LEFT : ch = Elements.CONSTRUCTION_DESTROYED_DOWN_LEFT; break;
                case CONSTRUCTION_DESTROYED_RIGHT : ch = Elements.CONSTRUCTION_DESTROYED_DOWN_RIGHT; break;
            }
        } else if (bulletDirection.equals(Direction.RIGHT)) {
            switch (ch) {
                case CONSTRUCTION : ch = Elements.CONSTRUCTION_DESTROYED_LEFT; break;
                case CONSTRUCTION_DESTROYED_LEFT : ch = Elements.CONSTRUCTION_DESTROYED_LEFT_TWICE; break;
                case CONSTRUCTION_DESTROYED_RIGHT : ch = Elements.CONSTRUCTION_DESTROYED_LEFT_RIGHT; break;
                case CONSTRUCTION_DESTROYED_UP : ch = Elements.CONSTRUCTION_DESTROYED_UP_LEFT; break;
                case CONSTRUCTION_DESTROYED_DOWN : ch = Elements.CONSTRUCTION_DESTROYED_DOWN_LEFT; break;
            }
        } else if (bulletDirection.equals(Direction.LEFT)) {
            switch (ch) {
                case CONSTRUCTION : ch = Elements.CONSTRUCTION_DESTROYED_RIGHT; break;
                case CONSTRUCTION_DESTROYED_RIGHT : ch = Elements.CONSTRUCTION_DESTROYED_RIGHT_TWICE; break;
                case CONSTRUCTION_DESTROYED_UP : ch = Elements.CONSTRUCTION_DESTROYED_RIGHT_UP; break;
                case CONSTRUCTION_DESTROYED_DOWN : ch = Elements.CONSTRUCTION_DESTROYED_DOWN_RIGHT; break;
                case CONSTRUCTION_DESTROYED_LEFT : ch = Elements.CONSTRUCTION_DESTROYED_LEFT_RIGHT; break;
            }
        } else if (bulletDirection.equals(Direction.DOWN)) {
            switch (ch) {
                case CONSTRUCTION : ch = Elements.CONSTRUCTION_DESTROYED_UP; break;
                case CONSTRUCTION_DESTROYED_UP : ch = Elements.CONSTRUCTION_DESTROYED_UP_TWICE; break;
                case CONSTRUCTION_DESTROYED_RIGHT : ch = Elements.CONSTRUCTION_DESTROYED_RIGHT_UP; break;
                case CONSTRUCTION_DESTROYED_DOWN : ch = Elements.CONSTRUCTION_DESTROYED_UP_DOWN; break;
                case CONSTRUCTION_DESTROYED_LEFT : ch = Elements.CONSTRUCTION_DESTROYED_UP_LEFT; break;
            }
        }
    }

    @Override
    public Elements state(Player player, Object... alsoAtPoint) {
        if (!destroyed()) {
            return ch;
        } else  {
            return Elements.NONE;
        }
    }

    @Override
    public void tick() {
        if (timer == REGENERATE_TIME) {
            timer = 0;
            reset();
        }
        if (destroyed()) {
            timer++;
        }
    }

    public void reset() {
        ch = Elements.CONSTRUCTION;
    }

    public boolean destroyed() {
        return ch.power == 0;
    }
}
