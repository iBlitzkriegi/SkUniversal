package us._donut_.skuniversal.shopkeepers;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import com.nisovin.shopkeepers.ShopkeepersPlugin;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

public class ExprKeeperName extends SimpleExpression<String> {

    private Expression<Entity> entity;

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        entity = (Expression<Entity>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "name of keeper";
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        ShopkeepersPlugin skp = ShopkeepersPlugin.getInstance();
        return new String[]{skp.getShopkeeperByEntity(entity.getSingle(e)).getName()};
    }

    @Override
    public void change(Event e, Object[] delta, Changer.ChangeMode mode){
        if (mode == Changer.ChangeMode.SET) {
            ShopkeepersPlugin skp = ShopkeepersPlugin.getInstance();
            skp.getShopkeeperByEntity(entity.getSingle(e)).setName((String) delta[0]);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            return CollectionUtils.array(String.class);
        }
        return null;
    }
}
