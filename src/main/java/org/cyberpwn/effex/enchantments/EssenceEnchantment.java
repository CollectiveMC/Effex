package org.cyberpwn.effex.enchantments;

import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.cyberpwn.effex.ETag;
import org.cyberpwn.effex.Enchanted;
import org.cyberpwn.effex.Matte;
import org.cyberpwn.effex.effect.AmbientEffect;
import org.phantomapi.sync.TaskLater;
import org.phantomapi.util.P;
import org.phantomapi.vfx.ParticleEffect;
import com.rit.sucy.CustomEnchantment;

public class EssenceEnchantment extends CustomEnchantment implements AmbientEffect, Enchanted
{
	private double pow = 0.29;
	
	public EssenceEnchantment()
	{
		super("Essence", Matte.concat(Matte.swords(), Matte.pickaxes(), Matte.axes()));
		
		setMaxLevel(3);
		setBase(5);
		setInterval(8);
		description = "Increases experience gain";
	}
	
	@Override
	public void applyEffect(LivingEntity user, LivingEntity target, int enchantLevel, EntityDamageByEntityEvent event)
	{
		new TaskLater(1)
		{
			@Override
			public void run()
			{
				if(target.isDead())
				{
					for(int i = 0; i < enchantLevel; i++)
					{
						((ExperienceOrb) target.getWorld().spawn(target.getLocation(), ExperienceOrb.class)).setExperience((int) (40 * Math.random()));
						((ExperienceOrb) target.getWorld().spawn(target.getLocation(), ExperienceOrb.class)).setExperience((int) (30 * Math.random()));
						((ExperienceOrb) target.getWorld().spawn(target.getLocation(), ExperienceOrb.class)).setExperience((int) (20 * Math.random()));
					}
				}
			}
		};
	}
	
	@Override
	public void ambientPlay(Player p)
	{
		if(p.isBlocking())
		{
			ParticleEffect.CLOUD.display(0.1f, 12, P.getHand(p, -45, 40), 48);
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
		return ETag.get(ETag.OFFENSIVE, ETag.TNT);
	}
}