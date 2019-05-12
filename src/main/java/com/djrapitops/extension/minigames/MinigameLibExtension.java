/*
    Copyright(c) 2019 Risto Lahtela (Rsl1122)

    The MIT License(MIT)

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files(the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and / or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions :
    The above copyright notice and this permission notice shall be included in
    all copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
    THE SOFTWARE.
*/
package com.djrapitops.extension.minigames;

import com.comze_instancelabs.minigamesapi.sql.MainSQL;
import com.djrapitops.extension.FakePlayer;
import com.djrapitops.plan.extension.CallEvents;
import com.djrapitops.plan.extension.DataExtension;
import com.djrapitops.plan.extension.annotation.NumberProvider;
import com.djrapitops.plan.extension.icon.Color;

import java.util.UUID;

/**
 * Abstract representation of any minigame.
 *
 * @author Rsl1122
 */
public abstract class MinigameLibExtension implements DataExtension {

    private final MainSQL sql;

    public MinigameLibExtension(MainSQL sql) {
        this.sql = sql;
    }

    @Override
    public CallEvents[] callExtensionMethodsOn() {
        return new CallEvents[]{
                CallEvents.PLAYER_LEAVE
        };
    }

    @NumberProvider(
            text = "Wins",
            description = "How many wins the player has",
            priority = 10,
            iconName = "trophy",
            iconColor = Color.AMBER,
            showInPlayerTable = true
    )
    public long wins(UUID playerUUID) {
        return sql.getWins(new FakePlayer(playerUUID));
    }

    @NumberProvider(
            text = "Points",
            description = "How many points the player has",
            priority = 9,
            iconName = "plus",
            iconColor = Color.GREEN
    )
    public long points(UUID playerUUID) {
        return sql.getPoints(new FakePlayer(playerUUID));
    }

    @NumberProvider(
            text = "Game Points",
            description = "How many game points the player has",
            priority = 9,
            iconName = "plus",
            iconColor = Color.GREEN
    )
    public long gamePoints(UUID playerUUID) {
        return sql.getGamePoints(new FakePlayer(playerUUID));
    }
}