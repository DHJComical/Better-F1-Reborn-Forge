package com.movtery.betterf1.mixin;

import com.movtery.betterf1.BetterF1;
import com.movtery.betterf1.client.HUDState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class KeyboardMixin {

    @Shadow
    public GameSettings gameSettings;

    @Inject(method = "runTickKeyboard()V", at = @At(value = "FIELD",
            target = "Lnet/minecraft/client/settings/GameSettings;hideGUI:Z",
            opcode = Opcodes.PUTFIELD), cancellable = true)
    public void onF1Key(CallbackInfo ci) {
        BetterF1.state = BetterF1.state.next();

        // Seems most safe
        this.gameSettings.hideGUI = !BetterF1.state.equals(HUDState.ALL_VISIBLE);
        ci.cancel();
    }
}
