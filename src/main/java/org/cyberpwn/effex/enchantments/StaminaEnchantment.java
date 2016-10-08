package org.cyberpwn.effex.enchantments;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.cyberpwn.effex.ETag;
import org.cyberpwn.effex.Enchanted;
import org.cyberpwn.effex.Matte;
import org.cyberpwn.effex.effect.AmbientEffect;
import com.rit.sucy.CustomEnchantment;

public class StaminaEnchantment extends CustomEnchantment implements AmbientEffect, Enchanted
{
	private double pow = 0.12;
	public StaminaEnchantment()
	{
		super("Stamina", Matte.concat(Matte.helmets()));
		
		setMaxLevel(1);
		setBase(5);
		setInterval(8);
		description = "Free Food Forever (Helmet)";
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

	@Override
	public ETag[] getTags()
	{
		return ETag.get(ETag.UTILITY);
	}
	
	public void setPow(double pow)
	{
		this.pow = pow;
	}
}