package us._donut_.skuniversal.shopkeepers;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import com.nisovin.shopkeepers.events.ShopkeeperCreatedEvent;
import com.nisovin.shopkeepers.events.ShopkeeperDeletedEvent;
import com.nisovin.shopkeepers.events.ShopkeeperTradeCompletedEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ShopkeepersRegister {
    public static void registerShopekeepers() {
        if (Bukkit.getServer().getPluginManager().getPlugin("Shopkeepers") != null) {

            //Conditions
            Skript.registerCondition(CondIsKeeper.class, "%entity% is [a] [shop]keeper");
            Skript.registerCondition(CondKeeperExists.class, "[a] [shop]keeper [(named|with name)] %string% exists");

            //Effects
            Skript.registerEffect(EffDeleteKeeper.class, "(delete|remove) [the] [shop]keeper [(named|with name)] %string%");

            //Expressions
            Skript.registerExpression(ExprKeepers.class, String.class, ExpressionType.SIMPLE, "[[the ]names of] [all] [the] [existing] [shop]keepers");
            Skript.registerExpression(ExprKeeperLoc.class, Location.class, ExpressionType.SIMPLE, "[the] loc[ation] of [the] [shop]keeper [(named|with name)] %string%");
            Skript.registerExpression(ExprKeeperName.class, String.class, ExpressionType.COMBINED, "[the] [shop]keeper name of [the] %entity%", "%entity%'s [shop]keeper name");
            Skript.registerExpression(ExprKeeperAmount.class, Number.class, ExpressionType.SIMPLE, "[the] (amount|number) of [shop]keepers of %player%");

            //Events
            Skript.registerEvent("Shopkeeper Create", SimpleEvent.class, ShopkeeperCreatedEvent.class, "[on] [shop]keeper creat(e|ion)");
            EventValues.registerEventValue(ShopkeeperCreatedEvent.class, Player.class, new Getter<Player, ShopkeeperCreatedEvent>() {
                public Player get(ShopkeeperCreatedEvent e) {
                    return e.getPlayer();
                }
            }, 0);
            EventValues.registerEventValue(ShopkeeperCreatedEvent.class, Location.class, new Getter<Location, ShopkeeperCreatedEvent>() {
                public Location get(ShopkeeperCreatedEvent e) {
                    return e.getShopkeeper().getLocation();
                }
            }, 0);
            Skript.registerEvent("Shopkeeper Delete", SimpleEvent.class, ShopkeeperDeletedEvent.class, "[on] [shop]keeper (delet(e|ion)|remov(e|al))");
            EventValues.registerEventValue(ShopkeeperDeletedEvent.class, Player.class, new Getter<Player, ShopkeeperDeletedEvent>() {
                public Player get(ShopkeeperDeletedEvent e) {
                    return e.getPlayer();
                }
            }, 0);
            EventValues.registerEventValue(ShopkeeperDeletedEvent.class, Location.class, new Getter<Location, ShopkeeperDeletedEvent>() {
                public Location get(ShopkeeperDeletedEvent e) {
                    return e.getShopkeeper().getLocation();
                }
            }, 0);
            EventValues.registerEventValue(ShopkeeperDeletedEvent.class, String.class, new Getter<String, ShopkeeperDeletedEvent>() {
                public String get(ShopkeeperDeletedEvent e) {
                    return e.getShopkeeper().getName();
                }
            }, 0);
            Skript.registerEvent("Shopkeeper Trade Complete", SimpleEvent.class, ShopkeeperTradeCompletedEvent.class, "[on] [shop]keeper trad(e|ing) complet(e|ion)");
            EventValues.registerEventValue(ShopkeeperTradeCompletedEvent.class, Player.class, new Getter<Player, ShopkeeperTradeCompletedEvent>() {
                public Player get(ShopkeeperTradeCompletedEvent e) {
                    return e.getPlayer();
                }
            }, 0);
            EventValues.registerEventValue(ShopkeeperTradeCompletedEvent.class, String.class, new Getter<String, ShopkeeperTradeCompletedEvent>() {
                public String get(ShopkeeperTradeCompletedEvent e) {
                    return e.getShopkeeper().getName();
                }
            }, 0);
            EventValues.registerEventValue(ShopkeeperTradeCompletedEvent.class, ItemStack.class, new Getter<ItemStack, ShopkeeperTradeCompletedEvent>() {
                public ItemStack get(ShopkeeperTradeCompletedEvent e) {
                    return e.getClickEvent().getCurrentItem();
                }
            }, 0);
        }
    }
}
