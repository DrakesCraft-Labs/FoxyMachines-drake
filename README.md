# FoxyMachines Drake

FoxyMachines Drake is the maintained DrakesCraft distribution of the FoxyMachines Slimefun addon. It keeps the original item IDs, recipes and stored data so established worlds and player inventories remain compatible while the runtime is updated for the current platform.

**Runtime:** Paper 1.21.1 compatible server, Java 21, Slimefun Drake 11 and InfinityLib Drake.

## Operational guarantees

- Plugin identity remains `FoxyMachines`; existing Slimefun items and PDC data continue to resolve.
- Chunk-loader quota releases made while the owner is offline are persisted and applied on their next join.
- Forcefield domes no longer retain Bukkit `Block` instances, do not modify world state from async tasks, and only remove barriers created by the dome itself.
- Wands reject protected internal blocks and validate their selected volume before changing terrain.
- The addon does not self-update. Releases are built, verified and staged through DrakesCraft operations.

## Build

```bash
mvn -B -ntp clean verify
```

The distributable artifact is `target/FoxyMachines-drake.jar`.

## Deployment

1. Back up `/plugins/FoxyMachines-drake.jar` and `/plugins/FoxyMachines/`.
2. Upload the verified artifact with the same plugin filename.
3. Restart only during a planned maintenance window.
4. Confirm that Slimefun registers FoxyMachines without migration errors and validate an existing machine before announcing the release.

The previous JAR is the rollback artifact. Do not run two FoxyMachines JARs at once.

## Configuration

`plugins/FoxyMachines/config.yml` retains the established options. New installations default `auto-update` to `false`; no remote update service is used by this distribution.

Individual items can still be disabled through Slimefun's `items.yml` configuration when appropriate.

## Bosses
* Pixie Queen
* Headless Horseman

## Other Mobs
* Pixie
* Helldog

## Tools
* Electric Wind Staff
* Electric Fire Staff
* Electric Fire Staff II
* Berry Bush Trimmer
* Poseidon's Fishing Rod
* Remote Controller
* Ghost Block Remover
* Position Selector
* Fill Wand
* Sponge Wand

## Weapons
* Healing Bow
* Cursed Sword
* Celestial Sword
* Elucidator
* Acri Arcum

## Armor
* Aquatic Helmet
* Resistant Chestplate
* Fiery Leggings
* Light Boots

## Machines
* Improvement Forge
* Potion Mixer
* Electric Gold Refinery
* Chunk Loader
* Boosted Rails
* Forcefield Dome

## Others
* Sacrificial Altar
* Ghost Blocks

## Provenance

This repository was split from the DrakesCraft community-addons monorepo while preserving its history. Original FoxyMachines authorship remains credited in source and project metadata; DrakesCraft Labs maintains this distribution and its deployment process.
