package org.cyberpwn.effex.enchantments;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.cyberpwn.effex.ETag;
import org.cyberpwn.effex.Enchanted;
import org.cyberpwn.effex.Matte;
import org.cyberpwn.effex.effect.AmbientEffect;
import org.phantomapi.lang.GList;
import org.phantomapi.nms.NMSX;
import org.phantomapi.util.C;
import org.phantomapi.util.P;
import org.phantomapi.vfx.ParticleEffect;
import com.rit.sucy.CustomEnchantment;

public class SoulStealerEnchantment extends CustomEnchantment implements AmbientEffect, Enchanted
{
	private double pow = 0;
	public SoulStealerEnchantment()
	{
		super("Soul Stealer", Matte.concat(Matte.swords(), Matte.axes(), Matte.bow()));
		
		setMaxLevel(1);
		setBase(5);
		setInterval(30);
		description = "Counts kills on the weapon (Weapons)";
	}
	
	@Override
	public void applyEffect(LivingEntity user, LivingEntity target, int enchantLevel, EntityDamageByEntityEvent event)
	{
		if(target.getHealth() - event.getDamage() <= 0 && target.getType().equals(EntityType.PLAYER))
		{
			Player p = ((Player) user);
			ItemStack is = p.getItemInHand();
			GList<String> lore = new GList<String>(is.getItemMeta().getLore());
			GList<String> newLore = new GList<String>();
			Boolean found = false;
			
			for(String i : lore)
			{
				if(C.stripColor(i).startsWith("Kills: "))
				{
					String current = C.stripColor(i).split(": ")[1];
					String next = C.RED + "Kills: " + (Integer.valueOf(current) + 1);
					newLore.add(next);
					found = true;
				}
				
				else
				{
					newLore.add(i);
				}
			}
			
			if(!found)
			{
				newLore.add(C.RED + "Kills: 1");
			}
			
			ItemMeta im = is.getItemMeta();
			im.setLore(newLore);
			is.setItemMeta(im);
		}
	}
	
	@Override
	public void ambientPlay(Player p)
	{
		if(Math.random() > 0.5)
		{
			NMSX.breakParticles(P.getHand(p), Material.REDSTONE_BLOCK, 1);
		}
		
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
		return ETag.get(ETag.UTILITY);
	}
	
	public void setPow(double pow)
	{
		this.pow = pow;
	}
}