package net.cebularz.newandmuddy.datagen;


import net.cebularz.newandmuddy.NewAndMuddy;
import net.cebularz.newandmuddy.block.ModBlocks;
import net.cebularz.newandmuddy.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, NewAndMuddy.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {

        simpleItem(ModItems.PACKED_MUD_BALL);
        simpleItem(ModItems.SINKING_MUD_BUCKET);
        wallItem(ModBlocks.DRIED_MUD_BRICKS_WALL, ModBlocks.DRIED_MUD_BRICKS);
        evenSimplerBlockItem(ModBlocks.DRIED_MUD_BRICKS_STAIRS);
        evenSimplerBlockItem(ModBlocks.DRIED_MUD_BRICKS_SLAB);

        wallItem(ModBlocks.POLISHED_PACKED_MUD_WALL, ModBlocks.POLISHED_PACKED_MUD);
        evenSimplerBlockItem(ModBlocks.POLISHED_PACKED_MUD_STAIRS);
        evenSimplerBlockItem(ModBlocks.POLISHED_PACKED_MUD_SLAB);

        wallItem(ModBlocks.POLISHED_DRIED_MUD_WALL, ModBlocks.POLISHED_DRIED_MUD);
        evenSimplerBlockItem(ModBlocks.POLISHED_DRIED_MUD_STAIRS);
        evenSimplerBlockItem(ModBlocks.POLISHED_DRIED_MUD_SLAB);

        //simpleBlockItemBlockTexture(ModBlocks.CINNAMON_FERN);

    }
    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(NewAndMuddy.MOD_ID,"item/" + item.getId().getPath()));
    }
    public void evenSimplerBlockItem(RegistryObject<Block> block) {
        this.withExistingParent(NewAndMuddy.MOD_ID + ":" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                modLoc("block/" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath()));
    }
    public void wallItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/wall_inventory"))
                .texture("wall",  new ResourceLocation(NewAndMuddy.MOD_ID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }
    private ItemModelBuilder simpleBlockItemBlockTexture(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(NewAndMuddy.MOD_ID,"block/" + item.getId().getPath()));
    }
}
