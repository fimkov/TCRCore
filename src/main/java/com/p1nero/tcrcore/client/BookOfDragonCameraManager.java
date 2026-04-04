package com.p1nero.tcrcore.client;

import net.magister.bookofdragons.entity.base.dragon.DragonFlyingRideableBase;
import net.minecraft.client.Minecraft;
import yesman.epicfight.api.client.event.types.ActivateTPSCamera;

public class BookOfDragonCameraManager {

    /**
     * 摆烂，取消就好了
     */
    public static void onEpicFightCameraSetup(ActivateTPSCamera event) {
        if(Minecraft.getInstance().player != null && Minecraft.getInstance().player.getVehicle() instanceof DragonFlyingRideableBase) {
            event.cancel();
        }

    }

}
