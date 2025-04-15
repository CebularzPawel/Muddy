package net.cebularz.amethystmore.datagen;


import net.cebularz.amethystmore.block.ModBlocks;
import net.cebularz.amethystmore.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {

    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        //AMETHYST BRICKS
        stonecutterResultFromBase(consumer,RecipeCategory.MISC, ModBlocks.AMETHYST_BRICKS.get().asItem(), Blocks.AMETHYST_BLOCK);
        stonecutterResultFromBase(consumer,RecipeCategory.MISC, ModBlocks.AMETHYST_BRICKS_STAIRS.get().asItem(), Blocks.AMETHYST_BLOCK);
        stonecutterResultFromBase(consumer,RecipeCategory.MISC, ModBlocks.AMETHYST_BRICKS_WALL.get().asItem(), Blocks.AMETHYST_BLOCK);
        stonecutterResultFromBase(consumer,RecipeCategory.MISC, ModBlocks.AMETHYST_BRICKS_SLAB.get().asItem(), Blocks.AMETHYST_BLOCK,2);
        stonecutterResultFromBase(consumer,RecipeCategory.MISC, ModBlocks.AMETHYST_BRICKS_STAIRS.get().asItem(), ModBlocks.AMETHYST_BRICKS.get());
        stonecutterResultFromBase(consumer,RecipeCategory.MISC, ModBlocks.AMETHYST_BRICKS_WALL.get().asItem(), ModBlocks.AMETHYST_BRICKS.get());
        stonecutterResultFromBase(consumer, RecipeCategory.MISC, ModBlocks.AMETHYST_BRICKS_SLAB.get().asItem(), ModBlocks.AMETHYST_BRICKS.get(), 2);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.AMETHYST_BRICKS_SLAB.get(),6)
                .pattern("   ")
                .pattern("   ")
                .pattern("MMM")
                .define('M', ModBlocks.AMETHYST_BRICKS.get())
                .unlockedBy(getHasName(ModBlocks.AMETHYST_BRICKS.get()), has(ModBlocks.AMETHYST_BRICKS.get()))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.AMETHYST_BRICKS.get(),4)
                .pattern("   ")
                .pattern("MM ")
                .pattern("MM ")
                .define('M', Blocks.AMETHYST_BLOCK)
                .unlockedBy(getHasName(Blocks.AMETHYST_BLOCK), has(Blocks.AMETHYST_BLOCK))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.AMETHYST_BRICKS_STAIRS.get(),4)
                .pattern("M  ")
                .pattern("MM ")
                .pattern("MMM")
                .define('M', ModBlocks.AMETHYST_BRICKS.get())
                .unlockedBy(getHasName(ModBlocks.AMETHYST_BRICKS.get()), has(ModBlocks.AMETHYST_BRICKS.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.AMETHYST_BRICKS_WALL.get(),6)
                .pattern("   ")
                .pattern("MMM")
                .pattern("MMM")
                .define('M', ModBlocks.AMETHYST_BRICKS.get())
                .unlockedBy(getHasName(ModBlocks.AMETHYST_BRICKS.get()), has(ModBlocks.AMETHYST_BRICKS.get()))
                .save(consumer);



        //AMETHYST BRICKS
        stonecutterResultFromBase(consumer,RecipeCategory.MISC, ModBlocks.AMETHYST_MOSAIC.get().asItem(), Blocks.AMETHYST_BLOCK);
        stonecutterResultFromBase(consumer,RecipeCategory.MISC, ModBlocks.AMETHYST_MOSAIC.get().asItem(), ModBlocks.AMETHYST_BRICKS.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.AMETHYST_MOSAIC.get(),7)
                .pattern("MmM")
                .pattern("mMm")
                .pattern("MmM")
                .define('M', ModBlocks.AMETHYST_BRICKS.get())
                .define('m', ModBlocks.AMETHYST_BRICKS_SLAB.get())
                .unlockedBy(getHasName(ModBlocks.AMETHYST_BRICKS.get()), has(ModBlocks.AMETHYST_BRICKS.get()))
                .save(consumer);

        stonecutterResultFromBase(consumer,RecipeCategory.MISC, ModBlocks.AMETHYST_SPIRAL.get().asItem(), Blocks.AMETHYST_BLOCK);
        stonecutterResultFromBase(consumer,RecipeCategory.MISC, ModBlocks.AMETHYST_SPIRAL.get().asItem(), ModBlocks.POLISHED_AMETHYST.get());
        stonecutterResultFromBase(consumer,RecipeCategory.MISC, ModBlocks.AMETHYST_SPIRAL.get().asItem(), ModBlocks.AMETHYST_BRICKS.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.AMETHYST_SPIRAL.get(),2)
                .pattern("   ")
                .pattern(" M ")
                .pattern(" M ")
                .define('M', ModBlocks.POLISHED_AMETHYST.get())
                .unlockedBy(getHasName(ModBlocks.POLISHED_AMETHYST.get()), has(ModBlocks.POLISHED_AMETHYST.get()))
                .save(consumer);

        stonecutterResultFromBase(consumer,RecipeCategory.MISC, ModBlocks.AMETHYST_PILLAR.get().asItem(), Blocks.AMETHYST_BLOCK);
        stonecutterResultFromBase(consumer,RecipeCategory.MISC, ModBlocks.AMETHYST_PILLAR.get().asItem(), ModBlocks.AMETHYST_BRICKS.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.AMETHYST_PILLAR.get(),2)
                .pattern("   ")
                .pattern(" M ")
                .pattern(" M ")
                .define('M', ModBlocks.AMETHYST_BRICKS.get())
                .unlockedBy(getHasName(ModBlocks.AMETHYST_BRICKS.get()), has(ModBlocks.AMETHYST_BRICKS.get()))
                .save(consumer);


        //AMETHYST BRICKS
        stonecutterResultFromBase(consumer,RecipeCategory.MISC, ModBlocks.POLISHED_AMETHYST.get().asItem(), Blocks.AMETHYST_BLOCK);
        stonecutterResultFromBase(consumer,RecipeCategory.MISC, ModBlocks.POLISHED_AMETHYST_STAIRS.get().asItem(), Blocks.AMETHYST_BLOCK);
        stonecutterResultFromBase(consumer,RecipeCategory.MISC, ModBlocks.POLISHED_AMETHYST_WALL.get().asItem(), Blocks.AMETHYST_BLOCK);
        stonecutterResultFromBase(consumer,RecipeCategory.MISC, ModBlocks.POLISHED_AMETHYST_SLAB.get().asItem(), Blocks.AMETHYST_BLOCK,2);

        stonecutterResultFromBase(consumer,RecipeCategory.MISC, ModBlocks.POLISHED_AMETHYST.get().asItem(), ModBlocks.AMETHYST_BRICKS.get());
        stonecutterResultFromBase(consumer,RecipeCategory.MISC, ModBlocks.POLISHED_AMETHYST_STAIRS.get().asItem(), ModBlocks.AMETHYST_BRICKS.get());
        stonecutterResultFromBase(consumer,RecipeCategory.MISC, ModBlocks.POLISHED_AMETHYST_WALL.get().asItem(), ModBlocks.AMETHYST_BRICKS.get());
        stonecutterResultFromBase(consumer,RecipeCategory.MISC, ModBlocks.POLISHED_AMETHYST_SLAB.get().asItem(), ModBlocks.AMETHYST_BRICKS.get(),2);

        stonecutterResultFromBase(consumer,RecipeCategory.MISC, ModBlocks.POLISHED_AMETHYST_STAIRS.get().asItem(), ModBlocks.POLISHED_AMETHYST.get());
        stonecutterResultFromBase(consumer,RecipeCategory.MISC, ModBlocks.POLISHED_AMETHYST_WALL.get().asItem(), ModBlocks.POLISHED_AMETHYST.get());
        stonecutterResultFromBase(consumer, RecipeCategory.MISC, ModBlocks.POLISHED_AMETHYST_SLAB.get().asItem(), ModBlocks.POLISHED_AMETHYST.get(), 2);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.POLISHED_AMETHYST_SLAB.get(),6)
                .pattern("   ")
                .pattern("   ")
                .pattern("MMM")
                .define('M', ModBlocks.POLISHED_AMETHYST.get())
                .unlockedBy(getHasName(ModBlocks.POLISHED_AMETHYST.get()), has(ModBlocks.POLISHED_AMETHYST.get()))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.POLISHED_AMETHYST.get(),4)
                .pattern("   ")
                .pattern("MM ")
                .pattern("MM ")
                .define('M', ModBlocks.AMETHYST_BRICKS.get())
                .unlockedBy(getHasName(ModBlocks.AMETHYST_BRICKS.get()), has(ModBlocks.AMETHYST_BRICKS.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.POLISHED_AMETHYST_STAIRS.get(),4)
                .pattern("M  ")
                .pattern("MM ")
                .pattern("MMM")
                .define('M', ModBlocks.POLISHED_AMETHYST.get())
                .unlockedBy(getHasName(ModBlocks.POLISHED_AMETHYST.get()), has(ModBlocks.POLISHED_AMETHYST.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.POLISHED_AMETHYST_WALL.get(),6)
                .pattern("   ")
                .pattern("MMM")
                .pattern("MMM")
                .define('M', ModBlocks.POLISHED_AMETHYST.get())
                .unlockedBy(getHasName(ModBlocks.POLISHED_AMETHYST.get()), has(ModBlocks.POLISHED_AMETHYST.get()))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.AMETHYST_ENDER_PEARL.get())
                .pattern(" A ")
                .pattern("AEA")
                .pattern(" A ")
                .define('A', Items.AMETHYST_SHARD)
                .define('E', Items.ENDER_PEARL)
                .unlockedBy(getHasName(Items.ENDER_PEARL), has(Items.ENDER_PEARL))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.AMETHYST_CARROT.get())
                .pattern(" A ")
                .pattern("ACA")
                .pattern(" A ")
                .define('A', Items.AMETHYST_SHARD)
                .define('C', Items.CARROT)
                .unlockedBy(getHasName(Items.AMETHYST_SHARD), has(Items.AMETHYST_SHARD))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.AMETHYST_APPLE.get())
                .pattern("AAA")
                .pattern("AaA")
                .pattern("AAA")
                .define('A', Items.AMETHYST_SHARD)
                .define('a', Items.APPLE)
                .unlockedBy(getHasName(Items.AMETHYST_SHARD), has(Items.AMETHYST_SHARD))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.AMETHYST_BALL.get())
                .pattern("aAa")
                .pattern("AdA")
                .pattern("aAa")
                .define('A', Blocks.AMETHYST_BLOCK)
                .define('a', Items.AMETHYST_SHARD)
                .define('d', Items.DIAMOND)
                .unlockedBy(getHasName(ModBlocks.POLISHED_AMETHYST.get()), has(ModBlocks.POLISHED_AMETHYST.get()))
                .save(consumer);
    }
}
