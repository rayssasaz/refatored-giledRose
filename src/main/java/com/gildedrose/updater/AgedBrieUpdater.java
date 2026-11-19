package com.gildedrose.updater;

import com.gildedrose.Item;

/**
 * Aged Brie: quality increases by 1 before sell date and by 2 on/after sell date, up to 50.
 */
public class AgedBrieUpdater extends BaseItemUpdater {
    @Override
    public void update(Item item) {
        increaseQuality(item, 1);
        item.sellIn--;
        if (item.sellIn < 0) {
            increaseQuality(item, 1);
        }
    }
}
