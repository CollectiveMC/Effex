package org.cyberpwn.effex.enchantments;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.cyberpwn.effex.ETag;
import org.cyberpwn.effex.Enchanted;
import org.cyberpwn.effex.Matte;
import org.cyberpwn.effex.effect.AmbientEffect;
import com.rit.sucy.CustomEnchantment;

public class DevilsGraceEnchantment extends CustomEnchantment implements AmbientEffect, Enchanted
{
	private double pow = 0.35;
	
	public DevilsGraceEnchantment()
	{
		super("Devil's Grace", Matte.concat(Matte.armor()));
		
		setMaxLevel(1);
		setBase(5);
		setInterval(8);
		description = "Reduces Damage from Boss Mobs (Armor)";
	}
	
	@Override
	public void applyDefenseEffect(LivingEntity user, LivingEntity target, int enchantLevel, EntityDamageEvent event)
	{
		if(event.getDamage() == 0.0 || event.isCancelled())
		{
			return;
		}
		
		try
		{
			if(target.getType().equals(EntityType.WITHER) || target.getType().equals(EntityType.ENDER_DRAGON))
			{
				event.setDamage(event.getDamage() - (event.getDamage() / 3));
			}
		}
		
		catch(Exception e)
		{
			
		}
	}
	
	@Override
	public void ambientPlay(Player p)
	{
		
	}
	
	@Override
	public double getIntensity(int level)
	{
		return pow;
	}
	
	public void setPow(double pow)
	{
		this.pow = pow;
	}
	
	@Override
	public ETag[] getTags()
	{
		return ETag.get(ETag.DEFENSIVE, ETag.UTILITY);
	}
}