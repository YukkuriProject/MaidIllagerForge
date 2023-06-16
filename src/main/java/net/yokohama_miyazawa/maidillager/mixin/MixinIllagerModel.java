package net.yokohama_miyazawa.maidillager.mixin;

import net.minecraft.client.model.IllagerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.entity.monster.Illusioner;
import net.minecraft.world.entity.monster.Pillager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@OnlyIn(Dist.CLIENT)
@Mixin(IllagerModel.class)
public class MixinIllagerModel {

    private ModelPart hire;
    private ModelPart sideburns;
    private ModelPart chignonB;
    private ModelPart tail;
    private ModelPart Skirt;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void init(ModelPart root, CallbackInfo cir) {
        ModelPart head = ((IllagerModel)(Object)this).getHead();
        this.hire = head.getChild("hire");
        this.sideburns = head.getChild("sideburns");
        this.chignonB = head.getChild("chignonB");
        this.tail = head.getChild("tail");
        this.Skirt = root.getChild("Skirt");

        this.hire.visible = false;
        this.sideburns.visible = false;
        this.chignonB.visible = false;
        this.tail.visible = false;

        ModelPart hat = ((IllagerModel)(Object)this).getHat();
        hat.visible = false;

        ModelPart arms = ((IllagerModelAccessor) (Object)this).getArms();
        arms.visible = false;
    }

    @Inject(method = "createBodyLayer", at = @At("HEAD"), cancellable = true)
    private static void onCreateBodyLayer(CallbackInfoReturnable<LayerDefinition> cir) {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        // オリジナルとの身長差分、体全体を下にずらす
        float heightOffset = 8.0F;

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F+heightOffset, 0.0F));
        head.addOrReplaceChild("hire", CubeListBuilder.create().texOffs(24, 0).addBox(-4.0F, 0.0F, 1.0F, 8.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        head.addOrReplaceChild("sideburns", CubeListBuilder.create().texOffs(24, 0).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        head.addOrReplaceChild("chignonB", CubeListBuilder.create().texOffs(52, 10).addBox(-2.0F, -7.2F, 4.0F, 4.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        head.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(46, 20).addBox(-1.5F, -7.8F, 4.0F, 3.0F, 9.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(32, 8).addBox(-3.0F, 0.0F, -2.0F, 6.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F+heightOffset, 0.0F));
        PartDefinition rightArm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(48, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 1.0F+heightOffset, 0.0F));
        PartDefinition leftArm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(56, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, 1.0F+heightOffset, 0.0F));
        PartDefinition rightLeg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(32, 19).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, 7.0F+heightOffset, 0.0F));
        PartDefinition leftLeg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(32, 19).mirror().addBox(-1.5F, 0.0F, -2.0F, 3.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(1.5F, 7.0F+heightOffset, 0.0F));
        PartDefinition Skirt = partdefinition.addOrReplaceChild("Skirt", CubeListBuilder.create().texOffs(0, 16).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 5.0F+heightOffset, 0.0F));

        // ダミーの帽子と腕
        head.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        partdefinition.addOrReplaceChild("arms", CubeListBuilder.create().texOffs(32, 8).addBox(-3.0F, 0.0F, -2.0F, 6.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F+heightOffset, 0.0F));

        cir.setReturnValue(LayerDefinition.create(meshdefinition, 64, 32));
    }

    @Inject(method = "setupAnim", at = @At("HEAD"), cancellable = true)
    private <T extends AbstractIllager> void onSetupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo cir) {
        if (entity instanceof Illusioner) {
            this.Skirt.visible = false;
            ModelPart hat = ((IllagerModel)(Object)this).getHat();
            hat.visible = false;
        }
        if (entity instanceof Pillager || entity instanceof Illusioner) {
            this.chignonB.visible = true;
            this.tail.visible = true;
        } else {
            this.sideburns.visible = true;
            this.hire.visible = true;
        }

        ModelPart head = ((IllagerModel)(Object)this).getHead();
        head.yRot = netHeadYaw * ((float)Math.PI / 180F);
        head.xRot = headPitch * ((float)Math.PI / 180F);

        ModelPart rightArm = ((IllagerModelAccessor) (Object)this).getRightArm();
        ModelPart leftArm  = ((IllagerModelAccessor) (Object)this).getLeftArm();
        ModelPart rightLeg = ((IllagerModelAccessor) (Object)this).getRightLeg();
        ModelPart leftLeg  = ((IllagerModelAccessor) (Object)this).getLeftLeg();
        // TODO: ラヴェジャーに乗っている時に下半身がめり込むのを解消する
        if (((IllagerModel)(Object)this).riding) {  // ラヴェジャーに乗っている時
            this.setAngle(rightArm, -0.6283185F, 0.0F, 0.0F);
            this.setAngle(leftArm, -0.6283185F, 0.0F, 0.0F);
            this.setAngle(rightLeg, -1.256637F, 0.3141593F, 0.0F);
            this.setAngle(leftLeg, -1.256637F, -0.3141593F, 0.0F);
        } else {
            this.setAngle(rightArm, (float)Math.cos(limbSwing * 0.6662 + Math.PI) * 2.0F * limbSwingAmount * 0.5F, 0.0F, (float)Math.PI / 5.0F);
            this.setAngle(leftArm, (float)Math.cos(limbSwing * 0.6662) * 2.0F * limbSwingAmount * 0.5F, 0.0F, -(float)Math.PI / 5.0F);
            this.setAngle(rightLeg, (float)Math.cos(limbSwing * 0.6662) * 1.4F * limbSwingAmount * 0.5F, 0.0F, 0.0F);
            this.setAngle(leftLeg, (float)Math.cos(limbSwing * 0.6662 + Math.PI) * 1.4F * limbSwingAmount * 0.5F, 0.0F, 0.0F);
        }

        AbstractIllager.IllagerArmPose armpose = entity.getArmPose();
        switch(armpose){
            case SPELLCASTING -> {
                this.setAngle(rightArm, 0.0F, 0.0F, (float)Math.PI * (2.0F / 3.0F));
                this.setAngle(leftArm, 0.0F, 0.0F, -(float)Math.PI * (2.0F / 3.0F));
            }
            case BOW_AND_ARROW, CROSSBOW_HOLD, CROSSBOW_CHARGE -> {
                this.setAngle(rightArm, -(float)Math.PI / 2.0F + head.xRot, -(float)Math.PI / 15.0F, 0.0F);
                this.setAngle(leftArm, -(float)Math.PI / 2.0F + head.xRot, (float)Math.PI / 15.0F, 0.0F);
            }
            case CELEBRATING -> {
                this.setAngle(rightArm, 0.0F, 0.0F, (float)Math.PI * (5.0F / 6.0F));
                this.setAngle(leftArm, 0.0F, 0.0F, -(float)Math.PI * (5.0F / 6.0F));
            }
        }

        cir.cancel();
    }

    private void setAngle(ModelPart part, float xAngle, float yAngle, float zAngle) {
        part.xRot = xAngle;
        part.yRot = yAngle;
        part.zRot = zAngle;
    }
}
