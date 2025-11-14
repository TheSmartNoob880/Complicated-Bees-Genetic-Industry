package com.thesmartnoob880.gendustrated_bees.screen.slot;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import static com.thesmartnoob880.gendustrated_bees.item.geneSampleItem.KEY_GENE_NAME;
import static com.thesmartnoob880.gendustrated_bees.item.geneSampleItem.KEY_GENE_VALUE;

public class GeneSampleSlot extends SlotItemHandler {
    private final TagKey<Item> itemTagKey;

    public GeneSampleSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition, TagKey<Item> tagKey) {
        super(itemHandler, index, xPosition, yPosition);
        this.itemTagKey = tagKey;
    }

    public boolean mayPlace(ItemStack stack) {
        if (stack.is(this.itemTagKey)){
            if (!stack.hasTag()){
                return true;
            }
            assert stack.getTag() !=null;
            return !(stack.getTag().contains(KEY_GENE_NAME) && stack.getTag().contains(KEY_GENE_VALUE));
        } else {
            return false;
        }
    }
}
