# FoxyMachines Drake

<p>
  <a href="https://github.com/DrakesCraft-Labs/FoxyMachines-drake/actions"><img src="https://img.shields.io/github/actions/workflow/status/DrakesCraft-Labs/FoxyMachines-drake/maven.yml?branch=main&label=CI&style=flat-square" alt="CI"/></a>
  <img src="https://img.shields.io/badge/Minecraft-1.21.11-6d28d9?style=flat-square" alt="Minecraft 1.21.11"/>
  <img src="https://img.shields.io/badge/Java-21-f89820?style=flat-square" alt="Java 21"/>
  <img src="https://img.shields.io/badge/Slimefun-Drake%2011-581c87?style=flat-square" alt="Slimefun Drake 11"/>
</p>

FoxyMachines Drake es la distribución mantenida de FoxyMachines para el stack de
DrakesCraft. Añade máquinas, herramientas, equipo, mobs y progresión a Slimefun
sin reemplazar los IDs, recetas ni datos de mundo que ya usan los jugadores.

## Runtime compatible

| Componente | Objetivo |
|---|---|
| Minecraft / Paper / Purpur | **1.21.11** |
| Java | **21** |
| Slimefun | **Slimefun Drake 11** |
| API de compilación | `paper-api 1.21.1-R0.1-SNAPSHOT` |

La API Maven de Paper conserva la línea `1.21.1`; el JAR se compila contra el
core Slimefun Drake 11 y se destina al runtime Paper/Purpur 1.21.11. No es una
segunda línea de compatibilidad ni un downgrade del servidor.

## Contenido

| Área | Incluye |
|---|---|
| Máquinas | Improvement Forge, Potion Mixer, Electric Gold Refinery, Chunk Loader, Boosted Rails y Forcefield Dome. |
| Herramientas | Staves eléctricos, Remote Controller, Position Selector, Fill/Sponge Wand, Ghost Block Remover y caña de Poseidón. |
| Equipo | Armas, armaduras, materiales y runas de progresión. |
| Mundo | Pixie Queen, Headless Horseman, Pixie, Helldog, altar sacrificial y ghost blocks. |

## Trabajo Drake

- Separación del monorepo a un repositorio construible y versionado por separado.
- Java 21, Paper API actual y dependencias del core Drake declaradas de forma explícita.
- Sin autoactualizador remoto: las actualizaciones se compilan, verifican, despliegan y pueden revertirse.
- Las liberaciones de cuota de Chunk Loader para dueños offline se persisten y se aplican en su próximo `PlayerJoin`.
- Los domos de fuerza ya no retienen instancias `Block`/chunks, no escriben Bukkit desde tareas async y no eliminan barreras ajenas.
- Las varitas bloquean materiales internos y validan el volumen solicitado antes de modificar terreno.
- Potion Mixer usa la API moderna de tipos de poción de 1.21.11.

## Compatibilidad de datos

El plugin conserva `name: FoxyMachines`, su paquete Java, claves PDC, IDs de
Slimefun y recetas. Esta es una condición de diseño: un update no debe invalidar
inventarios, máquinas colocadas ni progreso existente.

Antes de una actualización de producción, respalda:

```text
plugins/FoxyMachines-drake.jar
plugins/FoxyMachines/
```

No cargues dos JAR de FoxyMachines a la vez. Conserva el JAR anterior como
rollback hasta validar una máquina ya colocada, un Chunk Loader y un Forcefield
Dome tras el reinicio.

## Configuración

`plugins/FoxyMachines/config.yml` conserva las opciones conocidas del addon.
Las instalaciones nuevas usan `auto-update: false`; este fork no descarga ni
reemplaza artefactos automáticamente. Los ítems opcionales siguen pudiendo
deshabilitarse mediante la configuración de Slimefun.

## Build

```bash
mvn -B -ntp clean verify
```

Artefacto: `target/FoxyMachines-drake.jar`.

## Procedencia

El código original de FoxyMachines mantiene el crédito a GallowsDove. Este
repositorio preserva el historial del módulo separado desde el monorepo de
DrakesCraft y documenta los cambios propios de compatibilidad y operación.
