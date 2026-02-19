package com.p1nero.tcrcore.entity.custom.aine_iris;

import com.mojang.blaze3d.vertex.PoseStack;
import com.p1nero.tcrcore.TCRCoreMod;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

@OnlyIn(Dist.CLIENT)
public class AineIrisRenderer extends GeoEntityRenderer<AineEntity> {
    public AineIrisRenderer(EntityRendererProvider.Context context) {
        super(context, new DefaultedEntityGeoModel<>(ResourceLocation.fromNamespaceAndPath(TCRCoreMod.MOD_ID, "aine"), true));
    }

    @Override
    public void render(AineEntity entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        poseStack.pushPose();
        poseStack.scale(1.25F, 1.25F, 1.25F);
        poseStack.translate(0, 0.25F, 0);
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
        poseStack.popPose();
    }
}
