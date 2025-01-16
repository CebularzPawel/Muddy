package net.cebularz.amethystmore.datagen;


import net.cebularz.amethystmore.AmethystMore;
import net.cebularz.amethystmore.block.ModBlocks;
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
        super(output, AmethystMore.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {



        blockWithItem(ModBlocks.AMETHYST_BRICKS);
        stairsBlock(((StairBlock) ModBlocks.AMETHYST_BRICKS_STAIRS.get()),blockTexture(ModBlocks.AMETHYST_BRICKS.get()));
        slabBlock(((SlabBlock) ModBlocks.AMETHYST_BRICKS_SLAB.get()),blockTexture(ModBlocks.AMETHYST_BRICKS.get()),blockTexture(ModBlocks.AMETHYST_BRICKS.get()));
        wallBlock(((WallBlock) ModBlocks.AMETHYST_BRICKS_WALL.get()),blockTexture(ModBlocks.AMETHYST_BRICKS.get()));

        blockWithItem(ModBlocks.POLISHED_AMETHYST);
        stairsBlock(((StairBlock) ModBlocks.POLISHED_AMETHYST_STAIRS.get()),blockTexture(ModBlocks.POLISHED_AMETHYST.get()));
        slabBlock(((SlabBlock) ModBlocks.POLISHED_AMETHYST_SLAB.get()),blockTexture(ModBlocks.POLISHED_AMETHYST.get()),blockTexture(ModBlocks.POLISHED_AMETHYST.get()));
        wallBlock(((WallBlock) ModBlocks.POLISHED_AMETHYST_WALL.get()),blockTexture(ModBlocks.POLISHED_AMETHYST.get()));

        blockWithItem(ModBlocks.CHISELED_AMETHYST_BRICKS);

        axisBlock(((RotatedPillarBlock) ModBlocks.AMETHYST_PILLAR.get()), blockTexture(ModBlocks.AMETHYST_PILLAR.get()),
                new ResourceLocation(AmethystMore.MOD_ID, "block/amethyst_pillar_end"));
        blockItem(ModBlocks.AMETHYST_PILLAR);
        blockWithItem(ModBlocks.AMETHYST_MOSAIC);
        

        simpleBlockWithItem(ModBlocks.AMETHYST_BALL.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/amethyst_ball")));
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
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile(AmethystMore.MOD_ID +
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
