package me.gallowsdove.foxymachines.abstracts;

/** Normalizes computed health ratios before Bukkit receives them. */
final class BossBarProgress {
    private BossBarProgress() {
    }

    static double sanitize(double progress) {
        if (Double.isNaN(progress)) {
            return 0.0D;
        }
        return Math.clamp(progress, 0.0D, 1.0D);
    }
}
