package org.cyberpwn.effex.enchantments;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.cyberpwn.effex.ETag;
import org.cyberpwn.effex.Enchanted;
import org.cyberpwn.effex.Matte;
import org.cyberpwn.effex.effect.AmbientEffect;
import org.phantomapi.currency.ExperienceCurrency;
import org.phantomapi.currency.Transaction;
import org.phantomapi.sync.S;
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
		new S()
		{
			@Override
			public void sync()
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
								if(user instanceof Player)
								{
									Player p = (Player) user;
									new Transaction(new ExperienceCurrency()).to(p).amount(1 + 18 * Math.random()).noDiff().commit();
								}
							}
						}
					}
				};
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
	
	@Override
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