package org.cyberpwn.effex.enchantments;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.util.Vector;
import org.cyberpwn.effex.ETag;
import org.cyberpwn.effex.Enchanted;
import org.cyberpwn.effex.Matte;
import org.cyberpwn.effex.effect.AmbientEffect;
import org.cyberpwn.effex.effect.VampiricEffect;
import org.phantomapi.lang.GSound;
import org.phantomapi.nms.NMSX;
import org.phantomapi.physics.VectorMath;
import org.phantomapi.util.C;
import org.phantomapi.util.P;
import org.phantomapi.vfx.ParticleEffect;
import com.rit.sucy.CustomEnchantment;

public class SoulEruptionEnchantment extends CustomEnchantment implements AmbientEffect, Enchanted
{
	private double pow = 0.07;
	
	public SoulEruptionEnchantment()
	{
		super("Soul Eruption", Matte.concat(Matte.swords(), Matte.axes(), Matte.bow()));
		
		setMaxLevel(3);
		setBase(5);
		setInterval(30);
		description = "Sacrifice health to increase damage (Weapons)";
	}
	
	@Override
	public void applyEffect(LivingEntity user, LivingEntity target, int enchantLevel, EntityDamageByEntityEvent event)
	{
		if(event.getDamage() == 0.0 || event.isCancelled())
		{
			return;
		}
		
		if(Math.random() > 0.727 - enchantLevel / 15)
		{
			double modifier = event.getDamage() / (enchantLevel > 2 ? 2 : 3) / 2;
			event.setDamage(event.getDamage() + modifier);
			
			Location a = P.getHand((Player) user);
			Vector dir = VectorMath.direction(a, target.getLocation().add(0, 1.5, 0));
			Location b = target.getEyeLocation();
			
			new VampiricEffect(0.9f * enchantLevel).play(a, dir, b.add(0, 1.5, 0));
			Location s = target.getLocation().clone();
			s.setY(0);
			new GSound(Sound.SLIME_ATTACK, 2.0f, 1.8f).play(target.getLocation());
			new GSound(Sound.SLIME_ATTACK, 2.0f, 1.0f).play(target.getLocation());
			new GSound(Sound.SLIME_ATTACK, 2.0f, 0.2f).play(target.getLocation());
			new GSound(Sound.SLIME_ATTACK, 2.0f, 0.5f).play(target.getLocation());
			user.sendMessage(C.RED + "* You sacrifice health to increase damage.");
			user.damage(modifier);
		}
	}
	
	@Override
	public void ambientPlay(Player p)
	{
		if(Math.random() > 0.5)
		{
			NMSX.breakParticles(P.getHand(p), Material.REDSTONE_BLOCK, 1);
		}
		
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
		return ETag.get(ETag.OFFENSIVE);
	}
}