package org.cyberpwn.effex.enchantments;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.cyberpwn.effex.ETag;
import org.cyberpwn.effex.Enchanted;
import org.cyberpwn.effex.Matte;
import org.cyberpwn.effex.effect.AmbientEffect;
import org.phantomapi.lang.GSound;
import org.phantomapi.nms.NMSX;
import org.phantomapi.util.P;
import com.rit.sucy.CustomEnchantment;
import com.rit.sucy.EnchantmentAPI;

public class PoisonEnchantment extends CustomEnchantment implements AmbientEffect, Enchanted
{
	private double pow = 0.43;
	public PoisonEnchantment()
	{
		super("Poison", Matte.concat(Matte.swords(), Matte.axes(), Matte.bow()));
		
		setMaxLevel(2);
		setBase(5);
		setInterval(8);
		description = "Inflict poison on enemies (Sword, Axe, Bow)";
	}
	
	@Override
	public void applyEffect(LivingEntity user, LivingEntity target, int enchantLevel, EntityDamageByEntityEvent event)
	{
		if(Math.random() > 0.89 - (enchantLevel / 5))
		{
			boolean empowered = EnchantmentAPI.itemHasEnchantment(((Player) user).getItemInHand(), "Empowering");
			target.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 80 + (40 * enchantLevel) + (empowered ? 45 : 0), 0));
			NMSX.breakParticles(target.getEyeLocation(), Material.SLIME_BLOCK, 14);
			NMSX.breakParticles(target.getEyeLocation(), Material.REDSTONE_BLOCK, 14);
			new GSound(Sound.AMBIENCE_CAVE, 2.0f, 1.8f).play(target.getLocation());
		}
	}

	@Override
	public void ambientPlay(Player p)
	{
		if(Math.random() > 0.5)
		{
			NMSX.breakParticles(P.getHand(p), Material.SLIME_BLOCK, 4);
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
		return ETag.get(ETag.OFFENSIVE);
	}
}