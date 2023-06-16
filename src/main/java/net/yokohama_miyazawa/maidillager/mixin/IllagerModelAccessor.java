package net.yokohama_miyazawa.maidillager.mixin;

import net.minecraft.client.model.IllagerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@OnlyIn(Dist.CLIENT)
@Mixin(IllagerModel.class)
public interface IllagerModelAccessor {
    @Accessor("arms")
    public ModelPart getArms();

    @Accessor("rightLeg")
    public ModelPart getRightLeg();

    @Accessor("leftLeg")
    public ModelPart getLeftLeg();

    @Accessor("rightArm")
    public ModelPart getRightArm();

    @Accessor("leftArm")
    public ModelPart getLeftArm();
}
