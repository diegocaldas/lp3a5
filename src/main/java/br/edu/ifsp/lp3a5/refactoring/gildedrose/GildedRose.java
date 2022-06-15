package br.edu.ifsp.lp3a5.refactoring.gildedrose;

import java.util.Optional;

class ItemUtil {
	final private static int QUALITY_MAX = 50;
	final private static int QUALITY_MIN = 0;
	
	protected static boolean isNotQualityMax(Item item) {
		return item.quality < QUALITY_MAX;
	}
	
	protected static boolean isNotQualityMin(Item item) {
		return item.quality > QUALITY_MIN;    		
	}
	
	protected static boolean hasType(Item item, ItemType argType) {
		ItemType typeFromName = ItemType.getTypeByName(item.name);
		return typeFromName.equals(argType);
	}
}

enum ItemType {
	AGED_BRIE("Aged Brie"),
	BACKSTAGE_PASSES("Backstage passes to a TAFKAL80ETC concert"),
	SULFURAS("Sulfuras, Hand of Ragnaros"),
	OTHERS(null);
	
	final private String name;
	
	ItemType(String name) {
		this.name = name;
	}
	
	public Optional<String> getName() {
		return Optional.ofNullable(name);
	}
	
	private boolean isNameEquals(String otherName) {
		final String thisName = getName().orElse("#"+otherName);
		return thisName.equals(otherName);
	}
	
	public static ItemType getTypeByName(String name) {
		ItemType retorno = null;
		for(  ItemType type : ItemType.values() ) {
			if ( type.isNameEquals(name) ) {
				retorno = type;
			}
		}
		return retorno == null? ItemType.OTHERS : retorno;
	}
}

class GildedRose {
	
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }
    


    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            updateQualityByItem(items[i]);
        }
    }



	/**
	 * @param item
	 */
	private void updateQualityByItem(final Item item) {
		if (! ItemUtil.hasType(item, ItemType.AGED_BRIE)
		        && !ItemUtil.hasType(item, ItemType.BACKSTAGE_PASSES) ) {
		    if ( ItemUtil.isNotQualityMin(item) ) {
		        if (!ItemUtil.hasType(item, ItemType.SULFURAS)) {
		            item.quality = item.quality - 1;
		        }
		    }
		} else {
		    if ( ItemUtil.isNotQualityMax(item) ) {
		        item.quality = item.quality + 1;

		        if (ItemUtil.hasType(item, ItemType.BACKSTAGE_PASSES)) {
		            if (item.sellIn < 11) {
		                if ( ItemUtil.isNotQualityMax(item) ) {
		                    item.quality = item.quality + 1;
		                }
		            }

		            if (item.sellIn < 6) {
		                if ( ItemUtil.isNotQualityMax(item) ) {
		                    item.quality = item.quality + 1;
		                }
		            }
		        }
		    }
		}

		if (!ItemUtil.hasType(item, ItemType.SULFURAS)) {
		    item.sellIn = item.sellIn - 1;
		}

		if (item.sellIn < 0) {
		    if (!ItemUtil.hasType(item, ItemType.AGED_BRIE)) {
		        if (!ItemUtil.hasType(item, ItemType.BACKSTAGE_PASSES)) {
		            if ( ItemUtil.isNotQualityMin(item) ) {
		                if (!ItemUtil.hasType(item, ItemType.SULFURAS)) {
		                    item.quality = item.quality - 1;
		                }
		            }
		        } else {
		            item.quality = item.quality - item.quality;
		        }
		    } else {
		        if ( ItemUtil.isNotQualityMax(item) ) {
		            item.quality = item.quality + 1;
		        }
		    }
		}
	}
}