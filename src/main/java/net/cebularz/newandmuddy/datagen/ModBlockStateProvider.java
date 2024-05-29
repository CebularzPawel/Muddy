package net.cebularz.newandmuddy.datagen;


import net.cebularz.newandmuddy.NewAndMuddy;
import net.cebularz.newandmuddy.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, NewAndMuddy.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {


        axisBlock(((RotatedPillarBlock) ModBlocks.MUD_PILLAR.get()), blockTexture(ModBlocks.MUD_PILLAR.get()),
                new ResourceLocation(NewAndMuddy.MOD_ID, "block/mud_pillar_end"));
        blockItem(ModBlocks.MUD_PILLAR);
        blockWithItem(ModBlocks.CHISELED_MUD_BRICKS);

        blockWithItem(ModBlocks.POLISHED_PACKED_MUD);
        blockWithItem(ModBlocks.DRIED_MUD);
        blockWithItem(ModBlocks.DRIED_MUD_BRICKS);
        blockWithItem(ModBlocks.POLISHED_DRIED_MUD);
        blockWithItem(ModBlocks.CHISELED_DRIED_MUD_BRICKS);
        axisBlock(((RotatedPillarBlock) ModBlocks.DRIED_MUD_PILLAR.get()), blockTexture(ModBlocks.DRIED_MUD_PILLAR.get()),
                new ResourceLocation(NewAndMuddy.MOD_ID, "block/dried_mud_pillar_end"));
        blockItem(ModBlocks.DRIED_MUD_PILLAR);

        stairsBlock(((StairBlock) ModBlocks.DRIED_MUD_BRICKS_STAIRS.get()),blockTexture(ModBlocks.DRIED_MUD_BRICKS.get()));
        slabBlock(((SlabBlock) ModBlocks.DRIED_MUD_BRICKS_SLAB.get()),blockTexture(ModBlocks.DRIED_MUD_BRICKS.get()),blockTexture(ModBlocks.DRIED_MUD_BRICKS.get()));
        wallBlock(((WallBlock) ModBlocks.DRIED_MUD_BRICKS_WALL.get()),blockTexture(ModBlocks.DRIED_MUD_BRICKS.get()));

        stairsBlock(((StairBlock) ModBlocks.POLISHED_PACKED_MUD_STAIRS.get()),blockTexture(ModBlocks.POLISHED_PACKED_MUD.get()));
        slabBlock(((SlabBlock) ModBlocks.POLISHED_PACKED_MUD_SLAB.get()),blockTexture(ModBlocks.POLISHED_PACKED_MUD.get()),blockTexture(ModBlocks.POLISHED_PACKED_MUD.get()));
        wallBlock(((WallBlock) ModBlocks.POLISHED_PACKED_MUD_WALL.get()),blockTexture(ModBlocks.POLISHED_PACKED_MUD.get()));

        stairsBlock(((StairBlock) ModBlocks.POLISHED_DRIED_MUD_STAIRS.get()),blockTexture(ModBlocks.POLISHED_DRIED_MUD.get()));
        slabBlock(((SlabBlock) ModBlocks.POLISHED_DRIED_MUD_SLAB.get()),blockTexture(ModBlocks.POLISHED_DRIED_MUD.get()),blockTexture(ModBlocks.POLISHED_DRIED_MUD.get()));
        wallBlock(((WallBlock) ModBlocks.POLISHED_DRIED_MUD_WALL.get()),blockTexture(ModBlocks.POLISHED_DRIED_MUD.get()));
        //simpleBlockWithItem(ModBlocks.CINNAMON_FERN.get(), models().cross(blockTexture(ModBlocks.CINNAMON_FERN.get()).getPath(),
                //blockTexture(ModBlocks.CINNAMON_FERN.get())).renderType("cutout"));
        //simpleBlockWithItem(ModBlocks.POTTED_CINNAMON_FERN.get(), models().singleTexture("potted_catmint", new ResourceLocation("flower_pot_cross"), "plant",
               // blockTexture(ModBlocks.CINNAMON_FERN.get())).renderType("cutout"));


    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));

    }
    private void Top_Bottom_Side_Block(RegistryObject<Block> blockRegistryObject, String sideTexture, String bottomTexture, String topTexture) {
        ResourceLocation side = modLoc(sideTexture);
        ResourceLocation bottom = modLoc(bottomTexture);
        ResourceLocation top = modLoc(topTexture);
        simpleBlockWithItem(blockRegistryObject.get(), models().cubeBottomTop(blockRegistryObject.getId().getPath(), side, bottom, top));

    }
    private void blockItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile(NewAndMuddy.MOD_ID +
                ":block/" + ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath()));
    }
    private void Pillar_Block(RegistryObject<Block> blockRegistryObject, String sideTexture, String endTexture) {
        ResourceLocation side = modLoc(sideTexture);
        ResourceLocation end = modLoc(endTexture);
        simpleBlockWithItem(blockRegistryObject.get(), models().cubeColumnHorizontal(blockRegistryObject.getId().getPath(),side,end));
        BlockModelBuilder model1 = models().cubeColumn(blockRegistryObject.getId().getPath(),side,end);
        BlockModelBuilder model2 = models().cubeColumnHorizontal(blockRegistryObject.getId().getPath(),side,end);


    }
}
