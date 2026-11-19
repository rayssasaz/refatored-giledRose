package com.gildedrose.updater;

import com.gildedrose.Item;

/**
 * Standard item: quality degrades by 1 before sell date and by 2 on/after sell date.
 */
public class NormalItemUpdater extends BaseItemUpdater {
    @Override
    public void update(Item item) {
        decreaseQuality(item, 1);
        item.sellIn--;
        if (item.sellIn < 0) {
            decreaseQuality(item, 1);
        }
    }
}
