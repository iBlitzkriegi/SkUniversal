package us._donut_.skuniversal.advancedban;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.leoko.advancedban.manager.PunishmentManager;
import me.leoko.advancedban.manager.UUIDManager;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

public class CondBanned extends Condition {

    private Expression<OfflinePlayer> player;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        player = (Expression<OfflinePlayer>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "player " + p.getSingle(e) + " is banned"
    }

    @Override
    public boolean check(Event e) {
        if(player.getSingle(e)!=null){
            return PunishmentManager.get().isBanned(UUIDManager.get().getUUID(player.getSingle(e).getName()));
        }else{
            Skript.error("Must provide a player, please refer to the syntax");
            return false;
        }
    }
}
