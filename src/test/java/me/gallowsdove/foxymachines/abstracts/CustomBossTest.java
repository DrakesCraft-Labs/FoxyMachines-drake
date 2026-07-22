package me.gallowsdove.foxymachines.abstracts;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomBossTest {
    @Test
    void bossBarProgressAlwaysStaysInsideBukkitRange() {
        assertEquals(0.0D, BossBarProgress.sanitize(-0.2D));
        assertEquals(0.0D, BossBarProgress.sanitize(Double.NaN));
        assertEquals(0.5D, BossBarProgress.sanitize(0.5D));
        assertEquals(1.0D, BossBarProgress.sanitize(1.037593984962406D));
        assertEquals(1.0D, BossBarProgress.sanitize(Double.POSITIVE_INFINITY));
    }
}
