package org.cyberpwn.effex.enchantments;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.cyberpwn.effex.ETag;
import org.cyberpwn.effex.Enchanted;
import org.cyberpwn.effex.Matte;
import org.cyberpwn.effex.effect.AmbientEffect;
import org.phantomapi.nms.NMSX;
import org.phantomapi.util.P;
import org.phantomapi.vfx.ParticleEffect;
import com.rit.sucy.CustomEnchantment;

public class HellsCurseEnchantment extends CustomEnchantment implements AmbientEffect, Enchanted
{
	private double pow = -1337;
	
	public HellsCurseEnchantment()
	{
		super("Hells Curse", Matte.concat(Matte.chestplates()));
		
		setMaxLevel(1);
		setBase(5);
		setInterval(30);
		description = "#Hackedin #SuperCool #BooSwift";
	}
	
	@Override
	public void ambientPlay(Player p)
	{
		if(Math.random() > 0.5)
		{
			NMSX.breakParticles(P.getHand(p), Material.PORTAL, 1);
		}
		
		ParticleEffect.PORTAL.display(0f, 1, P.getHand(p), 24);
	}
	
	@Override
	public double getIntensity(int level)
	{
		return pow;
	}
	
	@Override
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