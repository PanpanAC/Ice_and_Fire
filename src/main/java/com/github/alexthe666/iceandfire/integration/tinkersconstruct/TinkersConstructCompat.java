package com.github.alexthe666.iceandfire.integration.tinkersconstruct;

import com.github.alexthe666.iceandfire.core.ModBlocks;
import com.github.alexthe666.iceandfire.core.ModItems;
import com.github.alexthe666.iceandfire.entity.EntitySnowVillager;
import com.github.alexthe666.iceandfire.integration.tinkersconstruct.book.TicBook;
import com.github.alexthe666.iceandfire.integration.tinkersconstruct.modifiers.ModofierMyrmexVenom;
import com.github.alexthe666.iceandfire.integration.tinkersconstruct.modifiers.ModifierDragonsFlame;
import com.github.alexthe666.iceandfire.integration.tinkersconstruct.modifiers.ModifierDragonsFrost;
import com.github.alexthe666.iceandfire.integration.tinkersconstruct.traits.TraitAmphithere;
import com.github.alexthe666.iceandfire.integration.tinkersconstruct.traits.TraitStymphalian;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import slimeknights.mantle.util.RecipeMatch;
import slimeknights.tconstruct.common.config.Config;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.events.MaterialEvent;
import slimeknights.tconstruct.library.fluid.FluidMolten;
import slimeknights.tconstruct.library.materials.*;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.smeltery.CastingRecipe;
import slimeknights.tconstruct.library.smeltery.MeltingRecipe;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import slimeknights.tconstruct.library.utils.HarvestLevels;
import slimeknights.tconstruct.shared.TinkerFluids;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;
import slimeknights.tconstruct.tools.TinkerMaterials;
import slimeknights.tconstruct.tools.TinkerTraits;

@Mod.EventBusSubscriber
public class TinkersConstructCompat {

    // There's no need to add support for silver as it's natively supported by Tinker's Construct
    // All the materials (currently) supported with Tinker's Construct
    public static Material materialDragonBone;
    public static Material materialAmphithereFeather;
    public static Material materialStymphalianBirdFeather;
    // Liquids added by this mod
    public static FluidMolten moltenSapphire;
    // All new traits added for parts
    public static final AbstractTrait traitAmphithere = new TraitAmphithere();
    public static final AbstractTrait traitStymphalian = new TraitStymphalian();
    // All new modifiers
    public static final Modifier modifierDragonsFlame = new ModifierDragonsFlame();
    public static final Modifier modifierDragonsFrost = new ModifierDragonsFrost();
    public static final Modifier modifierMyrmexPoison = new ModofierMyrmexVenom();

    static void preInit() {

        // Materials
        // Right now the dragon bone material is mimicking the normal bone as a material, but it's generally stronger.
        materialDragonBone = new Material("dragonbone", 0xC9C2B7, false);

        materialDragonBone.setCraftable(true).setCastable(false);
        materialDragonBone.addItem(ModItems.dragonbone, 1, Material.VALUE_Ingot * 2);
        materialDragonBone.addItem("boneDragon", 1, Material.VALUE_Ingot * 2);
        materialDragonBone.addItemIngot("boneDragon");
        materialDragonBone.setRepresentativeItem(new ItemStack(ModItems.dragonbone));

        materialDragonBone.addTrait(TinkerTraits.splitting, MaterialTypes.SHAFT);
        materialDragonBone.addTrait(TinkerTraits.splintering);

        TinkerRegistry.addMaterial(materialDragonBone);
        TinkerRegistry.addMaterialStats(materialDragonBone,
                new HeadMaterialStats(1000, 10.0f, 7.0f, HarvestLevels.COBALT),
                new BowMaterialStats(0.55f, 1.25f, 7),
                new ArrowShaftMaterialStats(1.5f, 10));



        materialAmphithereFeather = new Material("amphithere_feather", 0x1A977D, false);

        materialAmphithereFeather.setCraftable(true).setCastable(false);
        materialAmphithereFeather.addItem(ModItems.amphithere_feather, 1, Material.VALUE_Ingot);
        materialAmphithereFeather.setRepresentativeItem(new ItemStack(ModItems.amphithere_feather));

        materialAmphithereFeather.addTrait(traitAmphithere, MaterialTypes.FLETCHING);

        TinkerRegistry.addMaterial(materialAmphithereFeather);
        TinkerRegistry.addMaterialStats(materialAmphithereFeather,
                new FletchingMaterialStats(1.25f, 1.5f));



        materialStymphalianBirdFeather = new Material("stymphalian_bird_feather",0x7E5C45, false);

        materialStymphalianBirdFeather.setCraftable(true).setCastable(false);
        materialStymphalianBirdFeather.addItem(ModItems.stymphalian_bird_feather, 1, Material.VALUE_Ingot);
        materialStymphalianBirdFeather.setRepresentativeItem(new ItemStack(ModItems.stymphalian_bird_feather));

        materialStymphalianBirdFeather.addTrait(traitStymphalian, MaterialTypes.FLETCHING);

        TinkerRegistry.addMaterial(materialStymphalianBirdFeather);
        TinkerRegistry.addMaterialStats(materialStymphalianBirdFeather,
                new FletchingMaterialStats(1.5f, 0.75f));



        // Liquids
        moltenSapphire = new FluidMolten("sapphire", 0x7D96E7);
        moltenSapphire.setTemperature(820);
        FluidRegistry.registerFluid(moltenSapphire);
        FluidRegistry.addBucketForFluid(moltenSapphire);

        TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of("gemSapphire", Material.VALUE_Gem), moltenSapphire));
        TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of("oreSapphire", (int) (Material.VALUE_Gem * Config.oreToIngotRatio)), moltenSapphire));
        TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of("blockSapphire", Material.VALUE_Gem * 9), moltenSapphire));

        // Smelting gold/iron hippogryph armor
        TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(ModItems.iron_hippogryph_armor, Material.VALUE_Ingot * 4), TinkerFluids.iron));
        TinkerRegistry.registerMelting(new MeltingRecipe(RecipeMatch.of(ModItems.gold_hippogryph_armor, Material.VALUE_Ingot * 4), TinkerFluids.gold));

        // Modifiers
        modifierDragonsFlame.addItem(ModItems.fire_dragon_blood, 1, 1);
        modifierDragonsFrost.addItem(ModItems.ice_dragon_blood, 1, 1);
        modifierMyrmexPoison.addItem(ModItems.myrmex_stinger, 1, 1);

        // Register modifiers
        TicBook.registerBookPages();
    }

    static void init() {

        // Register casting molten sapphire to gem and block forms
        TinkerRegistry.registerTableCasting(new ItemStack(ModItems.sapphireGem), TinkerSmeltery.castGem, moltenSapphire, Material.VALUE_Gem);
        TinkerRegistry.registerBasinCasting(new ItemStack(ModBlocks.sapphireBlock), ItemStack.EMPTY, moltenSapphire, Material.VALUE_Gem * 9);
        // Register snow villager to sapphire melting to match the normal villager to emerald melting behavior
        TinkerRegistry.registerEntityMelting(EntitySnowVillager.class, new FluidStack(moltenSapphire, 6));
    }

    static void postInit() {

        // Register cast creation from sapphire gems
        for (FluidStack fs : TinkerSmeltery.castCreationFluids) {
            TinkerRegistry.registerTableCasting(new CastingRecipe(TinkerSmeltery.castGem, RecipeMatch.of("gemSapphire"), fs, true, true));
        }
    }

    // We are adding our own head stats to silver tools to match the speed and mining level of the tools from this mod (allowing them to compete).
    // We leave the durability (as the result is similar) and damage (as it's higher in Tinker's Construct, but it wouldn't feel right to nerf it)
    @SubscribeEvent
    public static void statRegister(MaterialEvent.StatRegisterEvent event) {

        if (event.material == TinkerMaterials.silver && event.stats.getIdentifier().equals(MaterialTypes.HEAD)) {
            MaterialEvent.StatRegisterEvent<HeadMaterialStats> registerEvent = (MaterialEvent.StatRegisterEvent<HeadMaterialStats>)event;
            registerEvent.overrideResult(new HeadMaterialStats(registerEvent.stats.durability, 11.0f, registerEvent.stats.attack, HarvestLevels.DIAMOND));
        }
    }
}
