package org.cyberpwn.effex.enchantments;

import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.cyberpwn.effex.ETag;
import org.cyberpwn.effex.Enchanted;
import org.cyberpwn.effex.Matte;
import org.cyberpwn.effex.effect.AmbientEffect;
import org.phantomapi.lang.GSound;
import org.phantomapi.sfx.Audio;
import org.phantomapi.util.P;
import org.phantomapi.vfx.ParticleEffect;
import com.rit.sucy.CustomEnchantment;

public class LongShotEnchantment extends CustomEnchantment implements AmbientEffect, Enchanted
{
	private double pow = 0.38;
	
	public LongShotEnchantment()
	{
		super("Long Shot", Matte.bow());
		
		setMaxLevel(1);
		setBase(5);
		setInterval(8);
		description = "The further the arrow travels, the more damage it does (Bow)";
	}
	
	@Override
	public void applyEffect(LivingEntity user, LivingEntity target, int enchantLevel, EntityDamageByEntityEvent event)
	{
		if(event.getDamage() == 0.0 || event.isCancelled())
		{
			return;
		}
		
		double maxDistance = 3600;
		double minDamage = 1;
		double distanceSquared = user.getLocation().distanceSquared(target.getLocation());
		double distance = distanceSquared > maxDistance ? maxDistance : distanceSquared;
		double initial = 10.0 * (distance / maxDistance);
		double finalDamage = initial < minDamage ? minDamage : initial;
		
		event.setDamage(finalDamage);
		
		Audio a = new Audio();
		a.add(new GSound(Sound.ORB_PICKUP, 1f, (float) (2 * (finalDamage / 10))));
		a.play((Player) user);
	}
	
	@Override
	public void ambientPlay(Player p)
	{
		ParticleEffect.ENCHANTMENT_TABLE.display(1f, 12, P.getHand(p), 48);
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
		return ETag.get(ETag.OFFENSIVE);
	}
}