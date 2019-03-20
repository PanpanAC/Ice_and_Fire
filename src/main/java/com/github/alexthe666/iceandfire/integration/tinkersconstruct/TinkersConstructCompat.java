package com.github.alexthe666.iceandfire.integration.tinkersconstruct;

import com.github.alexthe666.iceandfire.core.ModItems;
import com.github.alexthe666.iceandfire.integration.tinkersconstruct.book.TicBook;
import com.github.alexthe666.iceandfire.integration.tinkersconstruct.modifiers.ModifierDragonsFlame;
import com.github.alexthe666.iceandfire.integration.tinkersconstruct.modifiers.ModifierDragonsFrost;
import com.github.alexthe666.iceandfire.integration.tinkersconstruct.traits.TraitAmphithere;
import com.github.alexthe666.iceandfire.integration.tinkersconstruct.traits.TraitStymphalian;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import slimeknights.mantle.client.book.repository.FileRepository;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.book.TinkerBook;
import slimeknights.tconstruct.library.materials.*;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierTrait;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import slimeknights.tconstruct.tools.TinkerModifiers;
import slimeknights.tconstruct.tools.TinkerTraits;

import static slimeknights.tconstruct.library.utils.HarvestLevels.*;

public class TinkersConstructCompat {

    // All the materials (currently) supported with Tinker's Construct
    public static Material materialDragonBone;
    public static Material materialAmphithereFeather;
    public static Material materialStymphalianBirdFeather;
    // All new traits added for parts
    public static final AbstractTrait traitAmphithere = new TraitAmphithere();
    public static final AbstractTrait traitStymphalian = new TraitStymphalian();
    // All new modifiers
    public static final Modifier modifierDragonsFlame = new ModifierDragonsFlame();
    public static final Modifier modifierDragonsFrost = new ModifierDragonsFrost();

    static void preInit() {

        // Materials
        materialDragonBone = new Material("dragonbone", 0xC9C2B7, false);

        materialDragonBone.setCraftable(true).setCastable(false);
        materialDragonBone.addItem(ModItems.dragonbone, 1, Material.VALUE_Ingot * 2);
        materialDragonBone.addItem("boneDragon", 1, Material.VALUE_Ingot * 2);
        materialDragonBone.addItemIngot("boneDragon");
        materialDragonBone.setRepresentativeItem(new ItemStack(ModItems.dragonbone));

        materialDragonBone.addTrait(TinkerTraits.splitting, MaterialTypes.SHAFT);
        materialDragonBone.addTrait(TinkerTraits.fractured);

        TinkerRegistry.addMaterial(materialDragonBone);
        TinkerRegistry.addMaterialStats(materialDragonBone,
                new HeadMaterialStats(1000, 10.0f, 7.0f, COBALT),
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

        // Modifiers
        modifierDragonsFlame.addItem(ModItems.fire_dragon_blood, 1, 1);
        modifierDragonsFrost.addItem(ModItems.ice_dragon_blood, 1, 1);

        // Register modifiers
        TicBook.registerBookPages();
    }
}
