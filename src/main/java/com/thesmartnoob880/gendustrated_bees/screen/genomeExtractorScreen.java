package com.thesmartnoob880.gendustrated_bees.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.thesmartnoob880.gendustrated_bees.GendustratedBees;
import com.thesmartnoob880.gendustrated_bees.util.enums.EnumErrorCodesExtractor;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class genomeExtractorScreen extends AbstractContainerScreen<genomeExtractorMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(GendustratedBees.MODID, "textures/gui/genome_extractor_gui.png");
    public genomeExtractorScreen(genomeExtractorMenu pMenu, Inventory pInventory, Component pComponent) {
        super(pMenu, pInventory, pComponent);
        this.imageHeight= 187;
        this.inventoryLabelY = this.imageHeight-10000;
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;

        guiGraphics.blit(TEXTURE, x, y, 0, 0, this.imageWidth, this.imageHeight);

        renderProgressArrow(guiGraphics, x, y);
        renderEnergyBar(guiGraphics, x, y);
    }

    private void renderProgressArrow(GuiGraphics guiGraphics, int x, int y) {
        if (menu.isCrafting()){
            guiGraphics.blit(TEXTURE, x + 66, y +35, 176, 0, 16, menu.getScaledProgress());
        } else if (this.menu.getExtractorData().get(5) > 0){
            guiGraphics.blit(TEXTURE, x+66, y+65, 197, 0, 16, 16);
        }
    }
    private void renderEnergyBar(GuiGraphics guiGraphics, int x, int y){
        int energyScaled = this.menu.getEnergyScaled();
        guiGraphics.blit(TEXTURE,x+163,y+16+(75- energyScaled), 192, 75- energyScaled, 5, energyScaled);
    }

    @Override
    public void render(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, delta);
        this.renderPowerTooltip(guiGraphics, mouseX, mouseY);
        this.renderWarnings(guiGraphics, mouseX, mouseY);
    }

    protected void renderPowerTooltip(GuiGraphics guiGraphics, int pX, int pY){
        super.renderTooltip(guiGraphics, pX, pY);
        if (pX >= this.leftPos+163 && pX < this.leftPos +163 + 5 && pY >= this.topPos + 16 && pY < this.topPos + 16 + 75){
            int power = this.menu.getEnergy();
            int RFCost = this.menu.getEnergyPerTick();
            guiGraphics.renderComponentTooltip(this.font, List.of(Component.literal(power + " RF"), Component.literal(RFCost+ " RF/t").withStyle(ChatFormatting.GRAY)),pX, pY);
        }
    }

    protected void renderWarnings(GuiGraphics guiGraphics, int pX, int pY) {
        super.renderTooltip(guiGraphics, pX, pY);
        if (!menu.isCrafting() && pX >= this.leftPos + 66 && pX < this.leftPos + 66 + 16 && pY >= this.topPos + 65 && pY < this.topPos + 65 + 16) {
            int errorFlags = this.menu.getExtractorData().get(5);
            if (errorFlags > 0) {
                List<Component> errors = new ArrayList<>();

                for (EnumErrorCodesExtractor errorcode : EnumErrorCodesExtractor.values()) {
                    if ((errorFlags & errorcode.value) != 0) {
                        errors.add(errorcode.getExtractorMessage());
                    }
                }

                guiGraphics.renderTooltip(this.font, errors, Optional.empty(), pX, pY);
            }
        }
    }
}
