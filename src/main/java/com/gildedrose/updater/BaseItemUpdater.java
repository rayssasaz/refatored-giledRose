package com.gildedrose.updater;

import com.gildedrose.Item;

/**
 * Shared quality-bounding helpers for item update strategies.
 */
abstract class BaseItemUpdater implements ItemUpdater {
    protected static final int MAX_QUALITY = 50;
    protected static final int MIN_QUALITY = 0;

    protected void increaseQuality(Item item, int amount) {
        item.quality = Math.min(MAX_QUALITY, item.quality + amount);
    }

    protected void decreaseQuality(Item item, int amount) {
        item.quality = Math.max(MIN_QUALITY, item.quality - amount);
    }
}
