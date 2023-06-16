package net.yokohama_miyazawa.maidillager;

import com.mojang.logging.LogUtils;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

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
                    event.setSound(null);
                }
            }
        }
    }
}
