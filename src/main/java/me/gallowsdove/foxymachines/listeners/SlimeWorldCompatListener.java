package me.gallowsdove.foxymachines.listeners;

import me.gallowsdove.foxymachines.FoxyMachines;
import com.github.drakescraft_labs.slimefun4.legacy.api.BlockStorage;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.event.world.WorldUnloadEvent;

import java.util.Map;

public class SlimeWorldCompatListener implements Listener {

    @EventHandler
    public void onWorldLoad(WorldLoadEvent e) {
        syncChunkLoaders(e.getWorld());
    }

    @EventHandler
    public void onWorldUnload(WorldUnloadEvent e) {
        clearChunkLoaders(e.getWorld());
    }

    public void syncChunkLoaders(World world) {
        Map<Location, ?> storage = BlockStorage.getRawStorage(world);
        if (storage == null) return;

        boolean enabled = FoxyMachines.getInstance().getConfig().getBoolean("chunk-loaders-enabled", false);

        for (Location loc : storage.keySet()) {
            try {
                String id = BlockStorage.checkID(loc);
                if (id != null && id.equals("CHUNK_LOADER")) {
                    loc.getChunk().setForceLoaded(enabled);
                }
            } catch (Exception ignored) {
            }
        }
    }

    private void clearChunkLoaders(World world) {
        Map<Location, ?> storage = BlockStorage.getRawStorage(world);
        if (storage == null) return;

        for (Location loc : storage.keySet()) {
            try {
                String id = BlockStorage.checkID(loc);
                if (id != null && id.equals("CHUNK_LOADER")) {
                    loc.getChunk().setForceLoaded(false);
                }
            } catch (Exception ignored) {
            }
        }
    }

}
