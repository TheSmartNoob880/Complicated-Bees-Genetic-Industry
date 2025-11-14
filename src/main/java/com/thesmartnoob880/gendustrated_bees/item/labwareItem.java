package com.thesmartnoob880.gendustrated_bees.item;

import com.thesmartnoob880.gendustrated_bees.util.labwareHelper;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class labwareItem extends Item {
    public labwareItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return false;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level p_41422_, List<Component> pTooltip, TooltipFlag p_41424_) {
        super.appendHoverText(pStack, p_41422_, pTooltip, p_41424_);
        pTooltip.add(Component.translatable("item.gendustrated_bees.labware.labware_tooltip", labwareHelper.getLabwareRate(pStack.getItem())*100).append("%"));
    }
}
