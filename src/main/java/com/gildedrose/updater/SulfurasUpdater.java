package com.gildedrose.updater;

import com.gildedrose.Item;

/**
 * Sulfuras: legendary item whose sellIn and quality never change.
 */
public class SulfurasUpdater implements ItemUpdater {
    @Override
    public void update(Item item) {
        // Legendary item: no changes to sellIn or quality.
    }
}
