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
import org.cyberpwn.effex.effect.InfernoEffect;
import org.phantomapi.lang.GSound;
import org.phantomapi.nms.NMSX;
import org.phantomapi.physics.VectorMath;
import org.phantomapi.util.P;
import org.phantomapi.vfx.ParticleEffect;
import com.rit.sucy.CustomEnchantment;
import com.rit.sucy.EnchantmentAPI;

public class InfernoEnchantment extends CustomEnchantment implements AmbientEffect, Enchanted
{
	private double pow = 0.57;
	public InfernoEnchantment()
	{
		super("Inferno", Matte.concat(Matte.swords(), Matte.axes(), Matte.bow(), Matte.tnt()));
		
		setMaxLevel(3);
		setBase(5);
		setInterval(8);
		description = "Things will Burn (Swords, Axes, Bow, Tnt)";
	}
	
	@Override
	public void applyEffect(LivingEntity user, LivingEntity target, int enchantLevel, EntityDamageByEntityEvent event)
	{
		if(event.getDamage() == 0.0 || event.isCancelled())
		{
			return;
		}
		
		if(Math.random() > 0.83 - (enchantLevel / 5))
		{
			Location a = P.getHand((Player) user);
			Vector dir = VectorMath.direction(a, target.getLocation().add(0, 2, 0));
			Location b = target.getEyeLocation().add(0, 2, 0);
			boolean empowered = EnchantmentAPI.itemHasEnchantment(((Player) user).getItemInHand(), "Empowering");
			new InfernoEffect(1.6f * enchantLevel).play(a, dir, b);
			new GSound(Sound.FIRE, 2.0f, 1.8f).play(target.getLocation());
			new GSound(Sound.EXPLODE, 2.0f, 0.8f).play(target.getLocation());
			target.setFireTicks((empowered ? 29 : 0) + (15 * enchantLevel));
		}
	}
	
	@Override
	public void ambientPlay(Player p)
	{
		if(Math.random() > 0.4)
		{
			ParticleEffect.LAVA.display(0f, 1, P.getHand(p), 24);
		}
		
		NMSX.breakParticles(P.getHand(p), Material.LAVA, 1);
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