package com.movtery.betterf1.mixin.compat_iris;

import com.movtery.betterf1.BetterF1;
import com.movtery.betterf1.client.HUDState;
import net.coderbot.iris.pipeline.HandRenderer;
import net.minecraft.client.Options;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Pseudo
@Mixin(HandRenderer.class)
public class MixinHandRenderer {

    @Redirect(
            method = "canRender",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/client/Options;hideGui:Z"
            )
    )
    private boolean canRender(Options instance) {
        return instance.hideGui && BetterF1.state.equals(HUDState.ALL_HIDDEN);
    }
}
