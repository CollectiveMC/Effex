package org.cyberpwn.effex.enchantments;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.cyberpwn.effex.ETag;
import org.cyberpwn.effex.Enchanted;
import org.cyberpwn.effex.Matte;
import org.cyberpwn.effex.effect.AmbientEffect;
import com.rit.sucy.CustomEnchantment;

public class MagnetEnchantment extends CustomEnchantment implements AmbientEffect, Enchanted
{
	private double pow = 0.37;
	public MagnetEnchantment()
	{
		super("Magnetic", Matte.concat(Matte.boots()));
		
		setMaxLevel(1);
		setBase(5);
		setInterval(30);
		description = "Auto Collect broken block items (Boots)";
	}
	
	@Override
	public void applyEffect(LivingEntity user, LivingEntity target, int enchantLevel, EntityDamageByEntityEvent event)
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