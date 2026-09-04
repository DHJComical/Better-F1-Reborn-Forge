package com.movtery.betterf1.mixin;

import com.movtery.betterf1.BetterF1;
import com.movtery.betterf1.client.HUDState;
import net.minecraft.client.Options;
import net.minecraft.client.renderer.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = GameRenderer.class)
public class GameRendererMixin {

    @Redirect(
            method = "renderItemInHand(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/Camera;F)V",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/client/Options;hideGui:Z"
            )
    )
    private boolean onRenderHand(Options instance) {
        return instance.hideGui && BetterF1.state.equals(HUDState.ALL_HIDDEN);
    }
}
