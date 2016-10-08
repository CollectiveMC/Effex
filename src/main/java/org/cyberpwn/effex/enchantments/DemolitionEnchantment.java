package org.cyberpwn.effex.enchantments;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.cyberpwn.effex.ETag;
import org.cyberpwn.effex.Enchanted;
import org.cyberpwn.effex.Matte;
import org.cyberpwn.effex.effect.AmbientEffect;
import com.rit.sucy.CustomEnchantment;

public class DemolitionEnchantment extends CustomEnchantment implements AmbientEffect, Enchanted
{
	private double pow = 0.51;
	public DemolitionEnchantment()
	{
		super("Demolition", Matte.concat(Matte.armor()));
		
		setMaxLevel(2);
		setBase(5);
		setInterval(8);
		description = "Explosive Thorns (Armor)";
	}
	
	@Override
	public void applyDefenseEffect(LivingEntity user, LivingEntity target, int enchantLevel, EntityDamageEvent event)
	{
		if(Math.random() > 0.787 - (enchantLevel / 5))
		{
			double h = user.getHealth();
			user.getLocation().getWorld().createExplosion(user.getLocation().getX(), user.getLocation().getY(), user.getLocation().getZ(), 0f, false, false);
			user.setHealth(h);
			target.damage(2);
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
		return ETag.get(ETag.DEFENSIVE, ETag.OFFENSIVE);
	}
}