package us._donut_.skuniversal.parties;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import com.alessiodp.parties.Parties;
import com.alessiodp.parties.utils.api.PartiesAPI;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

public class ExprLeader extends SimpleExpression<OfflinePlayer> {

    private Expression<String> name;

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends OfflinePlayer> getReturnType() {
        return OfflinePlayer.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        name = (Expression<String>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "leader of party";
    }

    @Override
    @Nullable
    protected OfflinePlayer[] get(Event e) {
        PartiesAPI parties = new PartiesAPI();
        return new OfflinePlayer[]{Bukkit.getOfflinePlayer(parties.getPartyLeader(name.getSingle(e)))};
    }

    @Override
    public void change(Event e, Object[] delta, Changer.ChangeMode mode){
        OfflinePlayer newLeader = (OfflinePlayer) delta[0];
        if (mode == Changer.ChangeMode.SET) {
            Parties.getInstance().getPartyHandler().loadParty(name.getSingle(e)).setLeader(newLeader.getUniqueId());
        }
    }

    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            return CollectionUtils.array(OfflinePlayer.class);
        }
        return null;
    }
}