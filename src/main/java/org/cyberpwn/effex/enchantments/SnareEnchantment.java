package org.cyberpwn.effex.enchantments;

import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.util.Vector;
import org.cyberpwn.effex.ETag;
import org.cyberpwn.effex.Enchanted;
import org.cyberpwn.effex.Matte;
import org.cyberpwn.effex.effect.AmbientEffect;
import org.phantomapi.lang.GSound;
import org.phantomapi.sync.Task;
import org.phantomapi.util.P;
import org.phantomapi.vfx.ParticleEffect;
import com.rit.sucy.CustomEnchantment;
import com.rit.sucy.EnchantmentAPI;

public class SnareEnchantment extends CustomEnchantment implements AmbientEffect, Enchanted
{
	private double pow = 0.32;
	public SnareEnchantment()
	{
		super("Snare", Matte.bow());
		
		setMaxLevel(2);
		setBase(5);
		setInterval(8);
		description = "Slows enemies in place (Bow)";
	}
	
	@Override
	public void applyEffect(LivingEntity user, LivingEntity target, int enchantLevel, EntityDamageByEntityEvent event)
	{
		if(Math.random() > 0.8 - (enchantLevel / 5))
		{
			int[] k = new int[]{0};
			
			new GSound(Sound.CLICK, 2f, 0.4f).play(target.getLocation());
			new GSound(Sound.EXPLODE, 2f, 1.7f).play(target.getLocation());
			boolean empowered = EnchantmentAPI.itemHasEnchantment(((Player) user).getItemInHand(), "Empowering");
			
			new Task(0)
			{
				@Override
				public void run()
				{
					k[0]++;
					
					target.setVelocity(new Vector(0, -1, 0));
					ParticleEffect.BARRIER.display(0, 1, target.getLocation(), 32);
					
					if(k[0] > (enchantLevel * 10) + (empowered ? 20 : 0))
					{
						cancel();
					}
				}
			};
		}
	}
	
	@Override
	public void ambientPlay(Player p)
	{
		if(p.isBlocking())
		{
			ParticleEffect.CRIT_MAGIC.display(1f, 12, P.getHand(p, -45, 40), 48);
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
		return ETag.get(ETag.UTILITY, ETag.OFFENSIVE);
	}
}