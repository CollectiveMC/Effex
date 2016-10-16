package org.cyberpwn.effex.enchantments;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
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
import org.phantomapi.physics.VectorMath;
import org.phantomapi.sync.TaskLater;
import org.phantomapi.util.P;
import com.rit.sucy.CustomEnchantment;

public class DoubleStrikeEnchantment extends CustomEnchantment implements AmbientEffect, Enchanted
{
	private double pow = 0.27;
	
	public DoubleStrikeEnchantment()
	{
		super("Double Strike", Matte.concat(Matte.swords(), Matte.axes()));
		
		setMaxLevel(3);
		setBase(5);
		setInterval(30);
		description = "Kill more than one mob in a stack (Swords, Axes)";
	}
	
	@Override
	public void applyEffect(LivingEntity user, LivingEntity target, int enchantLevel, EntityDamageByEntityEvent event)
	{
		if(event.getDamage() == 0.0 || event.isCancelled())
		{
			return;
		}
		
		if(Math.random() > 0.787 - enchantLevel / 5)
		{
			if(!target.getType().equals(EntityType.PLAYER))
			{
				event.setDamage(20);
				
				new TaskLater(1)
				{
					@Override
					public void run()
					{
						Location a = P.getHand((Player) user);
						Vector dir = VectorMath.direction(a, target.getLocation().add(0, 1.5, 0));
						Location b = target.getEyeLocation();
						new VampiricEffect(1.6f * enchantLevel).play(a, dir, b.add(0, 1.5, 0));
						Location s = target.getLocation().clone();
						s.setY(0);
						new GSound(Sound.SLIME_ATTACK, 2.0f, 1.8f).play(target.getLocation());
						new GSound(Sound.SLIME_ATTACK, 2.0f, 1.0f).play(target.getLocation());
						new GSound(Sound.SLIME_ATTACK, 2.0f, 0.2f).play(target.getLocation());
						new GSound(Sound.SLIME_ATTACK, 2.0f, 0.5f).play(target.getLocation());
						target.damage(event.getDamage());
						
						new TaskLater(2)
						{
							@Override
							public void run()
							{
								target.damage(event.getDamage());
							}
						};
					}
				};
			}
		}
	}
	
	@Override
	public void ambientPlay(Player p)
	{
		
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