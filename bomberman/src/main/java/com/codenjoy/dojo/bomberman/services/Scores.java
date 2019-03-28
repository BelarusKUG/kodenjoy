package com.codenjoy.dojo.bomberman.services;

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


import com.codenjoy.dojo.services.PlayerScores;
import com.codenjoy.dojo.services.settings.Parameter;
import com.codenjoy.dojo.services.settings.Settings;

public class Scores implements PlayerScores {

    private final Parameter<Integer> killWallScore;
    private final Parameter<Integer> killMeatChopperScore;
    private final Parameter<Integer> killOtherBombermanScore;
    private final Parameter<Integer> killBomermanPenalty;

    private volatile int score;

    public Scores(int startScore, Settings settings) {
        this.score = startScore;
        killWallScore = settings.addEditBox("Kill wall score").type(Integer.class).def(10);
        killMeatChopperScore = settings.addEditBox("Kill meat chopper score").type(Integer.class).def(100);
        killOtherBombermanScore = settings.addEditBox("Kill other bomberman score").type(Integer.class).def(1000);
        killBomermanPenalty = settings.addEditBox("Kill your bomberman penalty").type(Integer.class).def(50);
    }

    @Override
    public int clear() {
        return score = 0;
    }

    @Override
    public Integer getScore() {
        return score;
    }

    @Override
    public void event(Object event) {
        if (event.equals(Events.KILL_BOMBERMAN)) {
            score -= killBomermanPenalty.getValue();
        } else if (event.equals(Events.KILL_OTHER_BOMBERMAN)) {
            score += killOtherBombermanScore.getValue();
        } else if (event.equals(Events.KILL_MEAT_CHOPPER)) {
            score += killMeatChopperScore.getValue();
        } else if (event.equals(Events.KILL_DESTROY_WALL)) {
            score += killWallScore.getValue();
        }
        score = Math.max(0, score);
    }
}
