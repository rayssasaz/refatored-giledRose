package com.gildedrose.updater;

import com.gildedrose.Item;

/**
 * Backstage passes: quality increases as concert approaches and drops to 0 after sell date.
 */
public class BackstagePassUpdater extends BaseItemUpdater {
    @Override
    public void update(Item item) {
        increaseQuality(item, 1);
        if (item.sellIn < 11) {
            increaseQuality(item, 1);
        }
        if (item.sellIn < 6) {
            increaseQuality(item, 1);
        }

        item.sellIn--;
        if (item.sellIn < 0) {
            item.quality = MIN_QUALITY;
        }
    }
}
