package org.cyberpwn.effex.enchantments;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.cyberpwn.effex.ETag;
import org.cyberpwn.effex.Enchanted;
import org.cyberpwn.effex.Matte;
import org.cyberpwn.effex.effect.AmbientEffect;
import com.rit.sucy.CustomEnchantment;

public class HeatSeekerEnchantment extends CustomEnchantment implements AmbientEffect, Enchanted
{
	private double pow = 0.99;
	
	public HeatSeekerEnchantment()
	{
		super("Heat Seeker", Matte.concat(Matte.armor()));
		
		setMaxLevel(1);
		setBase(5);
		setInterval(8);
		description = "Fires an arrow that flies towards entities (bow)";
	}
	
	@Override
	public void applyDefenseEffect(LivingEntity user, LivingEntity target, int enchantLevel, EntityDamageEvent event)
	{
		
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
		return ETag.get(ETag.OFFENSIVE);
	}
}