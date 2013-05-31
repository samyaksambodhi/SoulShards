package com.shadwdrgn.soulshards;

import java.util.Calendar;

import net.minecraft.block.Block;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntitySpawnedSkeleton extends EntitySkeleton {
    /*
    private EntityAIArrowAttack field_85037_d = new EntityAIArrowAttack(this,
            0.25F, 60, 10.0F);
    private EntityAIAttackOnCollide field_85038_e = new EntityAIAttackOnCollide(
            this, EntityPlayer.class, 0.31F, false);
    */
    boolean special = false;

    public EntitySpawnedSkeleton(World par1World) {
        super(par1World);
    }

    @Override
    public void initCreature() {
        if (special) {
            tasks.addTask(4, new EntityAIAttackOnCollide(this,
                    EntityPlayer.class, 0.31F, false));
            this.setSkeletonType(1);
            this.setCurrentItemOrArmor(0, new ItemStack(Item.swordStone));
        } else {
            tasks.addTask(4,
                    new EntityAIArrowAttack(this, 0.25F, 20, 60, 15.0F));
            this.addRandomArmor();
            this.func_82162_bC();
        }

        this.setCanPickUpLoot(rand.nextFloat() < pickUpLootProability[worldObj.difficultySetting]);

        if (this.getCurrentItemOrArmor(4) == null) {
            Calendar var1 = worldObj.getCurrentDate();

            if (var1.get(2) + 1 == 10 && var1.get(5) == 31
                    && rand.nextFloat() < 0.25F) {
                this.setCurrentItemOrArmor(4, new ItemStack(
                        rand.nextFloat() < 0.1F ? Block.pumpkinLantern
                                : Block.pumpkin));
                equipmentDropChances[4] = 0.0F;
            }
        }
    }
}