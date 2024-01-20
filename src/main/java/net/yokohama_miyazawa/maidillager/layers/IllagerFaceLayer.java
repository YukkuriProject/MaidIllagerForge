package net.yokohama_miyazawa.maidillager.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.entity.monster.Evoker;
import net.minecraft.world.entity.monster.Pillager;
import net.minecraft.world.entity.monster.Vindicator;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.yokohama_miyazawa.maidillager.MaidIllager;

@OnlyIn(Dist.CLIENT)
public class IllagerFaceLayer<T extends AbstractIllager, M extends EntityModel<T>> extends RenderLayer<T, M> {
    private final ResourceLocation[] resourceLocationsForEvokerAndVindicator = {
            new ResourceLocation(MaidIllager.MODID + ":textures/entity/face/00.png"),
            new ResourceLocation(MaidIllager.MODID + ":textures/entity/face/01.png"),
            new ResourceLocation(MaidIllager.MODID + ":textures/entity/face/02.png"),
            new ResourceLocation(MaidIllager.MODID + ":textures/entity/face/03.png"),
            new ResourceLocation(MaidIllager.MODID + ":textures/entity/face/04.png"),
            new ResourceLocation(MaidIllager.MODID + ":textures/entity/face/05.png"),
            new ResourceLocation(MaidIllager.MODID + ":textures/entity/face/06.png"),
            new ResourceLocation(MaidIllager.MODID + ":textures/entity/face/07.png"),
            new ResourceLocation(MaidIllager.MODID + ":textures/entity/face/08.png")
    };
    private final ResourceLocation[] resourceLocationsForPillager = {
            new ResourceLocation(MaidIllager.MODID + ":textures/entity/face/20.png"),
            new ResourceLocation(MaidIllager.MODID + ":textures/entity/face/21.png"),
            new ResourceLocation(MaidIllager.MODID + ":textures/entity/face/22.png"),
            new ResourceLocation(MaidIllager.MODID + ":textures/entity/face/23.png"),
            new ResourceLocation(MaidIllager.MODID + ":textures/entity/face/24.png"),
            new ResourceLocation(MaidIllager.MODID + ":textures/entity/face/25.png"),
            new ResourceLocation(MaidIllager.MODID + ":textures/entity/face/26.png"),
            new ResourceLocation(MaidIllager.MODID + ":textures/entity/face/27.png"),
            new ResourceLocation(MaidIllager.MODID + ":textures/entity/face/28.png")
    };

    public IllagerFaceLayer(RenderLayerParent<T, M> parent){
        super(parent);
    }

    public void render(PoseStack poseStack, MultiBufferSource source, int light, T entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        if (entity.isInvisible()) return;
        M parentModel = this.getParentModel();
        if (entity instanceof Evoker || entity instanceof Vindicator) {
            ResourceLocation resourceLocation = resourceLocationsForEvokerAndVindicator[entity.getId() % resourceLocationsForEvokerAndVindicator.length];
            renderColoredCutoutModel(parentModel, resourceLocation, poseStack, source, light, entity, 1.0F, 1.0F, 1.0F);
        } else if (entity instanceof Pillager) {
            ResourceLocation resourceLocation = resourceLocationsForPillager[entity.getId() % resourceLocationsForPillager.length];
            renderColoredCutoutModel(parentModel, resourceLocation, poseStack, source, light, entity, 1.0F, 1.0F, 1.0F);
        }
    }
}
