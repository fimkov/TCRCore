package com.p1nero.tcrcore.datagen;

import com.github.L_Ender.cataclysm.init.ModItems;
import com.p1nero.tcrcore.datagen.tags.TCRItemTags;
import com.p1nero.tcrcore.item.TCRItems;
import io.redspace.ironsspellbooks.registries.ItemRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.function.Consumer;

public class TCRRecipeGenerator extends TCRRecipeProvider implements IConditionBuilder {
    public TCRRecipeGenerator(PackOutput output) {
        super(output);
    }
    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TCRItems.DRAGON_FLUTE.get(), 1)
                .pattern(" AA")
                .pattern("AAA")
                .pattern("BA ")
                .define('A', ModItems.KOBOLETON_BONE.get())
                .define('B', Items.STICK)
                .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, TCRItems.WITHER_SOUL_STONE_ACTIVATED.get(), 1)
                .requires(TCRItems.WITHER_SOUL_STONE.get())
                .requires(Items.GHAST_TEAR)
                .unlockedBy(getHasName(TCRItems.WITHER_SOUL_STONE.get()), has(TCRItems.WITHER_SOUL_STONE.get()))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, TCRItems.ANCIENT_ORACLE_FRAGMENT.get(), 1)
                .requires(TCRItemTags.CATACLYSM_HUMANOID_BOSS_DROP)
                .requires(TCRItemTags.CATACLYSM_HUMANOID_BOSS_DROP)
                .requires(TCRItemTags.CATACLYSM_HUMANOID_BOSS_DROP)
                .requires(TCRItemTags.CATACLYSM_HUMANOID_BOSS_DROP)
                .unlockedBy(getHasName(TCRItems.WITHER_SOUL_STONE.get()), has(TCRItems.WITHER_SOUL_STONE.get()))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, TCRItems.PROOF_OF_ADVENTURE_PLUS.get(), 1)
                .requires(TCRItems.ABYSS_FRAGMENT.get())
                .requires(TCRItems.STORM_FRAGMENT.get())
                .requires(TCRItems.FLAME_FRAGMENT.get())
                .requires(TCRItems.MECH_FRAGMENT.get())
                .requires(TCRItems.SOUL_FRAGMENT.get())
                .requires(TCRItems.DESERT_FRAGMENT.get())
                .requires(TCRItems.ENDER_FRAGMENT.get())
                .requires(TCRItems.NETHERITE_FRAGMENT.get())
                .requires(TCRItems.DIVINE_FRAGMENT.get())
                .unlockedBy(getHasName(TCRItems.WITHER_SOUL_STONE.get()), has(TCRItems.WITHER_SOUL_STONE.get()))
                .save(consumer);

    }

}
