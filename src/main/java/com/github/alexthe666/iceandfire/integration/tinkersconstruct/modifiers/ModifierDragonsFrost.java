package com.github.alexthe666.iceandfire.integration.tinkersconstruct.modifiers;

import com.github.alexthe666.iceandfire.entity.EntityFireDragon;
import com.github.alexthe666.iceandfire.entity.FrozenEntityProperties;
import com.github.alexthe666.iceandfire.integration.tinkersconstruct.TinkersConstructCompat;
import net.ilexiconn.llibrary.server.entity.EntityPropertiesHandler;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Enchantments;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import slimeknights.tconstruct.library.modifiers.IToolMod;
import slimeknights.tconstruct.library.modifiers.ModifierTrait;
import slimeknights.tconstruct.tools.TinkerModifiers;

public class ModifierDragonsFrost extends ModifierTrait {

    public ModifierDragonsFrost() {
        super("dragons_frost", 0x1BD2FF);
    }

    @Override
    public boolean canApplyTogether(Enchantment enchantment) {
        return enchantment != Enchantments.FIRE_ASPECT && enchantment != Enchantments.FLAME;
    }

    @Override
    public boolean canApplyTogether(IToolMod otherModifier) {
        return otherModifier != TinkerModifiers.modFiery && otherModifier != TinkersConstructCompat.modifierDragonsFlame;
    }

    @Override
    public void onHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, boolean isCritical) {
        if (target instanceof EntityFireDragon) {
            target.attackEntityFrom(DamageSource.DROWN, 13.5F);
        }
        FrozenEntityProperties frozenProps = EntityPropertiesHandler.INSTANCE.getProperties(target, FrozenEntityProperties.class);
        frozenProps.setFrozenFor(200);
        target.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 100, 2));
        target.addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 100, 2));

        super.onHit(tool, player, target, damage, isCritical);
    }

    @Override
    public float knockBack(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, float knockback, float newKnockback, boolean isCritical) {
        return newKnockback + 2.5f;
    }
}
