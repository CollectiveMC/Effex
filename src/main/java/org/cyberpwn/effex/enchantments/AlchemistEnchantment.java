package org.cyberpwn.effex.enchantments;

import org.bukkit.entity.Player;
import org.cyberpwn.effex.ETag;
import org.cyberpwn.effex.Enchanted;
import org.cyberpwn.effex.Matte;
import org.cyberpwn.effex.effect.AmbientEffect;
import com.rit.sucy.CustomEnchantment;

public class AlchemistEnchantment extends CustomEnchantment implements AmbientEffect, Enchanted
{
	private double pow = 0.1;
	
	public AlchemistEnchantment()
	{
		super("Alchemist", Matte.helmets());
		
		setMaxLevel(3);
		setBase(5);
		setInterval(8);
		description = "Chance of not consuming potions you drink. (Helmet)";
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