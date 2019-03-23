package com.github.alexthe666.iceandfire.integration.tinkersconstruct.modifiers;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import slimeknights.tconstruct.library.modifiers.ModifierTrait;

public class ModofierMyrmexVenom extends ModifierTrait {

    public ModofierMyrmexVenom() {
        super("myrmex_venomous", 0x5BBD50);
    }

    @Override
    public void onHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, boolean isCritical) {
        target.addPotionEffect(new PotionEffect(MobEffects.POISON, 200, 2));

        super.onHit(tool, player, target, damage, isCritical);
    }
}
