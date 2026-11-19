package com.gildedrose;

class GildedRose {
    private static final String AGED_BRIE = "Aged Brie";
    private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";

    private static final int MAX_QUALITY = 50;
    private static final int MIN_QUALITY = 0;

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            updateItem(item);
        }
    }

    private void updateItem(Item item) {
        if (SULFURAS.equals(item.name)) {
            return;
        }

        if (AGED_BRIE.equals(item.name)) {
            updateAgedBrie(item);
        } else if (BACKSTAGE_PASSES.equals(item.name)) {
            updateBackstagePasses(item);
        } else {
            updateNormalItem(item);
        }
    }

    private void updateAgedBrie(Item item) {
        increaseQuality(item, 1);
        item.sellIn--;
        if (item.sellIn < 0) {
            increaseQuality(item, 1);
        }
    }

    private void updateBackstagePasses(Item item) {
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

    private void updateNormalItem(Item item) {
        decreaseQuality(item, 1);
        item.sellIn--;
        if (item.sellIn < 0) {
            decreaseQuality(item, 1);
        }
    }

    private void increaseQuality(Item item, int amount) {
        item.quality = Math.min(MAX_QUALITY, item.quality + amount);
    }

    private void decreaseQuality(Item item, int amount) {
        item.quality = Math.max(MIN_QUALITY, item.quality - amount);
    }
}
