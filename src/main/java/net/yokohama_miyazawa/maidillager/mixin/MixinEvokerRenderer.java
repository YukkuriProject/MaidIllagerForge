package net.yokohama_miyazawa.maidillager.mixin;

import net.minecraft.client.renderer.entity.EvokerRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.SpellcasterIllager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.yokohama_miyazawa.maidillager.MaidIllager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@OnlyIn(Dist.CLIENT)
@Mixin(EvokerRenderer.class)
public class MixinEvokerRenderer<T extends SpellcasterIllager> {
    private static final ResourceLocation EVOKER_ILLAGER = new ResourceLocation(MaidIllager.MODID + ":textures/entity/maid_evoker.png");

    @Inject(method = "getTextureLocation", at = @At("RETURN"), cancellable = true)
    public void onGetTextureLocation(T entity, CallbackInfoReturnable<ResourceLocation> cir){
        cir.setReturnValue((ResourceLocation) EVOKER_ILLAGER);
    }
}
