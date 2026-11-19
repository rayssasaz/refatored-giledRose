package com.gildedrose.updater;

import com.gildedrose.Item;

/**
 * Conjured item: quality degrades twice as fast as normal items (-2 before sell date, -4 on/after).
 */
public class ConjuredItemUpdater extends BaseItemUpdater {
    @Override
    public void update(Item item) {
        decreaseQuality(item, 2);
        item.sellIn--;
        if (item.sellIn < 0) {
            decreaseQuality(item, 2);
        }
    }
}
