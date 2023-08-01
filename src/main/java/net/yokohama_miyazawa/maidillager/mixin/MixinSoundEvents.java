package net.yokohama_miyazawa.maidillager.mixin;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.yokohama_miyazawa.maidillager.MaidIllager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@OnlyIn(Dist.CLIENT)
@Mixin(SoundEvents.class)
public class MixinSoundEvents {
    @Inject(method = "register(Ljava/lang/String;)Lnet/minecraft/sounds/SoundEvent;", at = @At("HEAD"), cancellable = true)
    private static void onRegister(String id, CallbackInfoReturnable<SoundEvent> cir) {
        if (isIllager(id) && isIllagerTalkEvent(id)) {
            MaidIllager.LOGGER.info(id);
            cir.setReturnValue(SoundEventsInvoker.invokeRegister(new ResourceLocation(makeId(MaidIllager.MODID, id))));
        }
    }

    private static String makeId(String modId, String sound) {
        return modId + ':' + sound;
    }

    private static boolean isIllager(String id) {
        return id.startsWith("entity.evoker") || id.startsWith("entity.illusioner") || id.startsWith("entity.pillager") || id.startsWith("entity.vindicator");
    }

    private static boolean isIllagerTalkEvent(String id) {
        return id.endsWith("ambient") || id.endsWith("celebrate") || id.endsWith("death") || id.endsWith("hurt");
    }
}
