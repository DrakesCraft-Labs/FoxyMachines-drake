package me.gallowsdove.foxymachines.implementation.machines;

import dev.drake.infinitylib.common.Scheduler;
import com.github.drakescraft_labs.slimefun4.api.events.PlayerRightClickEvent;
import com.github.drakescraft_labs.slimefun4.api.items.SlimefunItem;
import com.github.drakescraft_labs.slimefun4.api.recipes.RecipeType;
import com.github.drakescraft_labs.slimefun4.core.handlers.BlockBreakHandler;
import com.github.drakescraft_labs.slimefun4.core.handlers.BlockUseHandler;
import com.github.drakescraft_labs.slimefun4.implementation.SlimefunItems;
import me.gallowsdove.foxymachines.FoxyMachines;
import me.gallowsdove.foxymachines.Items;
import com.github.drakescraft_labs.slimefun4.legacy.api.BlockStorage;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.UUID;


public class ChunkLoader extends SlimefunItem {
    public ChunkLoader() {
        super(Items.MACHINES_ITEM_GROUP, Items.CHUNK_LOADER, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
                Items.REINFORCED_STRING, Items.STABILIZED_BLISTERING_BLOCK, Items.REINFORCED_STRING,
                SlimefunItems.ENRICHED_NETHER_ICE, Items.STABILIZED_BLISTERING_BLOCK, Items.WIRELESS_TRANSMITTER,
                Items.REINFORCED_STRING, Items.STABILIZED_BLISTERING_BLOCK, Items.REINFORCED_STRING
        });
    }

    @Override
    public void preRegister() {
        addItemHandler(onBreak(), onBlockUse());
    }

    @Nonnull
    private BlockBreakHandler onBreak() {
        return new BlockBreakHandler(false, false) {
            @Override
            public void onPlayerBreak(@Nonnull BlockBreakEvent e, @Nonnull ItemStack item, @Nonnull List<ItemStack> drops) {
                Block b = e.getBlock();
                String owner = BlockStorage.getLocationInfo(b.getLocation(), "owner");
                if (owner != null) {
                    try {
                        FoxyMachines.getInstance().getChunkLoaderQuotaService().release(UUID.fromString(owner));
                    } catch (IllegalArgumentException ignored) {
                        FoxyMachines.log(java.util.logging.Level.WARNING, "Ignoring Chunk Loader with invalid owner data at " + b.getLocation());
                    }

                    b.getChunk().setForceLoaded(false);
                    BlockStorage.clearBlockInfo(b);
                }

                Scheduler.run(() -> b.setType(Material.GLASS));
            }
        };
    }

    @Nonnull
    private BlockUseHandler onBlockUse() {
        return PlayerRightClickEvent::cancel;
    }

}
