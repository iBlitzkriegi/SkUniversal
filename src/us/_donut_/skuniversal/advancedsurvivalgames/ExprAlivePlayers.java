package us._donut_.skuniversal.advancedsurvivalgames;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import e.Game;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ExprAlivePlayers extends SimpleExpression<Player> {

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends Player> getReturnType() {
        return Player.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "the survival games alive players";
    }

    @Override
    @Nullable
    protected Player[] get(Event e) {
        List<Player> alivePlayers = new ArrayList<>();
        Set<OfflinePlayer> players = Game.getAlivePlayers();
        for (OfflinePlayer p : players) {
            alivePlayers.add(p.getPlayer());
        }
        return alivePlayers.toArray(new Player[alivePlayers.size()]);
    }
}
