package xyz.airdog46.miniessentials.listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import xyz.airdog46.miniessentials.commands.SetZombieEntityCommand;

public class ZombieMobListener implements Listener {
    SetZombieEntityCommand aaaa = new SetZombieEntityCommand();

    @EventHandler
    public void onZombieMobDeath(EntityDeathEvent e) {
        if (aaaa.isZombieModeEnabled())
            if (e.getEntityType() != EntityType.PLAYER)
                for (int i = 0; i < 2; i++)
                    e.getEntity().getWorld().spawnEntity(e.getEntity().getLocation(), e.getEntityType());
    }
}
