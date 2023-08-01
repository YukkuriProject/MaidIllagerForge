package net.yokohama_miyazawa.maidillager;

import com.mojang.logging.LogUtils;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import java.util.Iterator;


// The value here should match an entry in the META-INF/mods.toml file
@Mod(MaidIllager.MODID)
public class MaidIllager
{
    public static final String MODID = "maidillager";
    public static final Logger LOGGER = LogUtils.getLogger();

    public MaidIllager()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);

        LOGGER.info("MaidIllager");
        /*
        LOGGER.info("BuiltInRegistries");
        Iterator<ResourceLocation> iterator = BuiltInRegistries.SOUND_EVENT.keySet().iterator();
        while(iterator.hasNext()){
            LOGGER.info(iterator.next().toString());
        }
        LOGGER.info("BuiltInRegistries End");
        */
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {

    }
}
