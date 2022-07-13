/*
    Copyright(c) 2019 AuroraLS3

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
package net.playeranalytics.extension.minigameslib;

import com.comze_instancelabs.minigamesapi.MinigamesAPI;
import com.comze_instancelabs.minigamesapi.PluginInstance;
import com.comze_instancelabs.minigamesapi.sql.MainSQL;
import com.djrapitops.plan.extension.DataExtension;
import net.playeranalytics.extension.minigameslib.minigames.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * In charge of creating a data extension for each minigame via MinigameLib API.
 *
 * @author AuroraLS3
 */
public class MinigameFinder {

    private MinigamesAPI api;

    public MinigameFinder() {
        api = MinigamesAPI.getAPI();
        if (api == null) {
            throw new IllegalStateException();
        }
    }

    public Optional<MainSQL> getPluginSQL(String name) {
        try {
            return Optional.ofNullable((JavaPlugin) Bukkit.getPluginManager().getPlugin(name))
                    .map(api::getPluginInstance)
                    .map(PluginInstance::getSQLInstance);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public List<DataExtension> findMinigames() {
        List<DataExtension> extensions = new ArrayList<>();

        getPluginSQL("MGBedWars").map(BedWarsExtension::new).ifPresent(extensions::add);
        getPluginSQL("MGBowBash").map(BowBashExtension::new).ifPresent(extensions::add);
        getPluginSQL("MGColorMatch").map(ColorMatchExtension::new).ifPresent(extensions::add);
        getPluginSQL("MGConquer").map(ConquerExtension::new).ifPresent(extensions::add);
        getPluginSQL("MGDeathRun").map(DeathRunExtension::new).ifPresent(extensions::add);
        getPluginSQL("MGFlyingCars").map(FlyingCarsExtension::new).ifPresent(extensions::add);
        getPluginSQL("MGGunGame").map(GunGameExtension::new).ifPresent(extensions::add);
        getPluginSQL("MGHorseRacingPlus").map(HorseRacingPlusExtension::new).ifPresent(extensions::add);
        getPluginSQL("MGJumper").map(JumperExtension::new).ifPresent(extensions::add);
        getPluginSQL("MGMobEscape").map(MobEscapeExtension::new).ifPresent(extensions::add);
        getPluginSQL("MGNoteBlockBlitz").map(NoteBlockBlitzExtension::new).ifPresent(extensions::add);
        getPluginSQL("MGOneInTheChamber").map(OneInTheChamberExtension::new).ifPresent(extensions::add);
        getPluginSQL("MGSeaBattle").map(SeaBattleExtension::new).ifPresent(extensions::add);
        getPluginSQL("MGSkyWars").map(SkyWarsExtension::new).ifPresent(extensions::add);
        getPluginSQL("MGSnake").map(SnakeExtension::new).ifPresent(extensions::add);
        getPluginSQL("MGSplegg").map(SpleggExtension::new).ifPresent(extensions::add);
        getPluginSQL("MGSudoku").map(SudokuExtension::new).ifPresent(extensions::add);
        getPluginSQL("MGTrapdoorSpleef").map(TrapdoorSpleefExtension::new).ifPresent(extensions::add);
        getPluginSQL("MGWarlock").map(WarlockExtension::new).ifPresent(extensions::add);
        getPluginSQL("MGWarlockTactical").map(WarlockTacticalExtension::new).ifPresent(extensions::add);

        return extensions;
    }
}