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

public class ButcherEnchantment extends CustomEnchantment implements AmbientEffect, Enchanted
{
	private double pow = 0.51;
	
	public ButcherEnchantment()
	{
		super("Butcher", Matte.concat(Matte.swords(), Matte.axes(), Matte.bow()));
		
		setMaxLevel(3);
		setBase(5);
		setInterval(8);
		description = "Extra drops for mobs!";
	}
	
	@Override
	public void applyEffect(LivingEntity user, LivingEntity target, int enchantLevel, EntityDamageByEntityEvent event)
	{
		
	}
	
	@Override
	public void ambientPlay(Player p)
	{
		ParticleEffect.REDSTONE.display(0f, 1, P.getHand(p), 24);
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
		return ETag.get(ETag.OFFENSIVE, ETag.UTILITY);
	}
}