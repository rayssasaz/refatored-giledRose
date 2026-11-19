package com.gildedrose.updater;

import com.gildedrose.Item;

/**
 * Strategy for updating an {@link Item}'s sellIn and quality at the end of each day.
 */
public interface ItemUpdater {
    void update(Item item);
}
