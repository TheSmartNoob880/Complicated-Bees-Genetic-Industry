package com.thesmartnoob880.gendustrated_bees.item;

import com.accbdd.complicated_bees.ComplicatedBees;
import com.accbdd.complicated_bees.bees.gene.GeneTolerant;
import com.accbdd.complicated_bees.bees.gene.IGene;
import com.thesmartnoob880.gendustrated_bees.util.geneColors;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.antlr.v4.runtime.misc.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class geneSampleItem extends Item implements IGeneticHolder{
    public geneSampleItem(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level p_41422_, @NotNull List<Component> ptooltipComponents, TooltipFlag p_41424_) {

        if (stack.getTag() != null && stack.getTag().contains(KEY_GENE_NAME) && stack.getTag().contains(KEY_GENE_VALUE)){
            String transKey = "gene."+stack.getTag().getString(KEY_GENE_NAME).replace(":", ".");
            IGene<?> defaultGene = ComplicatedBees.GENE_REGISTRY.get().getValue(new ResourceLocation(stack.getTag().getString(KEY_GENE_NAME)));
            IGene<?> gene = defaultGene.deserialize(stack.getTag().getCompound(KEY_GENE_VALUE));
            MutableComponent value = gene.getTranslationKey();
            if (gene instanceof GeneTolerant<?> tolerantGene)
                value = value.append(" / ").append(tolerantGene.getTolerance().getTranslationKey());
            ptooltipComponents.add(Component.translatable(transKey+"_label").append(": ").append(value).withStyle(Style.EMPTY.withColor(geneColors.GENE_COLORS.get(defaultGene))));

            ptooltipComponents.add(Component.translatable("item.gendustrated_bees.gene_sample.dominance."+ gene.isDominant()).withStyle(Style.EMPTY.withColor(gene.isDominant() ? 15086117 : 2457574)));
        }
    }
    public static final String KEY_GENE_NAME = "geneKey";
    public static final String KEY_GENE_VALUE = "geneValue";

    public static boolean hasGeneData(ItemStack stack){
        return stack.getTag() != null && stack.getTag().contains(KEY_GENE_NAME) && stack.getTag().contains(KEY_GENE_VALUE);
    }

    @Override
    public Component getName(ItemStack stack) {
        if (stack.hasTag()){
            if (stack.getTag().contains(KEY_GENE_NAME) && stack.getTag().contains(KEY_GENE_VALUE)){
                return Component.translatable("item.gendustrated_bees.gene_sample");
            } else {
                return Component.translatable("item.gendustrated_bees.gene_sample.blank");
            }
        }else{
            return Component.translatable("item.gendustrated_bees.gene_sample.blank");
        }

    }

    public static ItemStack createGene(ResourceLocation location, IGene<?> igene){
        CompoundTag root = new CompoundTag();

        root.putString(KEY_GENE_NAME, location.toString());
        root.put(KEY_GENE_VALUE, igene.serialize());
        ItemStack result = new ItemStack(modItems.GENE_SAMPLE.get(), 1, root);
        result.setTag(root);
        return result;
    }

    @Override
    public boolean hasGeneticData(ItemStack stack) {
        return hasGeneData(stack);
    }
}
