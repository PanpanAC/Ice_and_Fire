package com.github.alexthe666.iceandfire.integration.tinkersconstruct.traits;

import com.github.alexthe666.iceandfire.core.ModSounds;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import slimeknights.tconstruct.library.entity.EntityProjectileBase;
import slimeknights.tconstruct.library.traits.AbstractProjectileTrait;

import javax.annotation.Nullable;

public class TraitAmphithere extends AbstractProjectileTrait {

    public TraitAmphithere() {
        super("amphithere", 0x1A977D);
    }

    @Override
    public void onLaunch(EntityProjectileBase projectileBase, World world, @Nullable EntityLivingBase shooter) {
        projectileBase.playSound(ModSounds.AMPHITHERE_GUST, 1, 1);
    }

    @Override
    public void onProjectileUpdate(EntityProjectileBase projectile, World world, ItemStack toolStack) {
        if (projectile.ticksExisted % 60 == 0 && !projectile.isDefused() && !projectile.onGround) {
            projectile.playSound(ModSounds.AMPHITHERE_GUST, 1, 1);
        }
    }

    @Override
    public void afterHit(EntityProjectileBase projectile, World world, ItemStack ammoStack, EntityLivingBase attacker, Entity target, double impactSpeed) {
//        if (target instanceof EntityPlayer) {
//            this.damageShield((EntityPlayer) target, (float) projectile.getDamage(), projectile);
//        }
        target.isAirBorne = true;
        double xRatio = projectile.motionX;
        double zRatio = projectile.motionZ;
        float strength = -1.4F;
        float f = MathHelper.sqrt(xRatio * xRatio + zRatio * zRatio);
        target.motionX /= 2.0D;
        target.motionZ /= 2.0D;
        target.motionX -= xRatio / (double) f * (double) strength;
        target.motionZ -= zRatio / (double) f * (double) strength;
        target.motionY = 0.6D;
//        spawnExplosionParticle(projectile);
    }


//    protected static void spawnExplosionParticle(EntityProjectileBase projectile) {
//        if (projectile.world.isRemote) {
//            for(int height = 0; height < 1 + projectile.world.rand.nextInt(2); height++) {
//                for (int i = 0; i < 20; ++i) {
//                    double d0 = projectile.world.rand.nextGaussian() * 0.02D;
//                    double d1 = projectile.world.rand.nextGaussian() * 0.02D;
//                    double d2 = projectile.world.rand.nextGaussian() * 0.02D;
//                    double d3 = 10.0D;
//                    double xRatio = projectile.motionX * height;
//                    double zRatio = projectile.motionZ * height;
//                    projectile.world.spawnParticle(EnumParticleTypes.CLOUD, projectile.posX  + xRatio + (double) (projectile.world.rand.nextFloat() * projectile.width * 5.0F) - (double) projectile.width - d0 * 10.0D, projectile.posY + (double) (projectile.world.rand.nextFloat() * projectile.height) - d1 * 10.0D + height, projectile.posZ + zRatio + (double) (projectile.world.rand.nextFloat() * projectile.width * 5.0F) - (double) projectile.width - d2 * 10.0D, d0, d1, d2);
//                }
//            }
//        } else {
//            projectile.world.setEntityState(projectile, (byte) 20);
//        }
//    }

//    protected static void damageShield(EntityPlayer player, float damage, EntityProjectileBase projectile) {
//        if (damage >= 3.0F && player.getActiveItemStack().getItem().isShield(player.getActiveItemStack(), player)) {
//            ItemStack copyBeforeUse = player.getActiveItemStack().copy();
//            int i = 1 + MathHelper.floor(damage);
//            player.getActiveItemStack().damageItem(i, player);
//
//            if (player.getActiveItemStack().isEmpty()) {
//                EnumHand enumhand = player.getActiveHand();
//                net.minecraftforge.event.ForgeEventFactory.onPlayerDestroyItem(player, copyBeforeUse, enumhand);
//
//                if (enumhand == EnumHand.MAIN_HAND) {
//                    projectile.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, ItemStack.EMPTY);
//                } else {
//                    projectile.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, ItemStack.EMPTY);
//                }
//                player.resetActiveHand();
//                projectile.playSound(SoundEvents.ITEM_SHIELD_BREAK, 0.8F, 0.8F + projectile.world.rand.nextFloat() * 0.4F);
//            }
//        }
//    }
}
