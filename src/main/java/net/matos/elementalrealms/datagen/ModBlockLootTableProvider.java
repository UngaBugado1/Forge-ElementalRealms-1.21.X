package net.matos.elementalrealms.datagen;

import net.matos.elementalrealms.block.ModBlocks;
import net.matos.elementalrealms.block.custom.EmberootCropBlock;
import net.matos.elementalrealms.item.ModItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider pRegistries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), pRegistries);
    }

    @Override
    protected void generate() {
        dropSelf(ModBlocks.CHISELED_TERRAITE.get());
        dropSelf(ModBlocks.TECTORAX_EGG.get());

        dropSelf(ModBlocks.TERRAITE.get());
        dropSelf(ModBlocks.TERRAITE_STAIRS.get());
        dropSelf(ModBlocks.TERRAITE_WALL.get());
        this.add(ModBlocks.TERRAITE_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.TERRAITE_SLAB.get()));


        dropSelf(ModBlocks.POLISHED_TERRAITE.get());
        dropSelf(ModBlocks.POLISHED_TERRAITE_STAIRS.get());
        dropSelf(ModBlocks.POLISHED_TERRAITE_WALL.get());
        dropSelf(ModBlocks.POLISHED_TERRAITE_BUTTON.get());
        this.add(ModBlocks.POLISHED_TERRAITE_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.POLISHED_TERRAITE_SLAB.get()));


        dropSelf(ModBlocks.TERRAITE_BRICKS.get());
        dropSelf(ModBlocks.TERRAITE_BRICK_STAIRS.get());
        dropSelf(ModBlocks.TERRAITE_BRICK_WALL.get());
        this.add(ModBlocks.TERRAITE_BRICK_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.TERRAITE_BRICK_SLAB.get()));


        this.add(ModBlocks.GEOCLUSTER.get(),
                block -> createMultipleOreDrops(ModBlocks.GEOCLUSTER.get(), ModItems.VERINDITE_CRYSTAL.get(), 2, 3));


        LootItemCondition.Builder lootItemConditionBuilder = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.EMBEROOT_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(EmberootCropBlock.AGE, EmberootCropBlock.MAX_AGE));

        this.add(ModBlocks.EMBEROOT_CROP.get(), this.createCropDrops(ModBlocks.EMBEROOT_CROP.get(),
                ModItems.EMBEROOT.get(), ModItems.EMBEROOT.get(), lootItemConditionBuilder));



        this.dropSelf(ModBlocks.ARCHAIC_LOG.get());
        this.dropSelf(ModBlocks.ARCHAIC_WOOD.get());
        this.dropSelf(ModBlocks.STRIPPED_ARCHAIC_LOG.get());
        this.dropSelf(ModBlocks.STRIPPED_ARCHAIC_WOOD.get());
        this.dropSelf(ModBlocks.ARCHAIC_PLANKS.get());
        this.dropSelf(ModBlocks.ARCHAIC_SAPLING.get());

        this.dropSelf(ModBlocks.ARCHAIC_STAIRS.get());
        this.dropSelf(ModBlocks.ARCHAIC_SLAB.get());
        this.dropSelf(ModBlocks.ARCHAIC_FENCE.get());
        this.dropSelf(ModBlocks.ARCHAIC_FENCE_GATE.get());
        this.dropSelf(ModBlocks.ARCHAIC_PRESSURE_PLATE.get());
        this.dropSelf(ModBlocks.ARCHAIC_BUTTON.get());
        this.dropSelf(ModBlocks.ARCHAIC_TRAPDOOR.get());

        this.add(ModBlocks.ARCHAIC_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.ARCHAIC_SLAB.get()));

        this.add(ModBlocks.ARCHAIC_DOOR.get(),
                block -> createDoorTable(ModBlocks.ARCHAIC_DOOR.get()));

        this.add(ModBlocks.ARCHAIC_LEAVES.get(), block ->
                createLeavesDrops(block, ModBlocks.ARCHAIC_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
    }

    protected LootTable.Builder createMultipleOreDrops(Block pBlock, Item item, float minDrops, float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(
                pBlock, this.applyExplosionDecay(
                        pBlock, LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                                .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                )
        );
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
