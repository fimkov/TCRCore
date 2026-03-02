package com.p1nero.tcrcore.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.createmod.ponder.api.level.PonderLevel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.client.event.RenderLivingEvent;
import org.spongepowered.asm.mixin.Mixin;
import yesman.epicfight.client.events.engine.RenderEngine;
import yesman.epicfight.config.ClientConfig;

@Mixin(RenderEngine.Events.class)
public class RenderEngineEventsMixin {

    @WrapMethod(method = "renderLivingEvent", remap = false)
    private static void tcr$wrapRenderLivingEvent(RenderLivingEvent.Pre<? extends LivingEntity, ? extends EntityModel<? extends LivingEntity>> event, Operation<Void> original) {
        LivingEntity livingentity = event.getEntity();
        if (livingentity.level() instanceof PonderLevel) {
            boolean computeShaderSetting = ClientConfig.activateComputeShader;
            ClientConfig.activateComputeShader = false;
            original.call(event);
            ClientConfig.activateComputeShader = computeShaderSetting;
        } else {
            original.call(event);
        }
    }

}
