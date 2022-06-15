package br.edu.ifsp.lp3a5.refactoring.gildedrose;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class GildedRoseTest {
	
	final private static String SULFURAS = "Sulfuras, Hand of Ragnaros";
	final private static String BRIE = "Aged Brie"; 
	final private static String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
	final private static String NORMAL_ITEM = "NORMAL ITEM";
	
	private Item createSulfuras() {
		return new Item(SULFURAS,-1,80);
	}
	
    void avancaDias(GildedRose app, int qtdDias) {
        for (int i = 0; i < qtdDias; i++) {
            app.updateQuality();
        }
    }

    @Test
    void testSulfuras() {
        Item[] items = new Item[] { createSulfuras() };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(SULFURAS, app.items[0].name);
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(80, app.items[0].quality);
    }
    
    @Test
    void testNormalItem() {
    	Item[] items = new Item[] { new Item(NORMAL_ITEM, 1, 15) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(NORMAL_ITEM, app.items[0].name);
        assertEquals(0, app.items[0].sellIn);
        assertEquals(14, app.items[0].quality);
        app.updateQuality();
        assertEquals(NORMAL_ITEM, app.items[0].name);
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(12, app.items[0].quality);
    }
    
    @Test
    void testBrie() {
    	Item[] items = new Item[] { new Item(BRIE, 1, 48) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(BRIE, app.items[0].name);
        assertEquals(0, app.items[0].sellIn);
        assertEquals(49, app.items[0].quality);
        app.updateQuality();
        assertEquals(BRIE, app.items[0].name);
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(50, app.items[0].quality);
        app.updateQuality();
        assertEquals(BRIE, app.items[0].name);
        assertEquals(-2, app.items[0].sellIn);
        assertEquals(50, app.items[0].quality);
    }
    
    @Test
    void testPasses() {
    	Item[] items = new Item[] { new Item(BACKSTAGE_PASSES, 15, 30) };
        GildedRose app = new GildedRose(items);
        
        avancaDias(app, 1);
        assertEquals(BACKSTAGE_PASSES, app.items[0].name);
        assertEquals(14, app.items[0].sellIn);
        assertEquals(31, app.items[0].quality);
        
        avancaDias(app, 4);
        assertEquals(BACKSTAGE_PASSES, app.items[0].name);
        assertEquals(10, app.items[0].sellIn);
        assertEquals(35, app.items[0].quality);
        
        avancaDias(app, 1);
        assertEquals(BACKSTAGE_PASSES, app.items[0].name);
        assertEquals(9, app.items[0].sellIn);
        assertEquals(37, app.items[0].quality);
        
        avancaDias(app, 4);
        assertEquals(BACKSTAGE_PASSES, app.items[0].name);
        assertEquals(5, app.items[0].sellIn);
        assertEquals(45, app.items[0].quality);
        
        avancaDias(app, 1);
        assertEquals(BACKSTAGE_PASSES, app.items[0].name);
        assertEquals(4, app.items[0].sellIn);
        assertEquals(48, app.items[0].quality);
        
        avancaDias(app, 1);
        assertEquals(BACKSTAGE_PASSES, app.items[0].name);
        assertEquals(3, app.items[0].sellIn);
        assertEquals(50, app.items[0].quality);
        
        avancaDias(app, 3);
        assertEquals(BACKSTAGE_PASSES, app.items[0].name);
        assertEquals(0, app.items[0].sellIn);
        assertEquals(50, app.items[0].quality);
        
        avancaDias(app, 1);
        assertEquals(BACKSTAGE_PASSES, app.items[0].name);
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);
        
        avancaDias(app, 1);
        assertEquals(BACKSTAGE_PASSES, app.items[0].name);
        assertEquals(-2, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);
    }

}
