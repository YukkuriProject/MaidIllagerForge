package net.yokohama_miyazawa.maidillager.mixin;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EvokerRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.SpellcasterIllager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.yokohama_miyazawa.maidillager.MaidIllager;
import net.yokohama_miyazawa.maidillager.layers.IllagerFaceLayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@OnlyIn(Dist.CLIENT)
@Mixin(EvokerRenderer.class)
public class MixinEvokerRenderer<T extends SpellcasterIllager> {
    private static final ResourceLocation EVOKER_ILLAGER = new ResourceLocation(MaidIllager.MODID + ":textures/entity/maid_evoker.png");

    @Inject(method = "getTextureLocation", at = @At("RETURN"), cancellable = true)
    public void onGetTextureLocation(T entity, CallbackInfoReturnable<ResourceLocation> cir){
        cir.setReturnValue((ResourceLocation) EVOKER_ILLAGER);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void init(EntityRendererProvider.Context context, CallbackInfo cir) {
        ((EvokerRenderer)(Object)this).addLayer(new IllagerFaceLayer<>(((EvokerRenderer)(Object)this)));
    }
}
