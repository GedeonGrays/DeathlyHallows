package com.pyding.deathlyhallows.items;

import com.pyding.deathlyhallows.common.properties.ExtendedPlayer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class DeadlyPrism extends Item {
    public int damage;
    public float damageAmount;

    public String damageSource(){
        switch (damage){
            case 0:{
               return "Void";
            }
            case 1:{
                return "Magic";
            }
            case 2:{
                return "Generic";
            }
            case 3:{
                return "Bypass Armor";
            }
            case 4:{
                return "Fire";
            }
            case 5:{
                return "Explosion";
            }
            case 6:{
                return "Allowed in Creative Mode";
            }
            case 7:{
                return "Projectile";
            }
            case 8:{
                return "Absolute";
            }
            case 9:{
                return "Anvil";
            }
            case 10:{
                return "Wither";
            }
            case 11:{
                return "Lava";
            }
        }
        return null;
    }
    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        try{
            if(!player.worldObj.isRemote){
                switch (damage){
                    case 0:{
                        player.attackEntityFrom(DamageSource.outOfWorld,damageAmount);
                    }
                    case 1:{
                        player.attackEntityFrom(DamageSource.magic,damageAmount);
                    }
                    case 2:{
                        player.attackEntityFrom(DamageSource.generic,damageAmount);
                    }
                    case 3:{
                        player.attackEntityFrom(new DamageSource("bypass").setDamageBypassesArmor(),damageAmount);
                    }
                    case 4:{
                        player.attackEntityFrom(new DamageSource("fire").setFireDamage(),damageAmount);
                    }
                    case 5:{
                        player.attackEntityFrom(new DamageSource("explosion").setExplosion(),damageAmount);
                    }
                    case 6:{
                        player.attackEntityFrom(new DamageSource("creative").setDamageAllowedInCreativeMode(),damageAmount);
                    }
                    case 7:{
                        player.attackEntityFrom(new DamageSource("projectile").setProjectile(),damageAmount);
                    }
                    case 8:{
                        player.attackEntityFrom(new DamageSource("absolute").setDamageIsAbsolute(),damageAmount);
                    }
                    case 9:{
                        player.attackEntityFrom(DamageSource.anvil,damageAmount);
                    }
                    case 10:{
                        player.attackEntityFrom(DamageSource.wither,damageAmount);
                    }
                    case 11:{
                        player.attackEntityFrom(DamageSource.lava,damageAmount);
                    }
                }
            }
            /*ExtendedPlayer props = ExtendedPlayer.get(player);
            int amount = 400;
            props.setCurrentDuration(amount);
            player.getEntityData().setInteger("dhcurse",amount);
            player.getEntityData().setInteger("casterCurse",amount);
            props.setSource(player);
            props.setCoordinates(player.posX,player.posY,player.posZ,player.dimension);*/
        } catch(Exception e){
            e.printStackTrace();
        }
        return super.onItemRightClick(stack,world,player);
    }

    @Override
    public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) {
        if(entityLiving instanceof EntityPlayer && !entityLiving.worldObj.isRemote){
            EntityPlayer player = (EntityPlayer) entityLiving;
            if(!player.isSneaking()){
                if(damage == 0 || damage < 11)
                    damage++;
                else damage = 0;
                player.addChatComponentMessage(new ChatComponentText("Damage type is set to: §5"+damageSource()));
            } else {
                player.addChatComponentMessage(new ChatComponentText("Type amount of damage in chat:"));
                player.getEntityData().setBoolean("DeadlyPrism",true);
            }
        }
        return super.onEntitySwing(entityLiving, stack);
    }
}
