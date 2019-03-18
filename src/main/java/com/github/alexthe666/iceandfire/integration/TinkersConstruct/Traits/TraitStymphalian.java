package com.github.alexthe666.iceandfire.integration.TinkersConstruct.Traits;

import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import slimeknights.tconstruct.library.entity.EntityProjectileBase;
import slimeknights.tconstruct.library.traits.AbstractProjectileTrait;

public class TraitStymphalian extends AbstractProjectileTrait {

    public TraitStymphalian() {
        super("stymphalian", 0x7E5C45);
    }

    @Override
    public void onMovement(EntityProjectileBase projectile, World world, double slowdown) {
        projectile.motionY += projectile.getGravity()*75d/100d;
    }
}
