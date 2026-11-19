package com.gildedrose.updater;

import com.gildedrose.Item;

/**
 * Resolves the appropriate {@link ItemUpdater} strategy for a given {@link Item}.
 */
public final class ItemUpdaterFactory {
    private static final String AGED_BRIE = "Aged Brie";
    private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    private static final String CONJURED_PREFIX = "Conjured";

    private static final ItemUpdater NORMAL_UPDATER = new NormalItemUpdater();
    private static final ItemUpdater AGED_BRIE_UPDATER = new AgedBrieUpdater();
    private static final ItemUpdater BACKSTAGE_PASS_UPDATER = new BackstagePassUpdater();
    private static final ItemUpdater SULFURAS_UPDATER = new SulfurasUpdater();
    private static final ItemUpdater CONJURED_UPDATER = new ConjuredItemUpdater();

    private ItemUpdaterFactory() {
    }

    public static ItemUpdater forItem(Item item) {
        if (SULFURAS.equals(item.name)) {
            return SULFURAS_UPDATER;
        }
        if (AGED_BRIE.equals(item.name)) {
            return AGED_BRIE_UPDATER;
        }
        if (BACKSTAGE_PASSES.equals(item.name)) {
            return BACKSTAGE_PASS_UPDATER;
        }
        if (item.name != null && item.name.startsWith(CONJURED_PREFIX)) {
            return CONJURED_UPDATER;
        }
        return NORMAL_UPDATER;
    }
}
