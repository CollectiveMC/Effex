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

public class StickyEnchantment extends CustomEnchantment implements AmbientEffect, Enchanted
{
	private double pow = 0.18;
	public StickyEnchantment()
	{
		super("Sticky", Matte.tnt());
		
		setMaxLevel(1);
		setBase(5);
		setInterval(8);
		description = "TNT Will blow up where it was ignited (Tnt)";
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
			ParticleEffect.SLIME.display(1f, 12, P.getHand(p, -45, 40), 48);
		}
	}
	
	@Override
	public double getIntensity(int level)
	{
		return pow;
	}

	@Override
	public ETag[] getTags()
	{
		return ETag.get(ETag.TNT);
	}
	
	public void setPow(double pow)
	{
		this.pow = pow;
	}
}