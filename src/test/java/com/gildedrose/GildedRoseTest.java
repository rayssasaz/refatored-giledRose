package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    private Item updateSingleItem(String name, int sellIn, int quality) {
        Item[] items = new Item[] { new Item(name, sellIn, quality) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        return app.items[0];
    }

    @Test
    void normalItemDecreasesSellInAndQualityByOneBeforeSellDate() {
        Item item = updateSingleItem("+5 Dexterity Vest", 10, 20);
        assertEquals(9, item.sellIn);
        assertEquals(19, item.quality);
    }

    @Test
    void normalItemDecreasesQualityTwiceAsFastOnAndAfterSellDate() {
        Item onSellDate = updateSingleItem("Elixir of the Mongoose", 0, 10);
        assertEquals(-1, onSellDate.sellIn);
        assertEquals(8, onSellDate.quality);

        Item pastSellDate = updateSingleItem("Elixir of the Mongoose", -1, 10);
        assertEquals(-2, pastSellDate.sellIn);
        assertEquals(8, pastSellDate.quality);
    }

    @Test
    void normalItemQualityIsNeverNegative() {
        Item zeroQuality = updateSingleItem("foo", 5, 0);
        assertEquals(4, zeroQuality.sellIn);
        assertEquals(0, zeroQuality.quality);

        Item oneQualityExpired = updateSingleItem("foo", 0, 1);
        assertEquals(-1, oneQualityExpired.sellIn);
        assertEquals(0, oneQualityExpired.quality);
    }

    @Test
    void agedBrieIncreasesQualityOverTime() {
        Item beforeSellDate = updateSingleItem("Aged Brie", 2, 0);
        assertEquals(1, beforeSellDate.sellIn);
        assertEquals(1, beforeSellDate.quality);

        Item afterSellDate = updateSingleItem("Aged Brie", 0, 10);
        assertEquals(-1, afterSellDate.sellIn);
        assertEquals(12, afterSellDate.quality);
    }

    @Test
    void agedBrieQualityNeverExceedsFifty() {
        Item atMax = updateSingleItem("Aged Brie", 5, 50);
        assertEquals(4, atMax.sellIn);
        assertEquals(50, atMax.quality);

        Item nearMaxAfterSellDate = updateSingleItem("Aged Brie", 0, 49);
        assertEquals(-1, nearMaxAfterSellDate.sellIn);
        assertEquals(50, nearMaxAfterSellDate.quality);
    }

    @Test
    void sulfurasNeverChangesSellInOrQuality() {
        Item sulfurasZero = updateSingleItem("Sulfuras, Hand of Ragnaros", 0, 80);
        assertEquals(0, sulfurasZero.sellIn);
        assertEquals(80, sulfurasZero.quality);

        Item sulfurasNegative = updateSingleItem("Sulfuras, Hand of Ragnaros", -1, 80);
        assertEquals(-1, sulfurasNegative.sellIn);
        assertEquals(80, sulfurasNegative.quality);
    }

    @Test
    void backstagePassesIncreaseQualityByOneWhenMoreThanTenDaysLeft() {
        Item item = updateSingleItem("Backstage passes to a TAFKAL80ETC concert", 15, 20);
        assertEquals(14, item.sellIn);
        assertEquals(21, item.quality);

        Item elevenDays = updateSingleItem("Backstage passes to a TAFKAL80ETC concert", 11, 20);
        assertEquals(10, elevenDays.sellIn);
        assertEquals(21, elevenDays.quality);
    }

    @Test
    void backstagePassesIncreaseQualityByTwoWhenTenToSixDaysLeft() {
        Item tenDays = updateSingleItem("Backstage passes to a TAFKAL80ETC concert", 10, 20);
        assertEquals(9, tenDays.sellIn);
        assertEquals(22, tenDays.quality);

        Item sixDays = updateSingleItem("Backstage passes to a TAFKAL80ETC concert", 6, 20);
        assertEquals(5, sixDays.sellIn);
        assertEquals(22, sixDays.quality);
    }

    @Test
    void backstagePassesIncreaseQualityByThreeWhenFiveToOneDaysLeft() {
        Item fiveDays = updateSingleItem("Backstage passes to a TAFKAL80ETC concert", 5, 20);
        assertEquals(4, fiveDays.sellIn);
        assertEquals(23, fiveDays.quality);

        Item oneDay = updateSingleItem("Backstage passes to a TAFKAL80ETC concert", 1, 20);
        assertEquals(0, oneDay.sellIn);
        assertEquals(23, oneDay.quality);
    }

    @Test
    void backstagePassesQualityNeverExceedsFifty() {
        Item capped = updateSingleItem("Backstage passes to a TAFKAL80ETC concert", 5, 49);
        assertEquals(4, capped.sellIn);
        assertEquals(50, capped.quality);
    }

    @Test
    void backstagePassesDropToZeroAfterConcert() {
        Item afterConcert = updateSingleItem("Backstage passes to a TAFKAL80ETC concert", 0, 20);
        assertEquals(-1, afterConcert.sellIn);
        assertEquals(0, afterConcert.quality);
    }

    @Test
    void multiDaySimulationMatchesLegacyBehavior() {
        Item[] items = new Item[] {
                new Item("+5 Dexterity Vest", 10, 20),
                new Item("Aged Brie", 2, 0),
                new Item("Elixir of the Mongoose", 5, 7),
                new Item("Sulfuras, Hand of Ragnaros", 0, 80),
                new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20)
        };
        GildedRose app = new GildedRose(items);

        for (int day = 0; day < 15; day++) {
            app.updateQuality();
        }

        assertEquals("-5, 0", items[0].sellIn + ", " + items[0].quality);
        assertEquals("-13, 28", items[1].sellIn + ", " + items[1].quality);
        assertEquals("-10, 0", items[2].sellIn + ", " + items[2].quality);
        assertEquals("0, 80", items[3].sellIn + ", " + items[3].quality);
        assertEquals("0, 50", items[4].sellIn + ", " + items[4].quality);
    }
}
