package net.cebularz.amethystmore.item;

import net.cebularz.amethystmore.AmethystMore;
import net.cebularz.amethystmore.item.custom.AmethystArrowItem;
import net.cebularz.amethystmore.item.custom.AmethystEnderPearlItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, AmethystMore.MOD_ID);

    public static final RegistryObject<Item> AMETHYST_APPLE = ITEMS.register("amethyst_apple",
            () -> new Item(new Item.Properties().food(ModFoods.AMETHYST_APPLE)));
    public static final RegistryObject<Item> AMETHYST_CARROT = ITEMS.register("amethyst_carrot",
            () -> new Item(new Item.Properties().food(ModFoods.AMETHYST_CARROT)));
    public static final RegistryObject<Item> AMETHYST_ARROW = ITEMS.register("amethyst_arrow",
            () -> new AmethystArrowItem(new Item.Properties()));
    public static final RegistryObject<Item> AMETHYST_ENDER_PEARL = ITEMS.register("amethyst_ender_pearl",
            () -> new AmethystEnderPearlItem(new Item.Properties().stacksTo(16)));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
