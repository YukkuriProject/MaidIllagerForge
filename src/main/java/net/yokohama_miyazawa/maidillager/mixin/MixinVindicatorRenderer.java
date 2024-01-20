package net.yokohama_miyazawa.maidillager.mixin;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.VindicatorRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Vindicator;
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
@Mixin(VindicatorRenderer.class)
public class MixinVindicatorRenderer {
    private static final ResourceLocation VINDICATOR = new ResourceLocation(MaidIllager.MODID + ":textures/entity/maid_vindicator.png");

    @Inject(method = "getTextureLocation", at = @At("RETURN"), cancellable = true)
    public void onGetTextureLocation(Vindicator entity, CallbackInfoReturnable<ResourceLocation> cir){
        cir.setReturnValue((ResourceLocation) VINDICATOR);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void init(EntityRendererProvider.Context context, CallbackInfo cir) {
        ((VindicatorRenderer)(Object)this).addLayer(new IllagerFaceLayer<>(((VindicatorRenderer)(Object)this)));
    }
}
