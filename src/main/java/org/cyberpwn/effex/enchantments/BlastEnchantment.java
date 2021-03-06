package org.cyberpwn.effex.enchantments;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.cyberpwn.effex.ETag;
import org.cyberpwn.effex.Enchanted;
import org.cyberpwn.effex.Matte;
import org.cyberpwn.effex.effect.AmbientEffect;
import org.phantomapi.util.P;
import org.phantomapi.vfx.ParticleEffect;
import com.rit.sucy.CustomEnchantment;

public class BlastEnchantment extends CustomEnchantment implements AmbientEffect, Enchanted
{
	private double pow = 0.29;
	public BlastEnchantment()
	{
		super("Blast", Matte.concat(Matte.tnt(), Matte.pickaxes()));
		
		setMaxLevel(5);
		setBase(5);
		setInterval(8);
		description = "Increases the blast radius of tnt. (Tnt)";
	}
	
	@Override
	public void applyEffect(LivingEntity user, LivingEntity target, int enchantLevel, EntityDamageByEntityEvent event)
	{
		
	}
	
	@Override
	public void ambientPlay(Player p)
	{
		if(p.isBlocking())
		{
			ParticleEffect.CLOUD.display(0.1f, 12, P.getHand(p, -45, 40), 48);
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
		return ETag.get(ETag.OFFENSIVE, ETag.TNT);
	}
}