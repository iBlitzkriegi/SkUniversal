package us._donut_.skuniversal.lwc;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.griefcraft.lwc.LWC;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

public class CondAccess extends Condition {

    private Expression<Player> player;
    private Expression<Block> block;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        player = (Expression<Player>) e[0];
        block = (Expression<Block>) e[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "player has access to block";
    }

    @Override
    public boolean check(Event e) {
        return LWC.getInstance().canAccessProtection(player.getSingle(e), block.getSingle(e));
    }
}
