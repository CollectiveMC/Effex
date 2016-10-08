package org.cyberpwn.effex.enchantments;

import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.cyberpwn.effex.ETag;
import org.cyberpwn.effex.Enchanted;
import org.cyberpwn.effex.Matte;
import org.cyberpwn.effex.effect.AmbientEffect;
import org.phantomapi.lang.GSound;
import org.phantomapi.nms.NMSX;
import org.phantomapi.sfx.Audio;
import org.phantomapi.sfx.MFADistortion;
import org.phantomapi.util.P;
import org.phantomapi.vfx.ParticleEffect;
import org.phantomapi.world.Area;
import com.rit.sucy.CustomEnchantment;

public class EarthquakeEnchantment extends CustomEnchantment implements AmbientEffect, Enchanted
{
	private double pow = 0.56;
	public EarthquakeEnchantment()
	{
		super("Earthquake", Matte.concat(Matte.axes()));
		
		setMaxLevel(3);
		setBase(5);
		setInterval(8);
		description = "Shatter the ground with axe blows (Axes)";
	}
	
	@Override
	public void applyEffect(LivingEntity user, LivingEntity target, int enchantLevel, EntityDamageByEntityEvent event)
	{
		if(event.getDamage() == 0.0 || event.isCancelled())
		{
			return;
		}
		
		if(Math.random() > 0.787 - (enchantLevel / 5))
		{
			Audio a = new Audio();
			a.add(new GSound(Sound.ZOMBIE_WOODBREAK, 0.1f, 1.5f));
			new MFADistortion(7, 0.1f).distort(a).play(target.getLocation());
			Area ar = new Area(target.getLocation(), 2);
			
			for(int i = 0; i < 24; i++)
			{
				Block b = ar.random().getBlock();
				
				if(b.getType().isSolid())
				{
					NMSX.breakParticles(b.getLocation().add(0.5, 0.7, 0.5), b.getType(), 44);
				}
			}
		}
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