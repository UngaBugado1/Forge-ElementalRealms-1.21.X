package net.matos.elementalrealms.enchantment;

import net.matos.elementalrealms.ElementalRealms;
import net.matos.elementalrealms.enchantment.custom.FungusWalkEnchantmentEffect;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;

public class ModEnchantments {
    public static final ResourceKey<Enchantment> FUNGUS_WALK = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(ElementalRealms.MOD_ID, "fungus_walk"));


            public static void bootstrap(BootstrapContext<Enchantment> context)
            {
                var enchantments = context.lookup(Registries.ENCHANTMENT);
                var items = context.lookup(Registries.ITEM);

                register(context, FUNGUS_WALK, Enchantment.enchantment(Enchantment.definition(
                        items.getOrThrow(ItemTags.ARMOR_ENCHANTABLE),
                        items.getOrThrow(ItemTags.FOOT_ARMOR_ENCHANTABLE),
                        5,
                        2,
                        Enchantment.dynamicCost(5, 8),
                        Enchantment.dynamicCost(25, 8),
                        2,
                        EquipmentSlotGroup.FEET))
                        .exclusiveWith(enchantments.getOrThrow(EnchantmentTags.BOOTS_EXCLUSIVE))
                        .withEffect(EnchantmentEffectComponents.TICK, new FungusWalkEnchantmentEffect()));
            }



    private static void register(BootstrapContext<Enchantment> registry, ResourceKey<Enchantment> key, Enchantment.Builder builder) {
        registry.register(key, builder.build(key.location()));
    }
}
