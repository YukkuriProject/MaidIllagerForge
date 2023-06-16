package net.yokohama_miyazawa.maidillager.mixin;

import net.minecraft.client.renderer.entity.IllusionerRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Illusioner;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.yokohama_miyazawa.maidillager.MaidIllager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@OnlyIn(Dist.CLIENT)
@Mixin(IllusionerRenderer.class)
public class MixinIllusionerRenderer {
    private static final ResourceLocation ILLUSIONER = new ResourceLocation(MaidIllager.MODID + ":textures/entity/maid_illusioner.png");

    @Inject(method = "getTextureLocation", at = @At("RETURN"), cancellable = true)
    public void onGetTextureLocation(Illusioner entity, CallbackInfoReturnable<ResourceLocation> cir){
        cir.setReturnValue((ResourceLocation) ILLUSIONER);
    }
}
