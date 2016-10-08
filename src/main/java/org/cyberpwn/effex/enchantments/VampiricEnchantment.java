package org.cyberpwn.effex.enchantments;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import org.cyberpwn.effex.ETag;
import org.cyberpwn.effex.Enchanted;
import org.cyberpwn.effex.Matte;
import org.cyberpwn.effex.effect.AmbientEffect;
import org.cyberpwn.effex.effect.VampiricEffect;
import org.phantomapi.lang.GSound;
import org.phantomapi.physics.VectorMath;
import org.phantomapi.util.P;
import org.phantomapi.vfx.ParticleEffect;
import com.rit.sucy.CustomEnchantment;
import com.rit.sucy.EnchantmentAPI;

public class VampiricEnchantment extends CustomEnchantment implements AmbientEffect, Enchanted
{
	private double pow = 0.34;
	
	public VampiricEnchantment()
	{
		super("Vampiric", Matte.concat(Matte.swords(), Matte.axes(), Matte.bow()));
		
		setMaxLevel(2);
		setBase(5);
		setInterval(8);
		description = "Steal a portion of the damage (Weapons)";
	}
	
	@Override
	public void applyEffect(LivingEntity user, LivingEntity target, int enchantLevel, EntityDamageByEntityEvent event)
	{
		if(event.getDamage() == 0.0 || event.isCancelled())
		{
			return;
		}
		
		try
		{
			Player p = (Player) target;
			
			for(ItemStack i : p.getInventory().getArmorContents())
			{
				if(EnchantmentAPI.itemHasEnchantment(i, "Soulless"))
				{
					return;
				}
			}
		}
		
		catch(Exception e)
		{
			
		}
		
		if(Math.random() > 0.787 - (enchantLevel / 5))
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
			
			if(user.getHealth() + event.getDamage() / 3 <= user.getMaxHealth())
			{
				user.setHealth(user.getHealth() + event.getDamage() / 3);
			}
			
			else
			{
				user.setHealth(user.getMaxHealth());
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
	
	@Override
	public ETag[] getTags()
	{
		return ETag.get(ETag.OFFENSIVE);
	}
	
	public void setPow(double pow)
	{
		this.pow = pow;
	}
}