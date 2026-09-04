package com.movtery.betterf1.mixin.compat_iris;

import com.movtery.betterf1.BetterF1;
import com.movtery.betterf1.client.HUDState;
import net.irisshaders.iris.pathways.HandRenderer;
import net.minecraft.client.Options;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Pseudo
@Mixin(HandRenderer.class)
public class MixinHandRenderer {

    // remap = false only stops "canRender" (an Oculus-owned method) from being
    // remapped; the @At field target still remaps hideGui to srg in production
    @Redirect(
            method = "canRender",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/client/Options;hideGui:Z"
            ),
            remap = false
    )
    private boolean canRender(Options instance) {
        return instance.hideGui && BetterF1.state.equals(HUDState.ALL_HIDDEN);
    }
}
