package us._donut_.skuniversal.pvplevels;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.MathiasMC.PvPLevels.PvPLevelsAPI;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

public class ExprKDR extends SimpleExpression<Number> {

    private Expression<OfflinePlayer> player;

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Number> getReturnType() {
        return Number.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        player = (Expression<OfflinePlayer>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "kdr of player";
    }

    @Override
    @Nullable
    protected Number[] get(Event e) {
        PvPLevelsAPI pvp = new PvPLevelsAPI();
        int deaths;
        if (pvp.CurrentDeathsOfflinePlayer(player.getSingle(e))==0) {
            deaths = 1;
        } else {
            deaths = pvp.CurrentDeathsOfflinePlayer(player.getSingle(e));
        }
        float kdr = (float) pvp.CurrentKillsOfflinePlayer(player.getSingle(e))/deaths;
        return new Number[]{kdr};
    }
}
