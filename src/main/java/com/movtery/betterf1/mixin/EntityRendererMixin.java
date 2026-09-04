package com.movtery.betterf1.mixin;

import com.movtery.betterf1.BetterF1;
import com.movtery.betterf1.client.HUDState;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.settings.GameSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = EntityRenderer.class)
public class EntityRendererMixin {

    @Redirect(
            method = "renderHand(FI)V",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/client/settings/GameSettings;hideGUI:Z"
            )
    )
    private boolean onRenderHand(GameSettings instance) {
        return instance.hideGUI && BetterF1.state.equals(HUDState.ALL_HIDDEN);
    }
}
