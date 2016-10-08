package org.cyberpwn.effex.enchantments;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.cyberpwn.effex.ETag;
import org.cyberpwn.effex.Enchanted;
import org.cyberpwn.effex.Matte;
import org.cyberpwn.effex.effect.AmbientEffect;
import com.rit.sucy.CustomEnchantment;

public class AntiGravityEnchantment extends CustomEnchantment implements AmbientEffect, Enchanted
{
	private double pow = 0.23;
	public AntiGravityEnchantment()
	{
		super("Anti Gravity", Matte.boots());
		
		setMaxLevel(1);
		setBase(5);
		setInterval(8);
		description = "Never take fall damage. (Boots)";
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
		return ETag.get(ETag.UTILITY);
	}
}