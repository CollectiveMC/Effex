package org.cyberpwn.effex.enchantments;

import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.cyberpwn.effex.ETag;
import org.cyberpwn.effex.Enchanted;
import org.cyberpwn.effex.Matte;
import org.cyberpwn.effex.effect.AmbientEffect;
import org.phantomapi.nms.NMSX;
import org.phantomapi.util.P;
import org.phantomapi.vfx.ParticleEffect;
import com.rit.sucy.CustomEnchantment;

public class EnderEnchantment extends CustomEnchantment implements AmbientEffect, Enchanted
{
	private double pow = 1.23;
	public EnderEnchantment()
	{
		super("Ender", Matte.concat(Matte.swords(), Matte.axes(), Matte.bow()));
		
		setMaxLevel(1);
		setBase(5);
		setInterval(30);
		description = "Forged from endermen (Weapons)";
	}
	
	@Override
	public void applyEffect(LivingEntity user, LivingEntity target, int enchantLevel, EntityDamageByEntityEvent event)
	{
		if(event.getDamage() == 0.0 || event.isCancelled())
		{
			return;
		}
		
		event.setDamage(event.getDamage() + ((event.getDamage() * 0.343)));
	}
	
	@Override
	public void ambientPlay(Player p)
	{
		if(Math.random() > 0.5)
		{
			NMSX.breakParticles(P.getHand(p), Material.PORTAL, 1);
		}
		
		ParticleEffect.PORTAL.display(0f, 1, P.getHand(p), 24);
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