package net.yokohama_miyazawa.maidillager;

import com.mojang.logging.LogUtils;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.yokohama_miyazawa.maidillager.init.MaidIllagerSounds;
import org.slf4j.Logger;

import javax.annotation.Nullable;
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
    // 今はただ黙らせているだけ
    // TODO: 別の音声素材を充てる
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public void setEntitySounds(PlaySoundEvent event) {
        String[] soundNames = event.getName().split(Pattern.quote("."));
        String kind = soundNames[0];
        if(kind.equals("entity")){
            String mob = soundNames[1];
            if(mob.equals("vindicator") || mob.equals("pillager") || mob.equals("evoker") || mob.equals("illusioner")) {
                String action = soundNames[2];
                if(action.equals("ambient") || action.equals("hurt") || action.equals("death") || action.equals("celebrate")) {
                    event.setSound(SimpleSoundInstance.forLocalAmbience(getMaidSoundEvent(event, mob, action), 1.0f, 1.0f));
                }
            }
        }
    }

    private SoundEvent getMaidSoundEvent(PlaySoundEvent event, String mob, String action) {
        String mobAction = String.join(".", mob, action);
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
