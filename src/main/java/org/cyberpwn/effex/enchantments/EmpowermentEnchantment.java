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

public class EmpowermentEnchantment extends CustomEnchantment implements AmbientEffect, Enchanted
{
	private double pow = 0.36;
	public EmpowermentEnchantment()
	{
		super("Empowering", Matte.concat(Matte.swords(), Matte.axes(), Matte.bow()));
		
		setMaxLevel(1);
		setBase(5);
		setInterval(30);
		description = "Increases all other Enchantment Effect Durations (Sword, Axe, Bow)";
	}
	
	@Override
	public void applyEffect(LivingEntity user, LivingEntity target, int enchantLevel, EntityDamageByEntityEvent event)
	{
		
	}
	
	@Override
	public void ambientPlay(Player p)
	{
		ParticleEffect.ENCHANTMENT_TABLE.display(0.1f, 8, P.getHand(p), 24);
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