package net.yokohama_miyazawa.maidillager.mixin;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@OnlyIn(Dist.CLIENT)
@Mixin(SoundEvents.class)
public interface SoundEventsInvoker {
    @Invoker("register")
    public static SoundEvent invokeRegister(ResourceLocation id) {
        throw new AssertionError();
    }
}
