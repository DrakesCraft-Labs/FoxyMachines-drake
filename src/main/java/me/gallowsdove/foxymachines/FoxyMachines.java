package me.gallowsdove.foxymachines;


import dev.drake.infinitylib.common.Events;
import dev.drake.infinitylib.common.Scheduler;
import dev.drake.infinitylib.core.AbstractAddon;

import lombok.SneakyThrows;
import me.gallowsdove.foxymachines.abstracts.AbstractWand;
import me.gallowsdove.foxymachines.abstracts.CustomBoss;
import me.gallowsdove.foxymachines.commands.KillallCommand;
import me.gallowsdove.foxymachines.commands.ListallCommand;
import me.gallowsdove.foxymachines.commands.QuestCommand;
import me.gallowsdove.foxymachines.commands.SacrificialAltarCommand;
import me.gallowsdove.foxymachines.commands.SummonCommand;
import me.gallowsdove.foxymachines.implementation.consumables.UnbreakableRune;
import me.gallowsdove.foxymachines.implementation.machines.ForcefieldDome;
import me.gallowsdove.foxymachines.implementation.tools.BerryBushTrimmer;
import me.gallowsdove.foxymachines.listeners.*;
import me.gallowsdove.foxymachines.tasks.GhostBlockTask;
import me.gallowsdove.foxymachines.tasks.MobTicker;
import me.gallowsdove.foxymachines.tasks.QuestTicker;
import me.gallowsdove.foxymachines.utils.QuestUtils;
import me.gallowsdove.foxymachines.services.ChunkLoaderQuotaService;

import javax.annotation.Nonnull;
import java.io.File;

import org.bukkit.Bukkit;

public class FoxyMachines extends AbstractAddon {
    private static FoxyMachines instance;

    public String folderPath;
    private ChunkLoaderQuotaService chunkLoaderQuotaService;

    public FoxyMachines() {
        super("DrakesCraft-Labs", "FoxyMachines-drake", "main", "auto-update");
    }

    @Override
    @SneakyThrows
    public void enable() {
        instance = this;

        this.folderPath = getDataFolder().getAbsolutePath() + File.separator + "data-storage" + File.separator;
        this.chunkLoaderQuotaService = new ChunkLoaderQuotaService(this);

        Events.registerListener(new ChunkLoadListener());
        Events.registerListener(new ChunkLoaderListener());
        Events.registerListener(chunkLoaderQuotaService);
        SlimeWorldCompatListener slimeWorldCompatListener = new SlimeWorldCompatListener();
        Events.registerListener(slimeWorldCompatListener);
        Bukkit.getWorlds().forEach(slimeWorldCompatListener::syncChunkLoaders);
        Events.registerListener(new BoostedRailListener());
        Events.registerListener(new BerryBushListener());
        Events.registerListener(new ForcefieldListener());
        Events.registerListener(new GhostBlockListener());
        Events.registerListener(new RemoteControllerListener());
        Events.registerListener(new SacrificialAltarListener());
        Events.registerListener(new SwordListener());
        Events.registerListener(new PoseidonsFishingRodListener());
        Events.registerListener(new ArmorListener());
        Events.registerListener(new BowListener());
        Events.registerListener(new PositionSelectorListener());

        QuestUtils.init();
        AbstractWand.init();
        UnbreakableRune.init();
        ItemSetup.INSTANCE.init();
        ResearchSetup.INSTANCE.init();

        BerryBushTrimmer.loadTrimmedBlocks();
        ForcefieldDome.loadDomeLocations();
        Scheduler.run(() -> ForcefieldDome.INSTANCE.setupDomes());
        Scheduler.repeat(240, 10, new QuestTicker());
        Scheduler.repeat(100, new GhostBlockTask());
        if (getConfig().getBoolean("custom-mobs")) {
            Scheduler.repeat(2, new MobTicker());
        }



        getAddonCommand().addSub(new KillallCommand()).addSub((new QuestCommand())).
                addSub(new SacrificialAltarCommand()).addSub(new SummonCommand()).addSub(new ListallCommand());

    }

    @SneakyThrows
    @Override
    public void disable() {
        BerryBushTrimmer.saveTrimmedBlocks();
        ForcefieldDome.saveDomeLocations();
        if (getConfig().getBoolean("custom-mobs")) {
            CustomBoss.removeBossBars();
        }
    }

    @Nonnull
    public static FoxyMachines getInstance() {
        return instance;
    }

    @Nonnull
    public ChunkLoaderQuotaService getChunkLoaderQuotaService() {
        return chunkLoaderQuotaService;
    }
}
