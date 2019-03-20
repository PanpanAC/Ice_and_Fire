package com.github.alexthe666.iceandfire.integration.tinkersconstruct.modifiers;

import com.github.alexthe666.iceandfire.entity.EntityIceDragon;
import com.github.alexthe666.iceandfire.integration.tinkersconstruct.TinkersConstructCompat;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import slimeknights.tconstruct.library.modifiers.IToolMod;
import slimeknights.tconstruct.library.modifiers.ModifierTrait;
import slimeknights.tconstruct.tools.TinkerModifiers;

public class ModifierDragonsFlame extends ModifierTrait {

    public ModifierDragonsFlame() {
        super("dragons_flame", 0xEE5E10);
    }

    @Override
    public boolean canApplyTogether(Enchantment enchantment) {
        return enchantment != Enchantments.FIRE_ASPECT && enchantment != Enchantments.FLAME;
    }

    @Override
    public boolean canApplyTogether(IToolMod otherModifier) {
        return otherModifier != TinkerModifiers.modFiery && otherModifier != TinkersConstructCompat.modifierDragonsFrost;
    }

    @Override
    public void onHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, boolean isCritical) {
        if (target instanceof EntityIceDragon) {
            target.attackEntityFrom(DamageSource.IN_FIRE, 13.5f);
        }
        target.setFire(5);

        super.onHit(tool, player, target, damage, isCritical);
    }

    @Override
    public float knockBack(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, float knockback, float newKnockback, boolean isCritical) {
        return newKnockback + 3f;
    }
}
