package net.matos.elementalrealms.datagen;

import net.matos.elementalrealms.ElementalRealms;
import net.matos.elementalrealms.block.ModBlocks;
import net.matos.elementalrealms.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(pOutput, pRegistries);
    }

    @Override
    protected void buildRecipes(RecipeOutput pRecipeOutput) {


                            //LIST RECIPE BUILDER
//        List<ItemLike> TEST_SMELTABLES = List.of(ModItems.SMELTABLE_OUTPUT.get(),
//                ModBlocks.SMELTABLE_INPUT1.get(), ModBlocks.SMELTABLE_INPUT2.get());


                            // SHAPED RECIPE BUILDER
//        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BLOCK.get())
//                .pattern("AAA")
//                .pattern("AAA")
//                .pattern("AAA")
//                .define('A', ModItems.BLOCK_ITEM.get())
//                .unlockedBy(getHasName(ModItems.BLOCK_ITEM.get()), has(ModItems.BLOCK_ITEM.get())).save(pRecipeOutput);

                stairBuilder(ModBlocks.TERRAITE_STAIRS.get(), Ingredient.of(ModBlocks.TERRAITE.get())).group("terraite")
                        .unlockedBy(getHasName(ModBlocks.TERRAITE.get()), has(ModBlocks.TERRAITE.get())).save(pRecipeOutput);
                stairBuilder(ModBlocks.POLISHED_TERRAITE_STAIRS.get(), Ingredient.of(ModBlocks.POLISHED_TERRAITE.get())).group("terraite")
                        .unlockedBy(getHasName(ModBlocks.POLISHED_TERRAITE.get()), has(ModBlocks.POLISHED_TERRAITE.get())).save(pRecipeOutput);
                stairBuilder(ModBlocks.TERRAITE_BRICK_STAIRS.get(), Ingredient.of(ModBlocks.TERRAITE_BRICKS.get())).group("terraite")
                        .unlockedBy(getHasName(ModBlocks.TERRAITE_BRICKS.get()), has(ModBlocks.TERRAITE_BRICKS.get())).save(pRecipeOutput);

                slab(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.TERRAITE_SLAB.get(), ModBlocks.TERRAITE.get());
                slab(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_TERRAITE_SLAB.get(), ModBlocks.POLISHED_TERRAITE.get());
                slab(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.TERRAITE_BRICK_SLAB.get(), ModBlocks.TERRAITE_BRICKS.get());

                wall(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.TERRAITE_WALL.get(), ModBlocks.TERRAITE.get());
                wall(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_TERRAITE_WALL.get(), ModBlocks.POLISHED_TERRAITE.get());
                wall(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.TERRAITE_BRICK_WALL.get(), ModBlocks.TERRAITE_BRICKS.get());

                 buttonBuilder(ModBlocks.POLISHED_TERRAITE_BUTTON.get(), Ingredient.of(ModBlocks.POLISHED_TERRAITE.get())).group("terraite")
                .unlockedBy(getHasName(ModBlocks.POLISHED_TERRAITE.get()), has(ModBlocks.POLISHED_TERRAITE.get())).save(pRecipeOutput);

                polished(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_TERRAITE.get(), ModBlocks.TERRAITE.get());
                chiseled(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_TERRAITE.get(), ModBlocks.POLISHED_TERRAITE_SLAB.get());

                                                    // -----> Stonecutting recipes for Terraite variants <----- \\

                // Stonecutting recipes for Terraite
                 stonecutterResultFromBase(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.TERRAITE_STAIRS.get(), ModBlocks.TERRAITE.get());
                 stonecutterResultFromBase(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.TERRAITE_SLAB.get(), ModBlocks.TERRAITE.get(), 2);
                 stonecutterResultFromBase(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.TERRAITE_WALL.get(), ModBlocks.TERRAITE.get());

                // Stonecutting recipes for Terraite Bricks
                 stonecutterResultFromBase(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.TERRAITE_BRICKS.get(), ModBlocks.TERRAITE.get());
                 stonecutterResultFromBase(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.TERRAITE_BRICK_STAIRS.get(), ModBlocks.TERRAITE.get());
                 stonecutterResultFromBase(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.TERRAITE_BRICK_SLAB.get(), ModBlocks.TERRAITE.get(), 2);
                 stonecutterResultFromBase(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.TERRAITE_BRICK_WALL.get(), ModBlocks.TERRAITE.get());

        stonecutterResultFromBase(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.TERRAITE_BRICKS.get(), ModBlocks.TERRAITE_BRICKS.get());
        stonecutterResultFromBase(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.TERRAITE_BRICK_STAIRS.get(), ModBlocks.TERRAITE_BRICKS.get());
        stonecutterResultFromBase(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.TERRAITE_BRICK_SLAB.get(), ModBlocks.TERRAITE_BRICKS.get(), 2);
        stonecutterResultFromBase(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.TERRAITE_BRICK_WALL.get(), ModBlocks.TERRAITE_BRICKS.get());

                // Stonecutting recipes for Polished Terraite
                 stonecutterResultFromBase(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_TERRAITE.get(), ModBlocks.TERRAITE.get());
                 stonecutterResultFromBase(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_TERRAITE_STAIRS.get(), ModBlocks.TERRAITE.get());
                 stonecutterResultFromBase(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_TERRAITE_SLAB.get(), ModBlocks.TERRAITE.get(), 2);
                 stonecutterResultFromBase(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_TERRAITE_WALL.get(), ModBlocks.TERRAITE.get());

        stonecutterResultFromBase(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_TERRAITE.get(), ModBlocks.POLISHED_TERRAITE.get());
        stonecutterResultFromBase(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_TERRAITE_STAIRS.get(), ModBlocks.POLISHED_TERRAITE.get());
        stonecutterResultFromBase(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_TERRAITE_SLAB.get(), ModBlocks.POLISHED_TERRAITE.get(), 2);
        stonecutterResultFromBase(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_TERRAITE_WALL.get(), ModBlocks.POLISHED_TERRAITE.get());







        //SHAPELESS RECIPE BUILDER
//        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.BLOCK_ITEM.get(), 9)
//                .requires(ModBlocks.MOD_BLOCK.get())
//                .unlockedBy(getHasName(ModBlocks.MOD_BLOCK.get()), has(ModBlocks.MOD_BLOCK.get())).save(pRecipeOutput);


//        oreSmelting(pRecipeOutput, TEST_SMELTABLES, RecipeCategory.MISC, ModItems.SMELTABLE_OUTPUT.get(), 0.25f, 200, "smeltable_id");
//        oreBlasting(pRecipeOutput, TEST_SMELTABLES, RecipeCategory.MISC, ModItems.SMELTABLE_OUTPUT.get(), 0.25f, 100, "smeltable_id");

    }

    protected static RecipeBuilder polishedBuilder(RecipeCategory pCategory, ItemLike pResult, Ingredient pMaterial) {
        return ShapedRecipeBuilder.shaped(pCategory, pResult, 4).define('S', pMaterial).pattern("SS").pattern("SS");
    }

    protected static void chiseled(RecipeOutput pRecipeOutput, RecipeCategory pCategory, ItemLike pChiseledResult, ItemLike pMaterial) {
        chiseledBuilder(pCategory, pChiseledResult, Ingredient.of(pMaterial))
                .unlockedBy(getHasName(pMaterial), has(pMaterial))
                .save(pRecipeOutput);
    }

    protected static void stonecutterResultFromBase(RecipeOutput pRecipeOutput, RecipeCategory pCategory, ItemLike pResult, ItemLike pMaterial) {
        stonecutterResultFromBase(pRecipeOutput, pCategory, pResult, pMaterial, 1);
    }

    protected static void stonecutterResultFromBase(RecipeOutput pRecipeOutput, RecipeCategory pCategory, ItemLike pResult, ItemLike pMaterial, int pResultCount) {
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(pMaterial), pCategory, pResult, pResultCount)
                .unlockedBy(getHasName(pMaterial), has(pMaterial))
                .save(pRecipeOutput, getConversionRecipeName(pResult, pMaterial) + "_stonecutting");
    }

    protected static ShapedRecipeBuilder chiseledBuilder(RecipeCategory pCategory, ItemLike pChiseledResult, Ingredient pMaterial) {
        return ShapedRecipeBuilder.shaped(pCategory, pChiseledResult)
                .define('#', pMaterial)
                .pattern("#")
                .pattern("#");
    }


    protected static void oreSmelting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTime, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput recipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory,
                                                                       List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, ElementalRealms.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }
}
