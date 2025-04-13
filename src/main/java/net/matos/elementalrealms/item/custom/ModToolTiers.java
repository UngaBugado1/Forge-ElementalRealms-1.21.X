package net.matos.elementalrealms.item.custom;

import net.matos.elementalrealms.item.ModItems;
import net.matos.elementalrealms.util.ModTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class ModToolTiers {
    public static final Tier TERRAVALE = new ForgeTier(1325, 6, 3f, 20,
            ModTags.Blocks.NEEDS_TERRAVALE_TOOL, () -> Ingredient.of(ModItems.VERINDITE_CRYSTAL.get()),
            ModTags.Blocks.INCORRECT_FOR_TERRAVALE_TOOL);
}
