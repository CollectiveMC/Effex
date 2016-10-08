package org.cyberpwn.effex.enchantments;

import org.bukkit.entity.Player;
import org.cyberpwn.effex.ETag;
import org.cyberpwn.effex.Enchanted;
import org.cyberpwn.effex.Matte;
import org.cyberpwn.effex.effect.AmbientEffect;
import org.phantomapi.util.P;
import org.phantomapi.vfx.ParticleEffect;
import com.rit.sucy.CustomEnchantment;

public class ForgeEnchantment extends CustomEnchantment implements AmbientEffect, Enchanted
{
	private double pow = 0.21;
	public ForgeEnchantment()
	{
		super("Forge", Matte.pickaxes());
		
		setMaxLevel(1);
		setBase(5);
		setInterval(8);
		description = "Forges Ore into Ingots while mined (Pickaxe)";
	}
	
	@Override
	public void ambientPlay(Player p)
	{
		if(p.isBlocking())
		{
			ParticleEffect.FLAME.display(1f, 12, P.getHand(p, -45, 40), 48);
		}
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