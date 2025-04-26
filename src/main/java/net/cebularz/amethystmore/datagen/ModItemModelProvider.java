package net.cebularz.amethystmore.datagen;


import net.cebularz.amethystmore.AmethystMore;
import net.cebularz.amethystmore.block.ModBlocks;
import net.cebularz.amethystmore.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, AmethystMore.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.AMETHYST_APPLE);
        simpleItem(ModItems.AMETHYST_CARROT);

        wallItem(ModBlocks.AMETHYST_BRICKS_WALL, ModBlocks.AMETHYST_BRICKS);
        evenSimplerBlockItem(ModBlocks.AMETHYST_BRICKS_STAIRS);
        evenSimplerBlockItem(ModBlocks.AMETHYST_BRICKS_SLAB);

        wallItem(ModBlocks.POLISHED_AMETHYST_WALL, ModBlocks.POLISHED_AMETHYST);
        evenSimplerBlockItem(ModBlocks.AMETHYST_STAIRS);
        evenSimplerBlockItem(ModBlocks.AMETHYST_SLAB);

        wallItemVanilla(ModBlocks.AMETHYST_WALL, Blocks.AMETHYST_BLOCK);
        evenSimplerBlockItem(ModBlocks.POLISHED_AMETHYST_STAIRS);
        evenSimplerBlockItem(ModBlocks.POLISHED_AMETHYST_SLAB);

        simpleItem(ModItems.AMETHYST_ENDER_PEARL);
    }
    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(AmethystMore.MOD_ID,"item/" + item.getId().getPath()));
    }
    public void evenSimplerBlockItem(RegistryObject<Block> block) {
        this.withExistingParent(AmethystMore.MOD_ID + ":" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                modLoc("block/" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath()));
    }
    public void wallItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/wall_inventory"))
                .texture("wall",  new ResourceLocation(AmethystMore.MOD_ID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }
    public void wallItemVanilla(RegistryObject<Block> block, Block baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/wall_inventory"))
                .texture("wall",  new ResourceLocation("minecraft", "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock).getPath()));
    }
    private ItemModelBuilder simpleBlockItemBlockTexture(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(AmethystMore.MOD_ID,"block/" + item.getId().getPath()));
    }
}
