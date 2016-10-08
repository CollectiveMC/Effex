package org.cyberpwn.effex.enchantments;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.cyberpwn.effex.ETag;
import org.cyberpwn.effex.Enchanted;
import org.cyberpwn.effex.Matte;
import org.cyberpwn.effex.effect.AmbientEffect;
import org.phantomapi.util.P;
import org.phantomapi.vfx.ParticleEffect;
import com.rit.sucy.CustomEnchantment;

public class ReinforcedEnchantment extends CustomEnchantment implements AmbientEffect, Enchanted
{
	private double pow = 0.46;
	public ReinforcedEnchantment()
	{
		super("Reinforced", Matte.concat(Matte.armor()));
		
		setMaxLevel(2);
		setBase(5);
		setInterval(8);
		description = "Reduces all damage (Armor)";
	}
	
	@Override
	public void applyDefenseEffect(LivingEntity user, LivingEntity target, int enchantLevel, EntityDamageEvent event)
	{
		if(event.getDamage() == 0.0 || event.isCancelled())
		{
			return;
		}
		
		event.setDamage(event.getDamage() - ((event.getDamage() / (enchantLevel) / 2)));
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
		return ETag.get(ETag.DEFENSIVE);
	}
}