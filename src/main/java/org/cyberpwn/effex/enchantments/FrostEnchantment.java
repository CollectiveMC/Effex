package org.cyberpwn.effex.enchantments;

import org.bukkit.Location;
import org.bukkit.Material;
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
import org.cyberpwn.effex.effect.FrostEffect;
import org.phantomapi.lang.GSound;
import org.phantomapi.nms.NMSX;
import org.phantomapi.physics.VectorMath;
import org.phantomapi.util.P;
import org.phantomapi.vfx.ParticleEffect;
import com.rit.sucy.CustomEnchantment;
import com.rit.sucy.EnchantmentAPI;

public class FrostEnchantment extends CustomEnchantment implements AmbientEffect, Enchanted
{
	private double pow = 0.53;
	public FrostEnchantment()
	{
		super("Frost", Matte.concat(Matte.swords(), Matte.axes(), Matte.bow()));
		
		setMaxLevel(3);
		setBase(5);
		setInterval(30);
		description = "Slow and chill enemies (Swords, Axes, Bow)";
	}
	
	@Override
	public void applyEffect(LivingEntity user, LivingEntity target, int enchantLevel, EntityDamageByEntityEvent event)
	{
		if(event.getDamage() == 0.0 || event.isCancelled())
		{
			return;
		}
		
		if(Math.random() > 0.8 - (enchantLevel / 5))
		{
			boolean empowered = EnchantmentAPI.itemHasEnchantment(((Player) user).getItemInHand(), "Empowering");
			
			Location a = P.getHand((Player) user);
			Vector dir = VectorMath.direction(a, target.getLocation().add(0, 2, 0));
			Location b = target.getEyeLocation().add(0, 2, 0);
			
			new FrostEffect(1.6f * enchantLevel).play(a, dir, b);
			new GSound(Sound.FIZZ, 2.0f, 1.8f).play(target.getLocation());
			new GSound(Sound.DIG_SNOW, 2.0f, 0.8f).play(target.getLocation());
			target.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, (20 * enchantLevel) + (empowered ? 30 : 0), 0));
			target.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, (20 * enchantLevel) + (empowered ? 30 : 0), 0));
		}
	}

	@Override
	public void ambientPlay(Player p)
	{
		if(Math.random() > 0.5)
		{
			NMSX.breakParticles(P.getHand(p), Material.SNOW_BLOCK, 1);
		}
		
		ParticleEffect.CLOUD.display(0f, 1, P.getHand(p), 24);
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
		return ETag.get(ETag.OFFENSIVE);
	}
}