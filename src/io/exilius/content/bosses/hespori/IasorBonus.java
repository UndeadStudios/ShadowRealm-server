package io.exilius.content.bosses.hespori;

import java.util.concurrent.TimeUnit;

import io.exilius.Configuration;
import io.exilius.content.QuestTab;
import io.exilius.model.entity.player.Player;
import io.exilius.model.entity.player.PlayerHandler;

public class IasorBonus implements HesporiBonus {
    @Override
    public void activate(Player player) {
        Configuration.DOUBLE_DROPS_TIMER += TimeUnit.HOURS.toMillis(1) / 600;
        PlayerHandler.executeGlobalMessage("@bla@[@gre@Hespori@bla@] @blu@" + player.getDisplayNameFormatted() + " @bla@sprouted the Iasor and it is granting 1 hr of double drops!");
        QuestTab.updateAllQuestTabs();
    }

    @Override
    public void deactivate() {
        updateObject(false);
        Hespori.activeIasorSeed = false;
        Hespori.IASOR_TIMER = 0;
    }

    @Override
    public boolean canPlant(Player player) {
        return true;
    }

    @Override
    public HesporiBonusPlant getPlant() {
        return HesporiBonusPlant.IASOR;
    }
}
