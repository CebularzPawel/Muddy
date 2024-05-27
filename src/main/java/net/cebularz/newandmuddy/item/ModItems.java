package net.cebularz.newandmuddy.item;

import net.cebularz.newandmuddy.NewAndMuddy;
import net.cebularz.newandmuddy.block.ModBlocks;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SolidBucketItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, NewAndMuddy.MOD_ID);


    public static final RegistryObject<MudBall> MUD_BALL = ITEMS.register("mud_ball",
            () -> new MudBall(new Item.Properties()));
    public static final RegistryObject<Item> PACKED_MUD_BALL = ITEMS.register("packed_mud_ball",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SINKING_MUD_BUCKET = ITEMS.register("sinking_mud_bucket",
            () -> new SolidBucketItem(ModBlocks.SINKING_MUD.get(),SoundEvents.BUCKET_EMPTY_POWDER_SNOW, (new Item.Properties()).stacksTo(1)));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
