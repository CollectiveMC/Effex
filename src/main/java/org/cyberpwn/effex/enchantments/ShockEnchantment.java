package org.cyberpwn.effex.enchantments;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;
import org.cyberpwn.effex.ETag;
import org.cyberpwn.effex.Enchanted;
import org.cyberpwn.effex.Matte;
import org.cyberpwn.effex.effect.AmbientEffect;
import org.cyberpwn.effex.effect.ShockEffect;
import org.phantomapi.lang.GSound;
import org.phantomapi.physics.VectorMath;
import org.phantomapi.util.P;
import org.phantomapi.vfx.ParticleEffect;
import com.rit.sucy.CustomEnchantment;

public class ShockEnchantment extends CustomEnchantment implements AmbientEffect, Enchanted
{
	private double pow = 0.55;
	
	public ShockEnchantment()
	{
		super("Shock", Matte.concat(Matte.swords(), Matte.axes(), Matte.bow()));
		
		setMaxLevel(3);
		setBase(5);
		setInterval(8);
		description = "Shocking! (Weapons)";
	}
	
	@Override
	public void applyEffect(LivingEntity user, LivingEntity target, int enchantLevel, EntityDamageByEntityEvent event)
	{
		if(event.getDamage() == 0.0 || event.isCancelled())
		{
			return;
		}
		
		if(Math.random() > 0.8 - enchantLevel / 5)
		{
			Location a = P.getHand((Player) user);
			Vector dir = VectorMath.direction(a, target.getLocation());
			Location b = target.getEyeLocation();
			
			new ShockEffect(2.6f * enchantLevel).play(a, dir, b);
			Location s = target.getLocation().clone();
			s.setY(0);
			new GSound(Sound.AMBIENCE_THUNDER, 2.0f, 1.8f).play(target.getLocation());
			new GSound(Sound.EXPLODE, 2.0f, 1.8f).play(target.getLocation());
			target.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 10, 2));
		}
	}
	
	@Override
	public void ambientPlay(Player p)
	{
		ParticleEffect.CRIT_MAGIC.display(0f, 1, P.getHand(p), 24);
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