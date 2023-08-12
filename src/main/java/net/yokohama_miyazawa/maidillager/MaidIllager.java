package net.yokohama_miyazawa.maidillager;

import com.mojang.logging.LogUtils;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.PlayLevelSoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.yokohama_miyazawa.maidillager.init.MaidIllagerSounds;
import org.slf4j.Logger;

import java.util.Objects;
import java.util.regex.Pattern;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MaidIllager.MODID)
public class MaidIllager
{
    public static final String MODID = "maidillager";
    private static final Logger LOGGER = LogUtils.getLogger();

    public MaidIllager()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);

        MaidIllagerSounds.SOUND_EVENTS.register(modEventBus);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {

    }

    // 邪悪な村人の声を変える
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public void setEntitySounds(PlayLevelSoundEvent event) {
        if (event.getSource() == SoundSource.HOSTILE) {
            SoundEvent sound = Objects.requireNonNull(
                    event.getSound(),
                    "Original sound must not be null.");
            String soundId = sound.getLocation().toString().split(":")[1];
            if (isIllager(soundId) && isIllagerTalkEvent(soundId)) {
                event.setNewPitch(1.0f);
                event.setSound(getMaidSoundEvent(soundId));
            }
        }
    }

    private boolean isIllager(String id) {
        return id.startsWith("entity.evoker") || id.startsWith("entity.illusioner") || id.startsWith("entity.pillager") || id.startsWith("entity.vindicator");
    }

    private boolean isIllagerTalkEvent(String id) {
        return id.endsWith("ambient") || id.endsWith("celebrate") || id.endsWith("death") || id.endsWith("hurt");
    }

    private SoundEvent getMaidSoundEvent(String soundId) {
        String[] ids = soundId.split(Pattern.quote("."));
        String mobAction = String.join(".", ids[1], ids[2]);
        switch(mobAction){
            case "evoker.ambient"       -> { return MaidIllagerSounds.MAID_EVOKER_AMBIENT.get(); }
            case "evoker.celebrate"     -> { return MaidIllagerSounds.MAID_EVOKER_CELEBRATE.get(); }
            case "evoker.death"         -> { return MaidIllagerSounds.MAID_EVOKER_DEATH.get(); }
            case "evoker.hurt"          -> { return MaidIllagerSounds.MAID_EVOKER_HURT.get(); }
            case "illusioner.ambient"   -> { return MaidIllagerSounds.MAID_ILLUSIONER_AMBIENT.get(); }
            case "illusioner.death"     -> { return MaidIllagerSounds.MAID_ILLUSIONER_DEATH.get(); }
            case "illusioner.hurt"      -> { return MaidIllagerSounds.MAID_ILLUSIONER_HURT.get(); }
            case "pillager.ambient"     -> { return MaidIllagerSounds.MAID_PILLAGER_AMBIENT.get(); }
            case "pillager.celebrate"   -> { return MaidIllagerSounds.MAID_PILLAGER_CELEBRATE.get(); }
            case "pillager.death"       -> { return MaidIllagerSounds.MAID_PILLAGER_DEATH.get(); }
            case "pillager.hurt"        -> { return MaidIllagerSounds.MAID_PILLAGER_HURT.get(); }
            case "vindicator.ambient"   -> { return MaidIllagerSounds.MAID_VINDICATOR_AMBIENT.get(); }
            case "vindicator.celebrate" -> { return MaidIllagerSounds.MAID_VINDICATOR_CELEBRATE.get(); }
            case "vindicator.death"     -> { return MaidIllagerSounds.MAID_VINDICATOR_DEATH.get(); }
            case "vindicator.hurt"      -> { return MaidIllagerSounds.MAID_VINDICATOR_HURT.get(); }
            default -> { throw new IllegalArgumentException(); }
        }
    }
}
