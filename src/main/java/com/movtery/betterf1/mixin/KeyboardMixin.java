package com.movtery.betterf1.mixin;

import com.movtery.betterf1.BetterF1;
import com.movtery.betterf1.client.HUDState;
import net.minecraft.client.KeyboardHandler;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(KeyboardHandler.class)
public class KeyboardMixin {

    @Shadow @Final
    private Minecraft minecraft;

    @Inject(method = "keyPress(JIIII)V", at = @At(value = "FIELD",
            target = "Lnet/minecraft/client/Options;hideGui:Z"), cancellable = true)
    public void onF1Key(long window, int key, int scancode, int action, int modifiers, CallbackInfo ci) {
        BetterF1.state = BetterF1.state.next();

        // Seems most safe
        minecraft.options.hideGui = !BetterF1.state.equals(HUDState.ALL_VISIBLE);
        ci.cancel();
    }
}
