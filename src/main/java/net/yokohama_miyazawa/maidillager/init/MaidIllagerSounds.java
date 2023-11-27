package net.yokohama_miyazawa.maidillager.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.yokohama_miyazawa.maidillager.MaidIllager;

public class MaidIllagerSounds {
    public static final SoundEvent MAID_EVOKER_AMBIENT = createEvent("entity.evoker.ambient");
    public static final SoundEvent MAID_EVOKER_CELEBRATE = createEvent("entity.evoker.celebrate");
    public static final SoundEvent MAID_EVOKER_DEATH = createEvent("entity.evoker.death");
    public static final SoundEvent MAID_EVOKER_HURT = createEvent("entity.evoker.hurt");
    public static final SoundEvent MAID_ILLUSIONER_AMBIENT = createEvent("entity.illusioner.ambient");
    public static final SoundEvent MAID_ILLUSIONER_DEATH = createEvent("entity.illusioner.death");
    public static final SoundEvent MAID_ILLUSIONER_HURT = createEvent("entity.illusioner.hurt");
    public static final SoundEvent MAID_PILLAGER_AMBIENT = createEvent("entity.pillager.ambient");
    public static final SoundEvent MAID_PILLAGER_CELEBRATE = createEvent("entity.pillager.celebrate");
    public static final SoundEvent MAID_PILLAGER_DEATH = createEvent("entity.pillager.death");
    public static final SoundEvent MAID_PILLAGER_HURT = createEvent("entity.pillager.hurt");
    public static final SoundEvent MAID_VINDICATOR_AMBIENT = createEvent("entity.vindicator.ambient");
    public static final SoundEvent MAID_VINDICATOR_CELEBRATE = createEvent("entity.vindicator.celebrate");
    public static final SoundEvent MAID_VINDICATOR_DEATH = createEvent("entity.vindicator.death");
    public static final SoundEvent MAID_VINDICATOR_HURT = createEvent("entity.vindicator.hurt");

    private static SoundEvent createEvent(String sound) {
        ResourceLocation name = new ResourceLocation(MaidIllager.MODID, sound);
        return SoundEvent.createVariableRangeEvent(name);
    }
}
